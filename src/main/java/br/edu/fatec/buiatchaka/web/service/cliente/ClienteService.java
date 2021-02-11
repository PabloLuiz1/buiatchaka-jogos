package br.edu.fatec.buiatchaka.web.service.cliente;

import java.time.LocalDate;
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
	
	public Cliente logar(Cliente cliente) {
		Optional<Cliente> c = repo.findLogin(cliente.getEmail(), cliente.getSenha());
		return c.orElseThrow(
				() -> new ObjectNotFoundException("Item não encontrado. Tipo: " + Cliente.class.getName()));
	}

	public Cliente consultar(Long id) {
		Optional<Cliente> cliente = repo.findById(id);
		return cliente.orElseThrow(
				() -> new ObjectNotFoundException("Item não encontrado. Tipo: " + Cliente.class.getName()));
	}
	
	public void salvar(Cliente cliente) {
		Cliente c = consultar(cliente.getId());
		cliente.setCpf(c.getCpf());
		cliente.setDataNascimento(c.getDataNascimento());
		cliente.setDataUltimoLogin(LocalDate.now());
		if (cliente.getSenha() == null)
			cliente.setSenha(c.getSenha());
		repo.save(cliente);
	}
}
