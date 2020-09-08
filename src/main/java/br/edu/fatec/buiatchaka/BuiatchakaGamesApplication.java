package br.edu.fatec.buiatchaka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.fatec.buiatchaka.dominio.cliente.Cliente;

@SpringBootApplication
public class BuiatchakaGamesApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuiatchakaGamesApplication.class, args);
		Cliente c = new Cliente();
		c.setNome("Teste");
		System.out.println(c.getNome());
		Teste t = new Teste(1L);
		t.mostrar();
	}
}
