package br.edu.fatec.buiatchaka.repository.admin;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.fatec.buiatchaka.dominio.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
	
	@Query("SELECT a FROM Admin a WHERE a.email = :email AND a.senha = :senha")
	public Optional<Admin> findLogin (@Param("email") String email, @Param("senha") String senha);
}
