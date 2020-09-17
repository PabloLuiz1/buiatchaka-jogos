package br.edu.fatec.buiatchaka.web.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fatec.buiatchaka.dominio.cliente.Cliente;
import br.edu.fatec.buiatchaka.repository.cliente.ClienteRepository;
import br.edu.fatec.buiatchaka.web.service.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repo;
	
	public Cliente clienteLogado(Cliente cliente) {
		Optional<Cliente> clienteLogado = repo.findById(cliente.getId());
		return clienteLogado.orElseThrow(
				() -> new ObjectNotFoundException("Item não encontrado. Tipo: " + Cliente.class.getName()));
	}
	
}
