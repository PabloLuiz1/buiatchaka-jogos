package br.edu.fatec.buiatchaka.web.service.pedido;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fatec.buiatchaka.dominio.cliente.Cliente;
import br.edu.fatec.buiatchaka.dominio.pedido.Pedido;
import br.edu.fatec.buiatchaka.repository.pedido.PedidoRepository;
import br.edu.fatec.buiatchaka.web.service.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository repo;

	public List<Pedido> listar (Cliente cliente){ 
		return repo.findByCliente(cliente);
	}
	
	public List<Pedido> listar (){
		return repo.findAll();
	}
	
	public Pedido consultar (Long id) {
		Optional<Pedido> pedido = repo.findById(id);
		return pedido.orElseThrow(
				() -> new ObjectNotFoundException("Item não encontrado. Tipo: " + Pedido.class.getName()));
	}
	
	public void salvar (Pedido pedido) {
		repo.save(pedido);
	}
	
}