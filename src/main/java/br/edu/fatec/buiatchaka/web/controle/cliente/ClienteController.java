package br.edu.fatec.buiatchaka.web.controle.cliente;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fatec.buiatchaka.dominio.cliente.Cliente;
import br.edu.fatec.buiatchaka.sistema.logging.Log;
import br.edu.fatec.buiatchaka.web.service.cliente.ClienteService;
import br.edu.fatec.buiatchaka.web.service.exceptions.ObjectNotFoundException;

@Controller
public class ClienteController {
	@Autowired
	private ClienteService service;
	
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
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ModelAndView logar(@ModelAttribute Cliente cliente, HttpSession session) {
		ModelAndView mv = new ModelAndView("");
		try {
			Cliente c = service.logar(cliente);
			if (!(c.equals(null))) {
				session.setAttribute("cliente", c);
				Log.loggar("TESTANDO o cliente na SESSION: CPF: " + c.getCpf() + " - Primeiro endereço: " + c.getEnderecos().get(0).getNome());
				mv = new ModelAndView("redirect:/");
			}
		} catch (ObjectNotFoundException e) {
			mv = new ModelAndView("login");
			mv.addObject("erro", "E-mail e/ou senha inválidos.");
		}
		
		
		return mv;
	}
}
