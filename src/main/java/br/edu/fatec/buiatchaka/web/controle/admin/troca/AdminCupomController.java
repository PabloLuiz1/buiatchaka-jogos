package br.edu.fatec.buiatchaka.web.controle.admin.troca;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fatec.buiatchaka.dominio.cliente.Cupom;
import br.edu.fatec.buiatchaka.dominio.enums.EnumStatusPedido;
import br.edu.fatec.buiatchaka.dominio.enums.EnumStatusTroca;
import br.edu.fatec.buiatchaka.dominio.pedido.Pedido;
import br.edu.fatec.buiatchaka.dominio.pedido.Troca;
import br.edu.fatec.buiatchaka.web.service.pedido.CupomService;
import br.edu.fatec.buiatchaka.web.service.pedido.PedidoService;
import br.edu.fatec.buiatchaka.web.service.pedido.TrocaService;

@Controller
@RequestMapping("/admin/cupons")
public class AdminCupomController {
	@Autowired
	private CupomService cupomService;
	@Autowired
	private TrocaService trocaService;
	@Autowired
	private PedidoService pedidoService;
	
	@RequestMapping(value = "novo", method = RequestMethod.POST)
	public ModelAndView novoCupom (@ModelAttribute("cupom") Cupom cupom, @RequestParam("idTroca") Long idTroca, HttpServletRequest request) {
		ModelAndView mv;
		Troca troca = trocaService.consultar(idTroca);
		Pedido pedido = troca.getItem().getItem().getPedido();
		cupom.setTroca(troca);
		cupom.setCliente(troca.getItem().getItem().getPedido().getCliente());
		cupomService.salvar(cupom);
		troca.setStatus(EnumStatusTroca.valueOf("CUPOM_GERADO").toString());
		trocaService.salvar(troca);
		pedido.setStatus(EnumStatusPedido.valueOf("ENTREGUE").toString());
		pedidoService.salvar(pedido);
		String referer = request.getHeader("Referer");
		mv = new ModelAndView("redirect:" + referer);
		return mv;
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView listar() {
		ModelAndView mv;
		List<Cupom> cupons = cupomService.listar();
		mv = new ModelAndView("admin/cupons/index", "cupons", cupons);
		return mv;
	}
}
