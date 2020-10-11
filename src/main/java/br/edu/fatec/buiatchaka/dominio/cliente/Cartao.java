package br.edu.fatec.buiatchaka.dominio.cliente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

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
	@Column(nullable = false)
	private String numero;
	@Column(nullable = false)
	private String nomeTitular;
	@Column(nullable = false)
	private String bandeira;
	@Column(nullable = false)
	private String codigo;
	@Column(nullable = false)
	private String cpfTitular;
	@ManyToOne
	private Cliente cliente;
	
}
