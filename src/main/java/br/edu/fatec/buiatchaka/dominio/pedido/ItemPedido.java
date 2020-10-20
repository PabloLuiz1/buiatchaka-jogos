package br.edu.fatec.buiatchaka.dominio.pedido;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.produto.ItemEstoque;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class ItemPedido extends EntidadeDominio {
	@ManyToOne
	private ItemEstoque item;
	@Column(nullable = false, columnDefinition = "integer default 1")
	private int quantidade;
	@OneToOne
	private Pedido pedido;
}
