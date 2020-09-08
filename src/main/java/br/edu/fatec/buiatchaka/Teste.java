package br.edu.fatec.buiatchaka;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.fatec.buiatchaka.dominio.cliente.Cliente;
import br.edu.fatec.buiatchaka.repository.ClienteRepository;

public class Teste {
	private long id;
	Optional<Cliente> c;
	@Autowired
	public ClienteRepository repo;
	Teste(long id){
		this.id=id;
	}
	
	public void mostrar() {
		c = repo.findById(id);
		System.out.println(c.toString());
	}
}
