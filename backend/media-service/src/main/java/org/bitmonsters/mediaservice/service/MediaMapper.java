package org.bitmonsters.mediaservice.service;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.mediaservice.model.ContentType;
import org.bitmonsters.mediaservice.model.Media;
import org.bitmonsters.mediaservice.util.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class MediaMapper {

    private final FileUtil fileUtil;

    public Media toMedia(String url, String fileName, ContentType contentType, MultipartFile multipartFile, boolean isThumbnail) throws NoSuchAlgorithmException, IOException {
        return Media.builder()
                .fileName(fileName)
                .fileSize(multipartFile.getSize())
                .fileUrl(url)
                .fileType(multipartFile.getContentType())
                .checksum(fileUtil.getChecksum(multipartFile.getBytes()))
                .isThumbnail(isThumbnail)
                .contentType(contentType)
                .build();
    }
}
