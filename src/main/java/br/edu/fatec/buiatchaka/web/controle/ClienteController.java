package br.edu.fatec.buiatchaka.web.controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ClienteController {
	
	@RequestMapping(value = "sign-up", method = {RequestMethod.GET, RequestMethod.POST})
	public String signUp() {
		return "sign-up";
	}
	
	@RequestMapping(value = "sign-up-endereco", method = {RequestMethod.GET, RequestMethod.POST})
	public String signUpEndereco() {
		return "sign-up-endereco";
	}
	
	@RequestMapping(value = "perfil-dados", method = {RequestMethod.GET, RequestMethod.POST})
	public String perfilDados() {
		return "perfil-dados";
	}
}
