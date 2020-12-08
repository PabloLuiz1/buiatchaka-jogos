package br.edu.fatec.buiatchaka.repository.produto;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.fatec.buiatchaka.dominio.produto.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	@Transactional
	@Modifying
	@Query("UPDATE Produto p SET p.ativo = :ativo WHERE p.id = :id")
	public void ativarProduto(@Param("ativo") boolean ativo, @Param("id") Long id);
}
