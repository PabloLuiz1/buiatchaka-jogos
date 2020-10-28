package br.edu.fatec.buiatchaka.web.service.pedido;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fatec.buiatchaka.dominio.pedido.ItemPedido;
import br.edu.fatec.buiatchaka.repository.pedido.ItemPedidoRepository;
import br.edu.fatec.buiatchaka.web.service.exceptions.ObjectNotFoundException;

@Service
public class ItemPedidoService {
	@Autowired
	private ItemPedidoRepository repo;
	
	public ItemPedido consultar (Long id) {
		Optional<ItemPedido> item = repo.findById(id);
		return item.orElseThrow(() -> new ObjectNotFoundException("Item não encontrado. Tipo: " + ItemPedido.class.getName()));
	}
}
