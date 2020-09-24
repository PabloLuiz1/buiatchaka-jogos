package br.edu.fatec.buiatchaka.web.controle.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fatec.buiatchaka.dominio.cliente.Cartao;
import br.edu.fatec.buiatchaka.web.service.cliente.CartaoService;

@Controller
@RequestMapping(value = "perfil/cartoes")
public class CartaoController {
	@Autowired
	private CartaoService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView find (@PathVariable Long id) {
		ModelAndView mv;
		Cartao cartao = service.consultar(id);
		mv = new ModelAndView("perfil/ver-cartao", "cartao", cartao);
		return mv;
	}
	
	@RequestMapping(value ="/{id}", method = RequestMethod.POST)
	public ModelAndView salvar(@ModelAttribute("endereco") Cartao cartao) {
		ModelAndView mv;
		service.salvar(cartao);
		mv = new ModelAndView("perfil/ver-cartao", "cartao", cartao);
		return mv;
	}
}
