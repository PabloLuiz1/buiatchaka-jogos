package br.edu.fatec.buiatchaka.web.controle.cliente;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fatec.buiatchaka.dominio.cliente.Cliente;
import br.edu.fatec.buiatchaka.dominio.cliente.Endereco;
import br.edu.fatec.buiatchaka.web.service.cliente.EnderecoService;

@Controller
@RequestMapping(value = "perfil/enderecos")
public class EnderecoController {
	@Autowired
	private EnderecoService service;
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ModelAndView find (@PathVariable Long id) {
		ModelAndView mv;
		Endereco endereco = service.consultar(id);
		mv = new ModelAndView("perfil/ver-endereco", "endereco", endereco);
		return mv;
	}
	
	@RequestMapping(value ="{id}", method = RequestMethod.POST)
	public ModelAndView salvar(@ModelAttribute("endereco") Endereco endereco) {
		ModelAndView mv;
		service.salvar(endereco);
		mv = new ModelAndView("perfil/ver-endereco", "endereco", endereco);
		return mv;
	}
	
	@RequestMapping(value ="novo", method = RequestMethod.POST)
	public ModelAndView inserir(@ModelAttribute("endereco") Endereco endereco, HttpSession session) {
		ModelAndView mv;
		Cliente cliente = (Cliente) session.getAttribute("cliente");
		endereco.setCliente(cliente);
		endereco.setAtivo(true);
		service.salvar(endereco);
		mv = new ModelAndView("redirect:/perfil");
		return mv;
	}
	
	@RequestMapping(value = "excluir", method = RequestMethod.POST)
	public ModelAndView excluir(@RequestParam("endereco") String endereco) {
		ModelAndView mv;
		service.excluir(Long.valueOf(endereco));
		mv = new ModelAndView("redirect:/perfil");
		return mv;
	}
}
