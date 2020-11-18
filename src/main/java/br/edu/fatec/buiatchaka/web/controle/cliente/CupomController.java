package br.edu.fatec.buiatchaka.web.controle.cliente;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fatec.buiatchaka.dominio.cliente.Cliente;
import br.edu.fatec.buiatchaka.dominio.cliente.Cupom;
import br.edu.fatec.buiatchaka.web.service.pedido.CupomService;

@Controller
@RequestMapping("perfil/cupons")
public class CupomController {
	@Autowired
	private CupomService service;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView cupons(HttpSession session){
		ModelAndView mv;
		Cliente cliente = (Cliente) session.getAttribute("cliente");
		List<Cupom> cupons = service.listar(cliente);
		mv = new ModelAndView("perfil/cupons", "cupons", cupons);
		return mv;
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ModelAndView cupom(@PathVariable Long id) {
		ModelAndView mv;
		Cupom cupom = service.consultar(id);
		mv = new ModelAndView("perfil/ver-cupom", "cupom", cupom);
		return mv;
	}
}
