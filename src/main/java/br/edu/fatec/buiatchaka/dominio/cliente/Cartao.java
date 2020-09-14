package br.edu.fatec.buiatchaka.dominio.cliente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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
	@Column(nullable = false)
	private String bandeira;
	@Column(nullable = false)
	private String codigo;
	@Column(nullable = false)
	private String cpfTitular;
	@ManyToOne
	private Cliente cliente;
}
