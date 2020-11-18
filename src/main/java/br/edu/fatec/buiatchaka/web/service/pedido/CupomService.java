package br.edu.fatec.buiatchaka.web.service.pedido;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fatec.buiatchaka.dominio.cliente.Cliente;
import br.edu.fatec.buiatchaka.dominio.cliente.Cupom;
import br.edu.fatec.buiatchaka.repository.cliente.CupomRepository;
import br.edu.fatec.buiatchaka.web.service.exceptions.ObjectNotFoundException;

@Service
public class CupomService {
	@Autowired
	private CupomRepository repo;
	
	public Cupom consultar(String codigo){
		Optional<Cupom> cupom = repo.findByCodigo(codigo);
		return cupom.orElseThrow(
				() -> new ObjectNotFoundException("Item não encontrado. Tipo: " + Cupom.class.getName()));
	}
	
	public Cupom consultar(Long id) {
		Optional<Cupom> cupom = repo.findById(id); 
		return cupom.orElseThrow(
				() -> new ObjectNotFoundException("Item não encontrado. Tipo: " + Cupom.class.getName()));
	}
	
	public List<Cupom> listar(Cliente cliente){
		return repo.findByCliente(cliente);
	}
	
	public void salvar (Cupom cupom) {
		repo.save(cupom);
	}
}
