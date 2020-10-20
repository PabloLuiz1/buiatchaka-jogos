package br.edu.fatec.buiatchaka.web.controle.pedido;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping(value = "admin/pedidos", method = RequestMethod.GET)
	public ModelAndView adminPedidos () {
		ModelAndView mv;
		List<Pedido> pedidos = service.listar();
		mv = new ModelAndView("admin/pedidos", "pedidos", pedidos);
		return mv;
	}
	
	@RequestMapping(value = "admin/pedido/{id}", method = RequestMethod.GET)
	public ModelAndView adminVerPedido (@PathVariable Long id) {
		ModelAndView mv;
		Pedido pedido = service.consultar(id);
		mv = new ModelAndView("admin/ver-pedido", "pedido", pedido);
		return mv;
	}
	
	@RequestMapping(value = "admin/pedido/{id}", method = RequestMethod.POST)
	public ModelAndView adminAtualizarPedido (@ModelAttribute("pedido") Pedido pedido) {
		ModelAndView mv;
		service.salvar(pedido);
		mv = new ModelAndView("admin/ver-pedido", "pedido", pedido);
		return mv;
	}
	
}
