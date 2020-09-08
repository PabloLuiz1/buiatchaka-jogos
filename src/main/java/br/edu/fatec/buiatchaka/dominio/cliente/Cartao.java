package br.edu.fatec.buiatchaka.dominio.cliente;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter

@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "cartao", schema = "public")
public class Cartao extends FormaPagamentoAbstract {
	@Column(nullable = false)
	private String numero;
	@Column(nullable = false)
	private String nomeImpresso;
	@OneToOne(cascade=CascadeType.ALL)
	private Bandeira bandeira;
	@Column(nullable = false)
	private String codigo;
	@ManyToOne
	private Cliente cliente;
}
