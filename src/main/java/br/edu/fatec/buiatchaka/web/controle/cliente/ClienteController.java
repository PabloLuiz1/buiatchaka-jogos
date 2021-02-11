package br.edu.fatec.buiatchaka.web.controle.cliente;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fatec.buiatchaka.dominio.cliente.Cartao;
import br.edu.fatec.buiatchaka.dominio.cliente.Cliente;
import br.edu.fatec.buiatchaka.dominio.cliente.Endereco;
import br.edu.fatec.buiatchaka.web.service.cliente.CartaoService;
import br.edu.fatec.buiatchaka.web.service.cliente.ClienteService;
import br.edu.fatec.buiatchaka.web.service.cliente.EnderecoService;
import br.edu.fatec.buiatchaka.web.service.exceptions.ObjectNotFoundException;

@Controller
public class ClienteController {
	@Autowired
	private ClienteService service;
	
	@Autowired
	private EnderecoService enderecoService;
	
	@Autowired
	private CartaoService cartaoService;
	
	@RequestMapping(value = "cadastro", method = {RequestMethod.GET, RequestMethod.POST})
	public String signUp() {
		return "cadastro";
	}
	
	@RequestMapping(value = "ver-endereco", method = {RequestMethod.GET, RequestMethod.POST})
	public String editarEndereco() {
		return "ver-endereco";
	}
	
	@RequestMapping(value = "perfil", method = RequestMethod.GET)
	public ModelAndView perfilDados(HttpSession session) {
		Cliente cliente = (Cliente) session.getAttribute("cliente");
		ModelAndView mv;
		List<Endereco> enderecos = enderecoService.listar(cliente);
		List<Cartao> cartoes = cartaoService.listar(cliente);
		cliente.setEnderecos(enderecos);
		cliente.setCartoes(cartoes);
		mv = new ModelAndView("/perfil/index");
		return mv;
	}
	
	@RequestMapping(value = "perfil", method = RequestMethod.POST)
	public ModelAndView atualizarDados(@ModelAttribute Cliente cliente) {
		ModelAndView mv;
		service.salvar(cliente);
		mv = new ModelAndView("redirect:/perfil");
		return mv;
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
				mv = new ModelAndView("redirect:/");
			}
		} catch (ObjectNotFoundException e) {
			mv = new ModelAndView("login");
			mv.addObject("erro", "E-mail e/ou senha inválidos.");
		}
		
		return mv;
	}
	
	@RequestMapping(value = "perfil/atualizarSenha", method = RequestMethod.POST)
	public ModelAndView atualizarSenha(@RequestParam("senhaAtual") String senhaAtual, 
			@RequestParam("senhaNova") String senhaNova, @RequestParam("id") Long id, HttpSession session){
		ModelAndView mv;
		Cliente cliente = service.consultar(id);
		if (cliente.getSenha().equals(senhaAtual)) {
			cliente.setSenha(senhaNova);
		}
		service.salvar(cliente);
		mv = new ModelAndView("redirect:/perfil");
		return mv;
	}
}
