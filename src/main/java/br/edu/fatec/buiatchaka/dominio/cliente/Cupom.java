package br.edu.fatec.buiatchaka.dominio.cliente;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import br.edu.fatec.buiatchaka.dominio.pedido.Troca;
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
	@Column(nullable = false)
	private String codigo;
	@Column(nullable = false)
	private double valor;
	@OneToOne
	private Troca troca;
	
	public Cupom(Troca troca) {
		this.valor = troca.getValor();
		this.codigo = "TROCA" + troca.getId() + troca.getItem().getItem().getPedido().getCliente().getId();
		setCliente(troca.getItem().getItem().getPedido().getCliente());
		setTroca(troca);
		setDataCadastro(LocalDate.now());
		setDataVencimento(LocalDate.now().plusMonths(6));
		setAtivo(true);
	}
}