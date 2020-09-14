package br.edu.fatec.buiatchaka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class BuiatchakaGamesApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuiatchakaGamesApplication.class, args);
	}
}
