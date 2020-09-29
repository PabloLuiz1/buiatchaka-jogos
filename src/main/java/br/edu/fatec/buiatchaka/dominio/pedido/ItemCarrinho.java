package br.edu.fatec.buiatchaka.dominio.pedido;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.produto.ItemEstoque;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ItemCarrinho extends EntidadeDominio {
	private ItemEstoque item;
	private int quantidade;
	private double subtotal;
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
		setSubtotal();
	}
	
	public void setSubtotal() {
		this.subtotal = this.quantidade * this.item.getProduto().getPrecoVenda();
	}
}
