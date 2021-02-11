package br.edu.fatec.buiatchaka.web.controle.cliente;

import java.time.LocalDate;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fatec.buiatchaka.dominio.cliente.Cartao;
import br.edu.fatec.buiatchaka.dominio.cliente.Cliente;
import br.edu.fatec.buiatchaka.web.service.cliente.CartaoService;

@Controller
@RequestMapping(value = "perfil/cartoes")
public class CartaoController {
	@Autowired
	private CartaoService service;

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ModelAndView find(@PathVariable Long id) {
		ModelAndView mv;
		Cartao cartao = service.consultar(id);
		mv = new ModelAndView("perfil/ver-cartao", "cartao", cartao);
		return mv;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.POST)
	public ModelAndView salvar(@ModelAttribute("cartao") Cartao cartao, @RequestParam("ano") String ano,
			@RequestParam("mes") String mes, HttpSession session) {
		ModelAndView mv;
		Cliente cliente = (Cliente) session.getAttribute("cliente");
		cartao.setCliente(cliente);
		cartao.setDataVencimento(LocalDate.of(Integer.parseInt(ano), Integer.parseInt(mes), 1));
		cartao.setAtivo(true);
		service.salvar(cartao);
		mv = new ModelAndView("perfil/ver-cartao", "cartao", cartao);
		return mv;
	}

	@RequestMapping(value = "novo", method = RequestMethod.POST)
	public ModelAndView inserir(@ModelAttribute("cartao") Cartao cartao, @RequestParam("ano") String ano,
			@RequestParam("mes") String mes, HttpSession session) {
		ModelAndView mv;
		Cliente cliente = (Cliente) session.getAttribute("cliente");
		cartao.setCliente(cliente);
		cartao.setDataVencimento(LocalDate.of(Integer.parseInt(ano), Integer.parseInt(mes), 1));
		cartao.setAtivo(true);
		service.salvar(cartao);
		mv = new ModelAndView("redirect:/perfil");
		return mv;
	}

	@RequestMapping(value = "excluir", method = RequestMethod.POST)
	public ModelAndView excluir(@ModelAttribute("cartao") Long cartao) {
		ModelAndView mv;
		Cartao c = service.consultar(cartao);
		c.setAtivo(false);
		service.salvar(c);
		mv = new ModelAndView("redirect:/perfil");
		return mv;
	}
}
