package br.edu.fatec.buiatchaka.repository.pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.fatec.buiatchaka.dominio.pedido.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{

}
