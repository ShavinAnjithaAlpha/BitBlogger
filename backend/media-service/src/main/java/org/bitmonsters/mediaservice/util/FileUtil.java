package org.bitmonsters.mediaservice.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class FileUtil {

    public String getChecksum(byte[] bytes) throws NoSuchAlgorithmException, IOException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] checksum = messageDigest.digest(bytes);

        StringBuilder stringBuilder = new StringBuilder();
        for (byte b: checksum) {
            stringBuilder.append(String.format("%02x", b));
        }

        return stringBuilder.toString();

    }

    public boolean compareChecksum(MultipartFile file, String checksum) {
        return true;
    }

    public String getExtension(String fileName) {
        // create new Path variable
        return StringUtils.getFilenameExtension(fileName);
    }

}
