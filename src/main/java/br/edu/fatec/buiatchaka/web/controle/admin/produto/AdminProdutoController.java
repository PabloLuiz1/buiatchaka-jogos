package br.edu.fatec.buiatchaka.web.controle.admin.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fatec.buiatchaka.dominio.produto.Categoria;
import br.edu.fatec.buiatchaka.dominio.produto.Produto;
import br.edu.fatec.buiatchaka.web.service.produto.CategoriaService;
import br.edu.fatec.buiatchaka.web.service.produto.ProdutoService;

@Controller
@RequestMapping("admin/produtos")
public class AdminProdutoController {
	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private CategoriaService categoriaService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView inicio () {
		ModelAndView mv;
		List<Produto> produtos = produtoService.listar();
		mv = new ModelAndView("admin/produtos/index", "produtos", produtos);
		return mv;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ModelAndView salvar (@ModelAttribute Produto produto) {
		ModelAndView mv;
		produtoService.salvar(produto);
		mv = new ModelAndView("admin/produtos");
		return mv;
	}

	@RequestMapping(value = "novo", method = RequestMethod.GET)
	public ModelAndView novoPagina () {
		ModelAndView mv;
		List<Categoria> categorias = categoriaService.listar();
		mv = new ModelAndView("admin/produtos/novo-produto", "categorias", categorias);
		return mv;
	}
	
	@RequestMapping(value = "novo", method = RequestMethod.POST)
	public ModelAndView novoProduto (@ModelAttribute Produto produto) {
		ModelAndView mv;
		double precoVenda = (produto.getPrecoCompra() * 2) + produto.getGrupoPrecificacao();
		produto.setPrecoVenda(precoVenda);
		produtoService.salvar(produto);
		mv = new ModelAndView("redirect:/admin/produtos");
		return mv;
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ModelAndView consultar (@PathVariable Long id) {
		ModelAndView mv;
		Produto produto = produtoService.consultar(id);
		List<Categoria> categorias = categoriaService.listar();
		mv = new ModelAndView("admin/produtos/ver-produto", "produto", produto);
		mv.addObject("categorias", categorias);
		return mv;
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.POST)
	public ModelAndView alterar (@ModelAttribute Produto produto) {
		ModelAndView mv;
		produtoService.salvar(produto);
		Produto p = produtoService.consultar(produto.getId());
		List<Categoria> categorias = categoriaService.listar();
		mv = new ModelAndView("admin/produtos/ver-produto", "produto", p);
		mv.addObject("categorias", categorias);
		return mv;
	}
	
	@RequestMapping(value = "ativar", method = RequestMethod.POST)
	public ModelAndView ativar (@ModelAttribute Produto produto) {
		ModelAndView mv;
		produtoService.ativar(produto);
		Produto p = produtoService.consultar(produto.getId());
		mv = new ModelAndView ("admin/produtos/ver-produto", "produto", p);
		return mv;
	}
}