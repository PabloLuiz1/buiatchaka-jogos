package br.edu.fatec.buiatchaka.web.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.fatec.buiatchaka.repository.ClienteRepository;

@Controller
@RequestMapping(value = "/clientes")
public class ClienteController {
	@Autowired
	private ClienteRepository repo;

}
