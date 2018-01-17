package org.nicolas.stringbreaker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StringbreakerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StringbreakerApplication.class, args);
	}

}
