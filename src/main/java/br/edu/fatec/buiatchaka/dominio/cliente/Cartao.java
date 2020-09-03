package br.edu.fatec.buiatchaka.dominio.cliente;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter

@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Cartao extends FormaPagamentoAbstract {
	private String nome;
	private String numero;
	private String nomeImpresso;
	@OneToOne(cascade=CascadeType.ALL)
	private Bandeira bandeira;
	private String codigo;
	@ManyToOne
	private Cliente cliente;
}
