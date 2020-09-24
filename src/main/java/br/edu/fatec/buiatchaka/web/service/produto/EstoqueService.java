package br.edu.fatec.buiatchaka.web.service.produto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fatec.buiatchaka.dominio.produto.ItemEstoque;
import br.edu.fatec.buiatchaka.repository.produto.ItemEstoqueRepository;
import br.edu.fatec.buiatchaka.web.service.exceptions.ObjectNotFoundException;

@Service
public class EstoqueService {
	@Autowired
	ItemEstoqueRepository repo;
	
	public ItemEstoque consultar(Long id) {
		Optional<ItemEstoque> item = repo.findById(id);
		return item.orElseThrow(
				() -> new ObjectNotFoundException("Item não encontrado. Tipo: " + ItemEstoque.class.getName()));
	}
	
	public void salvar(ItemEstoque item) {
		repo.save(item);
	}
	
	public List<ItemEstoque> listar(){
		return repo.findAll();
	}
}
