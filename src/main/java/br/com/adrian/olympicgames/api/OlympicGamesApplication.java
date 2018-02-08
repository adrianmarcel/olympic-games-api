package br.com.adrian.olympicgames.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class OlympicGamesApplication {

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(OlympicGamesApplication.class);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(OlympicGamesApplication.class, args);
	}
}
