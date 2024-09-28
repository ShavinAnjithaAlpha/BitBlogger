package org.bitmonsters.pollservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PollServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PollServiceApplication.class, args);
	}

}
