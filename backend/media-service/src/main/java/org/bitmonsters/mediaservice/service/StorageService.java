package org.bitmonsters.mediaservice.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.io.OutputStream;

public interface StorageService {

    void init();

    String store(MultipartFile file, String fileName);

    String store(InputStream file, String fileName);

    void delete(String fileName);

    OutputStream get(String fileName);
}
