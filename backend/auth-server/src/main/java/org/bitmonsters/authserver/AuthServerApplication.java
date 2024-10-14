package org.bitmonsters.authserver;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.Objects;

@SpringBootApplication
@EnableFeignClients
public class AuthServerApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		System.setProperty("MAILTRAP_DEV_EMAIL_ID", Objects.requireNonNull(dotenv.get("MAILTRAP_DEV_EMAIL_ID")));
		System.setProperty("MAILTRAP_DEV_EMAIL_PASSWORD", Objects.requireNonNull(dotenv.get("MAILTRAP_DEV_EMAIL_PASSWORD")));

		SpringApplication.run(AuthServerApplication.class, args);
	}

}
