package br.edu.fatec.buiatchaka;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.fatec.buiatchaka.dominio.cliente.Cartao;
import br.edu.fatec.buiatchaka.dominio.cliente.Cliente;
import br.edu.fatec.buiatchaka.dominio.cliente.Cupom;
import br.edu.fatec.buiatchaka.dominio.cliente.Endereco;
import br.edu.fatec.buiatchaka.dominio.cliente.Telefone;

@SpringBootApplication
public class BuiatchakaGamesApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuiatchakaGamesApplication.class, args);
	}
}
