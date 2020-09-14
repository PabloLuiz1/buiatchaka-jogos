package br.edu.fatec.buiatchaka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.fatec.buiatchaka.dominio.cliente.Bandeira;

@Repository
public interface BandeiraRepository extends JpaRepository<Bandeira, Long>{

}