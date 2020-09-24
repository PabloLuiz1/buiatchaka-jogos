package br.edu.fatec.buiatchaka.web.service.cliente;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fatec.buiatchaka.dominio.cliente.Cliente;
import br.edu.fatec.buiatchaka.dominio.cliente.Endereco;
import br.edu.fatec.buiatchaka.repository.cliente.EnderecoRepository;
import br.edu.fatec.buiatchaka.web.service.exceptions.ObjectNotFoundException;

@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository repo;

	public Endereco consultar(Long id) {
		Optional<Endereco> endereco = repo.findById(id);
		return endereco.orElseThrow(
				() -> new ObjectNotFoundException("Item não encontrado. Tipo: " + Endereco.class.getName()));
	}
	
	public void salvar(Endereco endereco) {
		repo.save(endereco);
	}
	
	public List<Endereco> listar(Cliente cliente) {
		return repo.findAll();
	}

}
