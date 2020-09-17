package br.edu.fatec.buiatchaka.repository.cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.fatec.buiatchaka.dominio.cliente.Cartao;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long>{

}
