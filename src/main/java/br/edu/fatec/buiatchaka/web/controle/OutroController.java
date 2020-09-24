package br.edu.fatec.buiatchaka.web.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fatec.buiatchaka.dominio.produto.Produto;
import br.edu.fatec.buiatchaka.web.service.produto.ProdutoService;

@Controller
@RequestMapping("/")
public class OutroController {
	@Autowired
	ProdutoService service;
	
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = "ver-produto", method = RequestMethod.GET)
	public String produto() {
		return "ver-produto";
	}
	
	@RequestMapping(value = "produtos", method = RequestMethod.GET)
	public String produtos() {
		return "produtos";
	}
	
	@RequestMapping(value = "produto/{id}", method = RequestMethod.GET)
	public ModelAndView verProduto(@PathVariable Long id) {
		ModelAndView mv;
		Produto produto = service.consultar(id);
		mv = new ModelAndView("ver-produto", "produto", produto);
		return mv;
	}
	
	
}