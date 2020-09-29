package br.edu.fatec.buiatchaka.repository.produto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.fatec.buiatchaka.dominio.produto.ItemEstoque;

@Repository
public interface ItemEstoqueRepository extends JpaRepository<ItemEstoque, Long>{
	
	public List<ItemEstoque> findByDataCadastro(@Param("data_cadastro") LocalDate dataCadastro);
}
