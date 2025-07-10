package com.mystical.VisionLOC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
//@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@EnableAsync
//@EnableScheduling
public class VisionLocApplication {

	public static void main(String[] args) {
		SpringApplication.run(VisionLocApplication.class, args);
	}

}
