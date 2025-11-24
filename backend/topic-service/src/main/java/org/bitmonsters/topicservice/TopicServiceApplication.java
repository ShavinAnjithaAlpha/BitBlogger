package org.bitmonsters.topicservice;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.env.AbstractEnvironment;

@SpringBootApplication
@EnableFeignClients
public class TopicServiceApplication {

	public static void main(String[] args) {
		String profile = System.getProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME);
		if (profile == null) {
			profile = System.getenv("SPRING_PROFILES_ACTIVE");
		}

		if ("dev".equalsIgnoreCase(profile)) {
			Dotenv dotenv = Dotenv.configure()
					.ignoreIfMissing()
					.load();

			dotenv.entries().forEach(entry -> {
				// Avoid overriding existing environment variables
				if (System.getenv(entry.getKey()) == null && System.getProperty(entry.getKey()) == null) {
					System.setProperty(entry.getKey(), entry.getValue());
				}
			});
		}
		SpringApplication.run(TopicServiceApplication.class, args);
	}

}
