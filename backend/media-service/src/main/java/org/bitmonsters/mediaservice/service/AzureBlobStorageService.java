package org.bitmonsters.mediaservice.service;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import lombok.RequiredArgsConstructor;
import org.bitmonsters.mediaservice.exception.StorageException;
import org.bitmonsters.mediaservice.exception.StorageFileNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
@RequiredArgsConstructor
public class AzureBlobStorageService implements StorageService{

    @Value("${azure.storage.connection-string}")
    private String connectionString;

    @Value("${azure.storage.container}")
    private String containerName;

    private BlobServiceClient blobServiceClient;

    @Override
    public void init() {
        blobServiceClient = new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient();
    }

    @Override
    public String store(MultipartFile file, String fileName) {
        try {
            // check if the file is empty
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file");
            }

            // store the file in the azure blob container
            BlobContainerClient containerClient = blobServiceClient
                    .getBlobContainerClient(containerName);

            containerClient.getBlobClient(fileName)
                    .upload(file.getInputStream());

            // return the url of the stored file to the client
            return buildUrl(containerClient.getBlobContainerUrl(), fileName);
        } catch (IOException exception) {
            throw new StorageException("Failed to store file", exception);
        }
    }

    @Override
    public String store(InputStream file, String fileName) {
        try {
            // check if the file is empty
            if (file.available() == 0) {
                throw new StorageException("Failed to store empty file");
            }

            // store the file in the azure blob container
            BlobContainerClient containerClient = blobServiceClient
                    .getBlobContainerClient(containerName);

            containerClient.getBlobClient(fileName)
                    .upload(file);

            // return the url of the stored file to the client
            return buildUrl(containerClient.getBlobContainerUrl(), fileName);
        } catch (IOException exception) {
            throw new StorageException("Failed to store file", exception);
        }
    }

    @Override
    public void delete(String fileName) {
            BlobContainerClient blobContainerClient = blobServiceClient.getBlobContainerClient(containerName);

            if (!blobContainerClient.exists()) {
                throw new StorageFileNotFoundException(String.format("file is not found in storage: %s", fileName));
            }

            blobContainerClient.getBlobClient(fileName).delete();

    }

    @Override
    public OutputStream get(String fileName) {
        BlobContainerClient blobContainerClient = blobServiceClient.getBlobContainerClient(containerName);

        // check whether if the blob exists in the blob container
        if (!blobContainerClient.exists()) {
            throw new StorageFileNotFoundException(String.format("file is not found in storage: %s", fileName));
        }
        // get the output stream out of the blob
        OutputStream outputStream = new ByteArrayOutputStream();
        blobContainerClient.getBlobClient(fileName).downloadStream(outputStream);
        return outputStream;
    }

    String buildUrl(String containerUrl, String fileName) {
        return String.format("%s/%s", containerUrl, fileName);
    }
}
