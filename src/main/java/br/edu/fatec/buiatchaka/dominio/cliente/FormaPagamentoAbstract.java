package br.edu.fatec.buiatchaka.dominio.cliente;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@MappedSuperclass
public abstract class FormaPagamentoAbstract extends EntidadeDominio{
	@Column(nullable = false)
	private LocalDate dataVencimento;
	@ManyToOne
	private Cliente cliente;
}
