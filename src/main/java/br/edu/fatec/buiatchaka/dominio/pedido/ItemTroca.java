package br.edu.fatec.buiatchaka.dominio.pedido;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class ItemTroca extends EntidadeDominio {
	@OneToOne
	private ItemPedido item;
	private int quantidade;
}
