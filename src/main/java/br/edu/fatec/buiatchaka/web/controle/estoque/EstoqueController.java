package br.edu.fatec.buiatchaka.web.controle.estoque;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fatec.buiatchaka.dominio.produto.ItemEstoque;
import br.edu.fatec.buiatchaka.web.service.estoque.EstoqueService;

@Controller
@RequestMapping("/")
public class EstoqueController {
	@Autowired
	EstoqueService service;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv;
		List<ItemEstoque> itens = service.listar();
		mv = new ModelAndView("index", "itens", itens);
		return mv;
	}
	
	@RequestMapping(value = "produtos", method = RequestMethod.GET)
	public ModelAndView itens() {
		ModelAndView mv;
		List<ItemEstoque> itens = service.listar();
		mv = new ModelAndView("index", "itens", itens);
		return mv;
	}
	
	@RequestMapping(value = "produto/{id}", method = RequestMethod.GET)
	public ModelAndView verProduto(@PathVariable Long id) {
		ModelAndView mv;
		ItemEstoque item = service.consultar(id);
		mv = new ModelAndView("ver-produto", "item", item);
		return mv;
	}
	
	
}