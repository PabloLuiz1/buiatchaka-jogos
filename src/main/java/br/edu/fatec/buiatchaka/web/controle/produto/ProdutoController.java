package br.edu.fatec.buiatchaka.web.controle.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fatec.buiatchaka.dominio.produto.Produto;
import br.edu.fatec.buiatchaka.web.service.produto.ProdutoService;

@Controller
@RequestMapping("admin/produtos")
public class ProdutoController {
	
	@Autowired
	ProdutoService service;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView listar() {
		ModelAndView mv;
		List<Produto> produtos = service.listar();
		mv = new ModelAndView("admin/produtos/index", "produtos", produtos);
		return mv;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ModelAndView inserir(@ModelAttribute("produto") Produto produto) {
		ModelAndView mv;
		service.salvar(produto);
		mv = new ModelAndView("admin/produtos", "produtos", produto);
		return mv;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView find (@PathVariable Long id) {
		ModelAndView mv;
		Produto produto = service.consultar(id);
		mv = new ModelAndView("admin/produtos/ver-produto", "produto", produto);
		return mv;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public ModelAndView editar (@ModelAttribute("produto") Produto produto) {
		ModelAndView mv;
		service.salvar(produto);
		mv = new ModelAndView("admin/produtos/produtos", "produto", produto);
		return mv;
	}
	
}
