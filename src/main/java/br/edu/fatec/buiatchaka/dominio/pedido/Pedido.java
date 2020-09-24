package br.edu.fatec.buiatchaka.dominio.pedido;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.cliente.Cliente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class Pedido extends EntidadeDominio {
	@ManyToOne
	private Cliente cliente;
	@Column(nullable = false, columnDefinition = "character varying (255) default 'PROCESSAMENTO'")
	private EnumStatusPedido status;
	private double valorFrete;
	private double valorTotal;
}
