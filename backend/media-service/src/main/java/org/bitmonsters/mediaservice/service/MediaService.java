package org.bitmonsters.mediaservice.service;

import org.bitmonsters.mediaservice.dto.ExceptionResponse;
import org.bitmonsters.mediaservice.dto.FileRequest;
import org.bitmonsters.mediaservice.dto.MediaID;
import org.bitmonsters.mediaservice.dto.MediaObject;
import org.bitmonsters.mediaservice.exception.StorageException;
import org.bitmonsters.mediaservice.exception.StorageFileNotFoundException;
import org.bitmonsters.mediaservice.model.Media;
import org.bitmonsters.mediaservice.repository.MediaRepository;
import org.bitmonsters.mediaservice.util.FileUtil;
import org.bitmonsters.mediaservice.util.ImageUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

@Component
public class MediaService {

    private final StorageService storageService;
    private final MediaRepository repository;
    private final ImageUtil imageUtil;
    private final MediaMapper mapper;
    private final FileUtil fileUtil;

    public MediaService(StorageService storageService, MediaRepository repository, ImageUtil imageUtil, MediaMapper mapper, FileUtil fileUtil) {
        this.storageService = storageService;
        this.repository = repository;
        this.imageUtil = imageUtil;
        this.mapper = mapper;
        // initialize the storage service
        storageService.init();
        this.fileUtil = fileUtil;
    }

    public MediaID uploadImageFile(MultipartFile multipartFile, FileRequest fileRequest) {
        try {
            // validate the file type
            imageUtil.validateImage(multipartFile);
            // upload the file to the storage service
            String url = storageService.store(multipartFile, fileRequest.fileName());
            // store the uploaded media metadata in the database
            Media image = repository.save(mapper.toMedia(url, fileRequest.fileName(), fileRequest.contentType(), multipartFile, false));

            // create a thumbnail from the image provided
            OutputStream thumbnail = imageUtil.scaleImage(multipartFile, 0.4F);
            ByteArrayOutputStream byteArrayOutputStream = (ByteArrayOutputStream) thumbnail;
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            // store the thumbnail in the storage service and get the url
            String thumbnailUrl = storageService.store(byteArrayInputStream, imageUtil.getThumbnailFileName(fileRequest.fileName()));

            // save the thumbnail metadata in the database
            Media thumbnailMedia = repository.save(
                    Media.builder()
                            .fileName(imageUtil.getThumbnailFileName(fileRequest.fileName()))
                            .fileUrl(thumbnailUrl)
                            .fileType(multipartFile.getContentType())
                            .fileSize(((long) byteArrayInputStream.available()))
                            .checksum(fileUtil.getChecksum(byteArrayInputStream.readAllBytes()))
                            .isThumbnail(true)
                            .build()
            );

            return MediaID.builder()
                    .media(MediaObject.builder().id(image.getId()).url(url).build())
                    .thumbnail(MediaObject.builder().id(thumbnailMedia.getId()).url(thumbnailUrl).build())
                    .build();

        } catch(NoSuchAlgorithmException exception) {
            throw new StorageException(String.format("Failed to load digest algorithm: %s", exception.getMessage()));
        } catch (IOException exception) {
            throw new StorageException("Failed to store file on the storage");
        }
    }

    public MediaObject uploadVideoFile(MultipartFile multipartFile) {
        return null;
    }

    public MediaObject uploadFile(MultipartFile multipartFile, FileRequest fileRequest) {
        try {
            // store the file in the storage service
            String url = storageService.store(multipartFile, fileRequest.fileName());
            // store meta dat ain the database
            Media media = repository.save(mapper.toMedia(url, fileRequest.fileName(), fileRequest.contentType(), multipartFile, false));

            // return a Media object
            return MediaObject.builder()
                    .id(media.getId())
                    .url(url)
                    .build();
        } catch (IOException exception) {
            throw new StorageException("Failed to store file on the storage");
        } catch (NoSuchAlgorithmException exception) {
            throw new StorageException(String.format("Failed to load digest algorithm: %s", exception.getMessage()));
        }
    }

    @ExceptionHandler(StorageException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handleStorageException(StorageException exception) {
        return ExceptionResponse.builder()
                .error(exception.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleStorageFileNotFoundException(StorageFileNotFoundException exception) {
        return ExceptionResponse.builder()
                .error(exception.getMessage())
                .status(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase())
                .timestamp(LocalDateTime.now())
                .build();
    }
}
