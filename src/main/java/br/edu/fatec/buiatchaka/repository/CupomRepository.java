package br.edu.fatec.buiatchaka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.fatec.buiatchaka.dominio.cliente.Cupom;

public interface CupomRepository extends JpaRepository<Cupom, Long>{

}
