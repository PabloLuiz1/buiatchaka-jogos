package br.edu.fatec.buiatchaka.repository.pedido;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.fatec.buiatchaka.dominio.pedido.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long>{

}
