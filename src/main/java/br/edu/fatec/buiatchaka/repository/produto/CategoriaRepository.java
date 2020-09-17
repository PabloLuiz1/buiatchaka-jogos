package br.edu.fatec.buiatchaka.repository.produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.fatec.buiatchaka.dominio.produto.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
