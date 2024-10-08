package org.bitmonsters.mediaservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.bitmonsters.mediaservice.dto.FileRequest;
import org.bitmonsters.mediaservice.dto.MediaID;
import org.bitmonsters.mediaservice.dto.MediaObject;
import org.bitmonsters.mediaservice.service.MediaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/media")
@RequiredArgsConstructor
public class MediaController {

    private final MediaService mediaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MediaObject uploadFile(
            @RequestParam("file")MultipartFile multipartFile,
            @Valid @ModelAttribute FileRequest fileRequest
            ) {
        return mediaService.uploadFile(multipartFile, fileRequest);
    }

    @PostMapping("/image")
    @ResponseStatus(HttpStatus.CREATED)
    public MediaID uploadImage(
            @RequestParam("file")MultipartFile multipartFile,
            @Valid @ModelAttribute FileRequest imageRequest
            ) {
        return mediaService.uploadImageFile(multipartFile, imageRequest);
    }

    @PostMapping("/video")
    @ResponseStatus(HttpStatus.CREATED)
    public MediaObject uploadVideo(
            @RequestParam("file")MultipartFile multipartFile
    ) {
        return mediaService.uploadVideoFile(multipartFile);
    }

}
