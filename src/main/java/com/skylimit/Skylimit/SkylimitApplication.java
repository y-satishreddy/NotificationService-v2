package com.skylimit.Skylimit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SkylimitApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkylimitApplication.class, args);
	}

}
