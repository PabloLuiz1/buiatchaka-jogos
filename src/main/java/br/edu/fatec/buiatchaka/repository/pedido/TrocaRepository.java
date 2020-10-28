package br.edu.fatec.buiatchaka.repository.pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.fatec.buiatchaka.dominio.pedido.Troca;

@Repository
public interface TrocaRepository extends JpaRepository<Troca, Long>{
}
