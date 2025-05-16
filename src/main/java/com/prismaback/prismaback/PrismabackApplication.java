package com.prismaback.prismaback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.github.cdimascio.dotenv.*;

@SpringBootApplication
@EnableJpaAuditing
public class PrismabackApplication {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.load();
		System.setProperty("DATABASE_USERNAME", dotenv.get("DATABASE_USERNAME"));
		System.setProperty("DATABASE_PASSWORD", dotenv.get("DATABASE_PASSWORD"));

		SpringApplication.run(PrismabackApplication.class, args);
		System.out.println("Startup sequence complete: Category is... Java Realness! 10s, 10s, 10s across the board!");
	}

}
