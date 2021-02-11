package br.edu.fatec.buiatchaka.web.controle.admin.estoque;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fatec.buiatchaka.dominio.produto.Categoria;
import br.edu.fatec.buiatchaka.dominio.produto.ItemEstoque;
import br.edu.fatec.buiatchaka.web.service.estoque.EstoqueService;
import br.edu.fatec.buiatchaka.web.service.produto.CategoriaService;

@Controller
@RequestMapping("/admin/estoque")
public class AdminEstoqueController {
	@Autowired
	private EstoqueService service;
	@Autowired
	private CategoriaService categoriaService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv;
		List<ItemEstoque> itens = service.listar();
		mv = new ModelAndView("/admin/estoque/index", "estoque", itens);
		return mv;
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ModelAndView verProduto(@PathVariable Long id) {
		ModelAndView mv;
		ItemEstoque item = service.consultar(id);
		List<Categoria> categorias = categoriaService.listar();
		mv = new ModelAndView("/admin/estoque/ver-item-estoque", "item", item);
		mv.addObject("categorias", categorias);
		return mv;
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.POST)
	public ModelAndView atualizarQuantidade(@RequestParam("idItem") Long idItem, @RequestParam("quantidade") int quantidade) {
		ModelAndView mv;
		ItemEstoque item = service.consultar(idItem);
		item.setQuantidade(quantidade);
		service.salvar(item);
		item = service.consultar(idItem);
		List<Categoria> categorias = categoriaService.listar();
		mv = new ModelAndView("redirect:/admin/estoque/" + idItem, "item", item);
		mv.addObject("categorias", categorias);
		return mv;
	}
}