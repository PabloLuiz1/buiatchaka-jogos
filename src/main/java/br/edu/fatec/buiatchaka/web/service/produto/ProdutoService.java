package br.edu.fatec.buiatchaka.web.service.produto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fatec.buiatchaka.dominio.produto.Produto;
import br.edu.fatec.buiatchaka.repository.produto.ProdutoRepository;
import br.edu.fatec.buiatchaka.web.service.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	@Autowired
	ProdutoRepository repo;
	
	public Produto consultar(Long id) {
		Optional<Produto> produto = repo.findById(id);
		return produto.orElseThrow(
				() -> new ObjectNotFoundException("Item não encontrado. Tipo: " + Produto.class.getName()));
	}
	
	public void salvar(Produto produto) {
		repo.save(produto);
	}
	
	public List<Produto> listar(){
		return repo.findAll();
	}
}
