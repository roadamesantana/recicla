package com.recila.reader;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.recila.reader.dto.ReciclaDTO;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<Collection<ReciclaDTO>> typeReference = new TypeReference<Collection<ReciclaDTO>>() {};

			File file = new File("/home/ronald/workspace/spring-recicla/recila.reader/target/recicla.json");
//			InputStream inputStream = TypeReference.class.getResourceAsStream("/recicla.json");

			try {
				List<ReciclaDTO> users = mapper.readValue(file, typeReference);
				users.stream().forEach(x -> System.out.println(x.toString()));
				System.out.println("FIM");
			} catch (IOException e) {
				System.out.println("Erro: " + e);
			}
		};
	}

}
