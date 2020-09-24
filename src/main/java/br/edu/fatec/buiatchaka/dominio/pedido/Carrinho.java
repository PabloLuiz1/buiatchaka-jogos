package br.edu.fatec.buiatchaka.dominio.pedido;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.cliente.Cliente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Carrinho extends EntidadeDominio {
	private Cliente cliente;
	private ItemPedido itens;
}
