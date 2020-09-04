package br.edu.fatec.buiatchaka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.fatec.buiatchaka.dominio.cliente.Telefone;

public interface TelefoneRepository extends JpaRepository<Telefone, Long>{

}