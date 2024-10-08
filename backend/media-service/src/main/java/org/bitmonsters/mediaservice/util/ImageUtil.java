package org.bitmonsters.mediaservice.util;

import org.bitmonsters.mediaservice.exception.InvalidFileTypeException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

@Service
public class ImageUtil {

    private final static String[] IMAGE_TYPES = {"image/png", "image/jpeg", "image/webp"};

    public OutputStream scaleImage(MultipartFile file, Float percentage) throws IOException {
        BufferedImage originalImage = ImageIO.read(file.getInputStream());

        int targetWidth = (int) (percentage * originalImage.getWidth());
        int targetHeight = (int) (percentage * originalImage.getHeight());

        Image scaledImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        // create a buffered image to hold the scaled image
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_BGR);

        // draw the scaled image onto the buffered image
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(scaledImage, 0, 0, null);
        graphics2D.dispose();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(resizedImage, Objects.requireNonNull(file.getContentType()).split("/")[1], byteArrayOutputStream);

        return byteArrayOutputStream;
    }

    public void validateImage(MultipartFile file) {
        boolean status = false;
        for (String type: IMAGE_TYPES) {
            if (type.equals(file.getContentType()))
                status = true;
        }

        if (!status)
            throw new InvalidFileTypeException(String.format("invalid file type: required: png/jpeg/webp: provided: %s", file.getContentType()));

    }

    public String getThumbnailFileName(String originalFileName) {
        return "tmb-" + originalFileName;
    }

}
