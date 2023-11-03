package com.solstis.purge.solstispurge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SolstisPurgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SolstisPurgeApplication.class, args);
	}

}
