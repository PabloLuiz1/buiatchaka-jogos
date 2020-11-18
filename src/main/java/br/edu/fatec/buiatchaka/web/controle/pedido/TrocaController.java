package br.edu.fatec.buiatchaka.web.controle.pedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fatec.buiatchaka.dominio.enums.EnumMotivoTroca;
import br.edu.fatec.buiatchaka.dominio.enums.EnumStatusPedido;
import br.edu.fatec.buiatchaka.dominio.pedido.ItemPedido;
import br.edu.fatec.buiatchaka.dominio.pedido.ItemTroca;
import br.edu.fatec.buiatchaka.dominio.pedido.Troca;
import br.edu.fatec.buiatchaka.web.service.pedido.ItemPedidoService;
import br.edu.fatec.buiatchaka.web.service.pedido.PedidoService;
import br.edu.fatec.buiatchaka.web.service.pedido.TrocaService;

@Controller
@RequestMapping("/troca")
public class TrocaController {
	@Autowired
	private TrocaService trocaService;
	@Autowired
	private PedidoService pedidoService;
	@Autowired
	private ItemPedidoService itemPedidoService;

	@RequestMapping(value = "nova", method = RequestMethod.POST)
	public ModelAndView novaTroca(@ModelAttribute("item") ItemPedido item, @RequestParam("id_pedido") Long idPedido,
			@RequestParam("quantidade") int quantidade, @RequestParam("motivo") String motivo,
			@RequestParam("mensagemCliente") String mensagemCliente) {
		ModelAndView mv = null;
		item.setPedido(pedidoService.consultar(idPedido));
		item.getPedido().setStatus(EnumStatusPedido.valueOf("TROCA_SOLICITADA").toString());
		for (ItemPedido i : item.getPedido().getItens()) {
			if (i.getId() == item.getId())
				item = i;
		}
		ItemTroca itemTroca = new ItemTroca();
		itemTroca.setItem(item);
		itemTroca.setQuantidade(1);
		Troca troca = new Troca();
		troca.setItem(itemTroca);
		troca.setMotivo(EnumMotivoTroca.valueOf(motivo).toString());
		troca.setMensagemCliente(mensagemCliente);
		troca.atualizarValor();
		trocaService.salvar(troca);
		mv = new ModelAndView("redirect:/perfil/pedidos");
		return mv;
	}

	@RequestMapping(value = "solicitar/{id}", method = RequestMethod.GET)
	public ModelAndView solicitarTroca(@PathVariable("id") Long id) {
		ModelAndView mv;
		ItemPedido item = itemPedidoService.consultar(id);
		mv = new ModelAndView("perfil/solicitar-troca", "item", item);
		return mv;
	}
}
