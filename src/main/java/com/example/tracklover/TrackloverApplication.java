package com.example.tracklover;

import com.example.tracklover.main.Main;
import com.example.tracklover.model.Artista;
import com.example.tracklover.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TrackloverApplication implements CommandLineRunner {
	@Autowired
	ArtistaRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(TrackloverApplication.class, args);
	}

	@Override

	public void run(String... args) throws Exception {
		Main principal = new Main(repository);
		principal.exibirMenu();
	}
}
