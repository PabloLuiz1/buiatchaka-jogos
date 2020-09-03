package br.edu.fatec.buiatchaka.dominio.cliente;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Cupom extends FormaPagamentoAbstract {
	private String codigo;
	private double valor;
	@ManyToOne
	private Cliente cliente;
}
