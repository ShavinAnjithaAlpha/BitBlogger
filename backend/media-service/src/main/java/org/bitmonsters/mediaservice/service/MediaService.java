package org.bitmonsters.mediaservice.service;

import com.azure.storage.blob.BlobServiceClient;
import org.bitmonsters.mediaservice.dto.FileRequest;
import org.bitmonsters.mediaservice.dto.MediaID;
import org.bitmonsters.mediaservice.dto.MediaObject;
import org.bitmonsters.mediaservice.exception.StorageException;
import org.bitmonsters.mediaservice.model.Media;
import org.bitmonsters.mediaservice.repository.MediaRepository;
import org.bitmonsters.mediaservice.util.FileUtil;
import org.bitmonsters.mediaservice.util.ImageUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.security.NoSuchAlgorithmException;

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
        this.fileUtil = fileUtil;
        // initialize the storage service
        storageService.init();
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
                            .fileSize(((long) (byteArrayOutputStream.size())))
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


    public void deleteMedia(String url) throws MalformedURLException {
        // get the file name from the url
        System.out.println(url);
        String[] urlParts = url.split("/");
        String fileName = urlParts[urlParts.length - 1];
        System.out.println(fileName);

        // delete the blob file from the storage service
        storageService.delete(fileName);

        // remove the file's metadata from the database also
        repository.deleteByFileUrl(url);
    }
}
