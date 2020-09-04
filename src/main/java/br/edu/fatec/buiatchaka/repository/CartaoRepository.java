package br.edu.fatec.buiatchaka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.fatec.buiatchaka.dominio.cliente.Cartao;

public interface CartaoRepository extends JpaRepository<Cartao, Long>{

}
