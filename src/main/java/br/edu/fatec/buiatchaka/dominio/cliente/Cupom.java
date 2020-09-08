package br.edu.fatec.buiatchaka.dominio.cliente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "cupom", schema = "public")
public class Cupom extends FormaPagamentoAbstract {
	@Column(nullable = false)
	private String codigo;
	@Column(nullable = false)
	private double valor;
	@ManyToOne
	private Cliente cliente;
}
