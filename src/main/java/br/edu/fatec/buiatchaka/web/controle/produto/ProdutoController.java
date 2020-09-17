package br.edu.fatec.buiatchaka.web.controle.produto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("admin/produtos")
public class ProdutoController {
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String raiz() {
		return "admin/produto/produtos";
	}
}
