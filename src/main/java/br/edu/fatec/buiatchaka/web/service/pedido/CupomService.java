package br.edu.fatec.buiatchaka.web.service.pedido;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
