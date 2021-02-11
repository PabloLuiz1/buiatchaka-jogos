package br.edu.fatec.buiatchaka.web.controle.pedido;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fatec.buiatchaka.dominio.cliente.Cartao;
import br.edu.fatec.buiatchaka.dominio.cliente.Cliente;
import br.edu.fatec.buiatchaka.dominio.cliente.Cupom;
import br.edu.fatec.buiatchaka.dominio.cliente.Endereco;
import br.edu.fatec.buiatchaka.dominio.pedido.Carrinho;
import br.edu.fatec.buiatchaka.dominio.pedido.ItemCarrinho;
import br.edu.fatec.buiatchaka.dominio.pedido.Pedido;
import br.edu.fatec.buiatchaka.dominio.produto.ItemEstoque;
import br.edu.fatec.buiatchaka.sistema.logging.Log;
import br.edu.fatec.buiatchaka.web.service.cliente.CartaoService;
import br.edu.fatec.buiatchaka.web.service.cliente.EnderecoService;
import br.edu.fatec.buiatchaka.web.service.estoque.EstoqueService;
import br.edu.fatec.buiatchaka.web.service.pedido.CupomService;
import br.edu.fatec.buiatchaka.web.service.pedido.PedidoService;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {
	@Autowired
	private EstoqueService estoqueService;
	@Autowired
	private CupomService cupomService;
	@Autowired
	private EnderecoService enderecoService;
	@Autowired
	private CartaoService cartaoService;
	@Autowired
	private PedidoService pedidoService;
	private final Carrinho carrinho = new Carrinho();
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView carrinho () {
		ModelAndView mv;
		mv = new ModelAndView("cliente/carrinho", "carrinho", carrinho);
		return mv;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ModelAndView adicionar(@ModelAttribute("itemEstoque") ItemEstoque item) {
		ModelAndView mv;
		ItemEstoque itemEstoque = estoqueService.consultar(item.getId());
		ItemCarrinho itemCarrinho = new ItemCarrinho(itemEstoque, 1);
		carrinho.addItem(itemCarrinho);
		carrinho.retirarDoEstoque(itemCarrinho);
		estoqueService.salvar(itemCarrinho.getItem());
		mv = new ModelAndView("redirect:/carrinho", "carrinho", carrinho);
		return mv;
	}
	
	@RequestMapping(value = "/limparCarrinho", method = RequestMethod.GET)
	public ModelAndView limparCarrinho() {
		ModelAndView mv;
		carrinho.getItens().forEach(carrinho::devolverEstoque);
		for (ItemCarrinho item : carrinho.getItens()) {
			ItemEstoque i = item.getItem();
			estoqueService.salvar(i);
		}
		carrinho.limparItens();
		mv = new ModelAndView("redirect:/carrinho");
		return mv;
	}
	
	@RequestMapping(value = "/remover", method = RequestMethod.POST)
	public ModelAndView removerItem(@ModelAttribute("itemCarrinho") ItemCarrinho item) {
		ModelAndView mv;
		carrinho.removerItem(item);
		mv = new ModelAndView("redirect:/carrinho", "carrinho", carrinho);
		return mv;
	}
	
	@RequestMapping(value = "/adicionarCupom", method = RequestMethod.POST)
	public ModelAndView adicionarCupom(@ModelAttribute("cupom") Cupom cupom) {
		ModelAndView mv;
		carrinho.adicionarCupom(cupomService.consultar(cupom.getCodigo()));
		mv = new ModelAndView("redirect:/carrinho/pagamento", "carrinho", carrinho);
		return mv;
	}
	
	@RequestMapping(value = "/entrega", method = RequestMethod.GET)
	public ModelAndView entrega() {
		ModelAndView mv;
		mv = new ModelAndView("cliente/carrinho-entrega", "carrinho", carrinho);
		return mv;
	}
	
	@RequestMapping(value = "/entrega", method = RequestMethod.POST)
	public ModelAndView processarEntrega(@ModelAttribute("endereco") Endereco endereco) {
		Endereco e = enderecoService.consultar(endereco.getId());
		carrinho.setEndereco(e);
		ModelAndView mv;
		mv = new ModelAndView("cliente/carrinho-pagamento", "carrinho", carrinho);
		return mv;
	}
	
	@RequestMapping(value = "/pagamento", method = RequestMethod.GET)
	public ModelAndView carrinhoPagamento() {
		ModelAndView mv;
		mv = new ModelAndView("cliente/carrinho-pagamento", "carrinho", carrinho);
		return mv;
	}
	
	@RequestMapping(value = "/pagamento", method = RequestMethod.POST)
	public ModelAndView pagamento(@ModelAttribute("endereco") Endereco endereco, HttpSession session) {
		ModelAndView mv;
		carrinho.setEndereco(enderecoService.consultar(endereco.getId()));
		mv = new ModelAndView("redirect:/carrinho/pagamento", "carrinho", carrinho);
		return mv;
	}
	
	@RequestMapping(value = "/adicionarCartao", method = RequestMethod.POST)
	public ModelAndView adicionarCartao(@ModelAttribute("cartao") Cartao cartao) {
		ModelAndView mv;
		Cartao c = cartaoService.consultar(cartao.getId());
		carrinho.adicionarCartao(c, carrinho.getTotal());
		mv = new ModelAndView("redirect:/carrinho/pagamento", "carrinho", carrinho); 
		return mv;
	}
	
	@RequestMapping(value = "/adicionarDoisCartoes", method = RequestMethod.POST)
	public ModelAndView adicionarDoisCartoes(@RequestParam("cartaoUm") Long idCartaoUm, @RequestParam("valorCartaoUm") double valorCartaoUm,
											 @RequestParam("cartaoDois") Long idCartaoDois, @RequestParam("valorCartaoDois") double valorCartaoDois) {
		ModelAndView mv;
		Cartao cartaoUm = cartaoService.consultar(idCartaoUm);
		Cartao cartaoDois = cartaoService.consultar(idCartaoDois);
		carrinho.adicionarCartao(cartaoUm, valorCartaoUm);
		carrinho.adicionarCartao(cartaoDois, valorCartaoDois);
		mv = new ModelAndView("redirect:/carrinho/pagamento", "carrinho", carrinho);
		return mv;
	}
	
	@RequestMapping(value = "/revisar", method = RequestMethod.GET)
	public ModelAndView paginaRevisar() {
		ModelAndView mv;
		mv = new ModelAndView("cliente/carrinho-revisar", "carrinho", carrinho);
		return mv;
	}
	
	@RequestMapping(value = "/revisar", method = RequestMethod.POST)
	public ModelAndView revisar(@ModelAttribute("cartao") Cartao cartao) {
		ModelAndView mv;
//		carrinho.adicionarCartao(cartao);
		mv = new ModelAndView("redirect:/carrinho/revisar", "carrinho", carrinho);
		return mv;
	}
	
	@RequestMapping(value = "/pedido", method = RequestMethod.GET)
	public ModelAndView pedido(HttpSession session) {
		Cliente c = (Cliente) session.getAttribute("cliente");
		carrinho.setCliente(c);
		ModelAndView mv;
		Pedido pedido = new Pedido(carrinho);
		pedidoService.salvar(pedido);
		mv = new ModelAndView("redirect:/carrinho/concluido", "pedido", pedido);
		carrinho.resetarCarrinho();
		return mv;
	}
	
	@RequestMapping(value = "/concluido", method = RequestMethod.GET)
	public String carrinhoConcluido() {
		return "cliente/carrinho-concluido";
	}
	
	@RequestMapping(value = "/quantidade", method = RequestMethod.POST)
	public ModelAndView quantidade (@RequestParam("idItem") Long idItem, @RequestParam("quantidade") int quantidade) {
		ModelAndView mv;
		ItemEstoque itemEstoque = estoqueService.consultar(idItem);
		ItemCarrinho itemCarrinho = null;
		for (ItemCarrinho i : this.carrinho.getItens()) {
			if (i.getItem().getId() == idItem)
				itemCarrinho = i;
		}
		itemCarrinho.setItem(itemEstoque);
		itemCarrinho.setQuantidade(quantidade);
		carrinho.devolverEstoque(itemCarrinho);
		estoqueService.salvar(itemCarrinho.getItem());
		itemEstoque = estoqueService.consultar(idItem);
		itemCarrinho.setItem(itemEstoque);
		itemCarrinho.setQuantidade(quantidade);
		carrinho.retirarDoEstoque(itemCarrinho);
		estoqueService.salvar(itemCarrinho.getItem());
		mv = new ModelAndView("redirect:/carrinho");
		return mv;
	}
}
