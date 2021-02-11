package br.edu.fatec.buiatchaka.repository.pedido;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.fatec.buiatchaka.dominio.cliente.Cliente;
import br.edu.fatec.buiatchaka.dominio.pedido.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	
	@Query("SELECT p FROM Pedido p WHERE p.cliente = :cliente")
	public List<Pedido> findByCliente (@Param("cliente") Cliente cliente); 
	
	@Query("SELECT COUNT(item_pedido) AS quantidade, to_char(item_pedido.dataCadastro, 'Mon') AS Mes, "
			+ "categoria.descricao "
			+ "FROM ItemPedido item_pedido "
			+ "JOIN ItemEstoque item_estoque "
			+ "ON item_pedido.item = item_estoque "
			+ "JOIN Produto produto "
			+ "ON item_estoque.produto = produto " 
			+ "JOIN Categoria categoria "
			+ "ON produto.categoria = categoria "
			+ "WHERE item_pedido.dataCadastro BETWEEN :dataInicio AND :dataFim "
			+ "GROUP BY Mes, item_pedido.quantidade, categoria.descricao ")
	public List<Object> findAnaliseByData(@Param("dataInicio") LocalDate dataInicio, @Param("dataFim") LocalDate dataFim);
}
