package br.edu.fatec.buiatchaka.repository.cliente;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.fatec.buiatchaka.dominio.cliente.Cliente;
import br.edu.fatec.buiatchaka.dominio.cliente.Cupom;

@Repository
public interface CupomRepository extends JpaRepository<Cupom, Long>{
	@Query("SELECT c FROM Cupom c WHERE c.codigo = :codigo")
	public Optional<Cupom> findByCodigo (@Param("codigo") String codigo);
	
	@Query("SELECT c FROM Cupom c WHERE c.cliente = :cliente AND c.ativo = true")
	public List<Cupom> findByCliente (@Param("cliente") Cliente cliente);
}
