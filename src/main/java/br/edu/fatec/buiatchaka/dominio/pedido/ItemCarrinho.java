package br.edu.fatec.buiatchaka.dominio.pedido;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.produto.ItemEstoque;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor

@Getter
@Setter

public class ItemCarrinho extends EntidadeDominio {
	private ItemEstoque item;
	private int quantidade = 1;
	private double subtotal;
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
		setSubtotal();
	}
	
	public void setSubtotal() {
		this.subtotal = this.quantidade * this.item.getProduto().getPrecoVenda();
	}
	
	public ItemCarrinho(ItemEstoque item, int quantidade) {
		this.item = item;
		this.quantidade = quantidade;
		setSubtotal();
	}
	
}
