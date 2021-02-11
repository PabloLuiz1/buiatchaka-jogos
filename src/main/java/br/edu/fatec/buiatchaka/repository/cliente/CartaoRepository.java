package br.edu.fatec.buiatchaka.repository.cliente;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.fatec.buiatchaka.dominio.cliente.Cartao;
import br.edu.fatec.buiatchaka.dominio.cliente.Cliente;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long>{

	@Query("SELECT c FROM Cartao c WHERE c.ativo = true AND c.id = :id")
	@Override
	public Optional<Cartao> findById(@Param("id") Long id);
	
	@Query("SELECT c FROM Cartao c WHERE c.ativo = true AND c.cliente = :cliente")
	public List<Cartao> findAll(@Param("cliente") Cliente cliente);
}
