package br.edu.fatec.buiatchaka.web.controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PedidoController {
	@RequestMapping("pedido")
	public String checkout() {
		return "checkout";
	}
	
	@RequestMapping("teste")
	public String enderecoCobranca() {
		return "checkout2";
	}
}
