package br.edu.fatec.buiatchaka.web.service.cliente;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fatec.buiatchaka.dominio.cliente.Cartao;
import br.edu.fatec.buiatchaka.dominio.cliente.Cliente;
import br.edu.fatec.buiatchaka.repository.cliente.CartaoRepository;
import br.edu.fatec.buiatchaka.web.service.exceptions.ObjectNotFoundException;

@Service
public class CartaoService {
	@Autowired
	private CartaoRepository repo;
	
	public Cartao consultar(Long id) {
		Optional<Cartao> cartao = repo.findById(id);
		return cartao.orElseThrow(
				() -> new ObjectNotFoundException("Item não encontrado. Tipo: " + Cartao.class.getName()));
	}
	
	public void salvar(Cartao cartao) {
		repo.save(cartao);
	}
	
	public List<Cartao> listar(Cliente cliente) {
		return repo.findAll(cliente);
	}
}
