package br.edu.fatec.buiatchaka.web.service.admin;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fatec.buiatchaka.dominio.Admin;
import br.edu.fatec.buiatchaka.repository.admin.AdminRepository;
import br.edu.fatec.buiatchaka.web.service.exceptions.ObjectNotFoundException;

@Service
public class AdminService {
	@Autowired
	private AdminRepository repo;
	
	public Admin logar(Admin admin) {
		Optional<Admin> c = repo.findLogin(admin.getEmail(), admin.getSenha());
		return c.orElseThrow(
				() -> new ObjectNotFoundException("Item não encontrado. Tipo: " + Admin.class.getName()));
	}

}
