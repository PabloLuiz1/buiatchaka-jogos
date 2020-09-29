package br.edu.fatec.buiatchaka.web.controle.pedido;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fatec.buiatchaka.dominio.cliente.Cliente;
import br.edu.fatec.buiatchaka.dominio.pedido.Pedido;
import br.edu.fatec.buiatchaka.web.service.pedido.PedidoService;

@Controller
public class PedidoController {
	@Autowired
	private PedidoService service;
	
	@RequestMapping(value = "perfil/pedidos", method = RequestMethod.GET)
	public ModelAndView raiz (HttpSession session) {
		ModelAndView mv;
		Cliente c = (Cliente) session.getAttribute("cliente");
		List<Pedido> pedidos = service.listar(c);
		mv = new ModelAndView("perfil/pedidos", "pedidos", pedidos); 
		return mv;
	}
	
	@RequestMapping(value = "pedidos/novo", method = RequestMethod.GET)
	public ModelAndView novoPedido() {
		ModelAndView mv;
		mv = new ModelAndView("/");
		return mv;
	}
}
