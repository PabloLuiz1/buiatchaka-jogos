package br.edu.fatec.buiatchaka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.fatec.buiatchaka.dominio.cliente.Cupom;

@Repository
public interface CupomRepository extends JpaRepository<Cupom, Long>{

}
