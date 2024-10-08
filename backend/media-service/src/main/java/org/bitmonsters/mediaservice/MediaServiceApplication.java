package org.bitmonsters.mediaservice;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;

@SpringBootApplication
public class MediaServiceApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		System.setProperty("AZURE_STORAGE_CONNECTION_STRING", Objects.requireNonNull(dotenv.get("AZURE_STORAGE_CONNECTION_STRING")));
		System.setProperty("AZURE_STORAGE_CONTAINER_NAME", Objects.requireNonNull(dotenv.get("AZURE_STORAGE_CONTAINER_NAME")));

		SpringApplication.run(MediaServiceApplication.class, args);
	}

}
