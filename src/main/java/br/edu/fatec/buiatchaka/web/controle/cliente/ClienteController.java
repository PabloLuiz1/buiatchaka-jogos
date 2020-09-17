package br.edu.fatec.buiatchaka.web.controle.cliente;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ClienteController {
	
	@RequestMapping(value = "cadastro", method = {RequestMethod.GET, RequestMethod.POST})
	public String signUp() {
		return "cadastro";
	}
	
	@RequestMapping(value = "ver-endereco", method = {RequestMethod.GET, RequestMethod.POST})
	public String editarEndereco() {
		return "ver-endereco";
	}
	
	@RequestMapping(value = "perfil", method = {RequestMethod.GET, RequestMethod.POST})
	public String perfilDados() {
		return "perfil/index";
	}
	
	@RequestMapping(value = "clientes", method = {RequestMethod.GET, RequestMethod.POST})
	public String clientes() {
		return "admin/clientes";
	}
	
}
