package com.simplefileserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class SinglefileserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(SinglefileserverApplication.class, args);
	}

}
