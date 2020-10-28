package br.edu.fatec.buiatchaka.web.controle.admin.troca;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fatec.buiatchaka.dominio.enums.EnumStatusPedido;
import br.edu.fatec.buiatchaka.dominio.pedido.EnumStatusTroca;
import br.edu.fatec.buiatchaka.dominio.pedido.Pedido;
import br.edu.fatec.buiatchaka.dominio.pedido.Troca;
import br.edu.fatec.buiatchaka.dominio.produto.ItemEstoque;
import br.edu.fatec.buiatchaka.web.service.estoque.EstoqueService;
import br.edu.fatec.buiatchaka.web.service.pedido.TrocaService;

@Controller
@RequestMapping("/admin/trocas")
public class AdminTrocaController {
	@Autowired
	private TrocaService service;
	@Autowired
	private EstoqueService estoqueService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView trocas() {
		ModelAndView mv;
		List<Troca> trocas = service.listar();
		mv = new ModelAndView("admin/trocas/index", "trocas", trocas);
		return mv;
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ModelAndView troca(@PathVariable("id") Long id) {
		ModelAndView mv;
		Troca troca = service.consultar(id);
		mv = new ModelAndView("admin/trocas/ver-troca", "troca", troca);
		return mv;
	}
	
	@RequestMapping(value = "atualizarTroca", method = RequestMethod.POST)
	public ModelAndView atualizarTroca(@ModelAttribute("troca") Troca troca, HttpServletRequest request) {
		ModelAndView mv;
		Troca t = service.consultar(troca.getId());
		t.setStatus(EnumStatusTroca.valueOf(troca.getStatus()).toString());
		t.setMensagemAdmin(troca.getMensagemAdmin());
		Pedido p = t.getItem().getItem().getPedido();
		if (troca.getStatus().equals(EnumStatusTroca.valueOf("APROVADA").toString())) {
			p.setStatus(EnumStatusPedido.valueOf("TROCA_AUTORIZADA").toString());
		}
		if (troca.getStatus().equals(EnumStatusTroca.valueOf("RECUSADA").toString())) {
			p.setStatus(EnumStatusPedido.valueOf("TROCA_NAO_AUTORIZADA").toString());
		}
		if (troca.getStatus().equals(EnumStatusTroca.valueOf("ITEM_RECEBIDO").toString())) {
			if (troca.isDevolverEstoque()) {
				ItemEstoque item = t.getItem().getItem().getItem();
				item.setQuantidade(item.getQuantidade() + t.getItem().getQuantidade());
				estoqueService.salvar(item);
			}
			p.setStatus(EnumStatusPedido.valueOf("EM_TROCA").toString());
		}
		if (troca.getStatus().equals(EnumStatusTroca.valueOf("CUPOM_GERADO").toString())) {
			p.setStatus(EnumStatusPedido.valueOf("TROCA_CONCLUIDA").toString());
		}
		service.salvar(t);
		String referer = request.getHeader("Referer");
		mv = new ModelAndView("redirect:" + referer);
		return mv;
	}
}
