package org.bitmonsters.tagservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableHystrix
@EnableScheduling
public class TagServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TagServiceApplication.class, args);
	}

}
