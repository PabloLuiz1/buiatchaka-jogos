package br.edu.fatec.buiatchaka.web.service.produto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fatec.buiatchaka.dominio.produto.Categoria;
import br.edu.fatec.buiatchaka.repository.produto.CategoriaRepository;
import br.edu.fatec.buiatchaka.web.service.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria consultar(Long id) {
		Optional<Categoria> categoria = repo.findById(id);
		return categoria.orElseThrow(
				() -> new ObjectNotFoundException("Item não encontrado. Tipo: " + Categoria.class.getName()));
	}
	
	public void salvar(Categoria categoria) {
		repo.save(categoria);
	}
	
	public List<Categoria> listar(){
		return repo.findAll();
	}
	
}
