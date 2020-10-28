package br.edu.fatec.buiatchaka.web.controle.admin;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fatec.buiatchaka.dominio.Admin;
import br.edu.fatec.buiatchaka.dominio.enums.EnumStatusPedido;
import br.edu.fatec.buiatchaka.dominio.pedido.Pedido;
import br.edu.fatec.buiatchaka.dominio.pedido.Troca;
import br.edu.fatec.buiatchaka.web.service.admin.AdminService;
import br.edu.fatec.buiatchaka.web.service.exceptions.ObjectNotFoundException;
import br.edu.fatec.buiatchaka.web.service.pedido.PedidoService;
import br.edu.fatec.buiatchaka.web.service.pedido.TrocaService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private PedidoService pedidoService;
	@Autowired
	private TrocaService trocaService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {
		return "admin/index";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String telaLogin() {
		return "admin/login";
	}
	 
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ModelAndView logar(@ModelAttribute Admin admin, HttpSession session) {
		ModelAndView mv = new ModelAndView("");
		try {
			Admin a = adminService.logar(admin);
			if (!(a.equals(null))) {
				session.setAttribute("admin", a);
				mv = new ModelAndView("redirect:/admin");
			}
		} catch (ObjectNotFoundException e) {
			mv = new ModelAndView("admin/login");
			mv.addObject("erro", "E-mail e/ou senha inválidos.");
		}
		return mv;
	}
	
	@RequestMapping(value = "perfil", method = RequestMethod.GET)
	public String clientes() {
		return "admin/perfil";
	}
	
	@RequestMapping(value = "usuarios", method = RequestMethod.GET)
	public String usuarios() {
		return "admin/usuarios";
	}
	
	@RequestMapping(value = "pedidos", method = RequestMethod.GET)
	public ModelAndView pedidos() {
		ModelAndView mv;
		List<Pedido> pedidos = pedidoService.listar();
		mv = new ModelAndView("admin/pedidos/index", "pedidos", pedidos);
		return mv;
	}
	
	@RequestMapping(value = "pedidos/{id}", method = RequestMethod.GET)
	public ModelAndView verPedido (@PathVariable Long id) {
		ModelAndView mv;
		Pedido pedido = pedidoService.consultar(id);
		List<Troca> trocas = trocaService.listar(pedido);
		mv = new ModelAndView("admin/pedidos/ver-pedido.html", "pedido", pedido);
		mv.addObject("trocas", trocas);
		return mv;
	}
	
	@RequestMapping(value = "pedidos/atualizarPedido", method = RequestMethod.POST)
	public ModelAndView atualizarPedido (@ModelAttribute("pedido") Pedido pedido) {
		ModelAndView mv;
		Pedido p = pedidoService.consultar(pedido.getId());
		p.setStatus(EnumStatusPedido.valueOf(pedido.getStatus()).toString());
		pedidoService.salvar(p);
		mv = new ModelAndView("redirect:/admin/pedidos");
		return mv;
	}
	
}
