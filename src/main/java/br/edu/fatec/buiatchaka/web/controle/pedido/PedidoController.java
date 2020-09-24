package br.edu.fatec.buiatchaka.web.controle.pedido;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "perfil/pedidos")
public class PedidoController {
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView raiz () {
		return null;
	}

}
