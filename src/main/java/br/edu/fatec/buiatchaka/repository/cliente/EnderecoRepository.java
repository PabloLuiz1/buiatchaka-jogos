package br.edu.fatec.buiatchaka.repository.cliente;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.fatec.buiatchaka.dominio.cliente.Cliente;
import br.edu.fatec.buiatchaka.dominio.cliente.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
	
	@Query("SELECT e FROM Endereco e WHERE ativo = true AND e.id = :id")
	public Optional<Endereco> findById(@Param("id") Long id);
	@Query("SELECT e FROM Endereco e WHERE ativo = true AND e.cliente = :cliente") 
	public List<Endereco> findAll(@Param("cliente") Cliente cliente);
	
	@Query("SELECT e FROM Endereco e WHERE ativo = true")
	@Override
	public List<Endereco> findAll();
}
