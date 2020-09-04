package br.edu.fatec.buiatchaka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.fatec.buiatchaka.dominio.cliente.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
}
