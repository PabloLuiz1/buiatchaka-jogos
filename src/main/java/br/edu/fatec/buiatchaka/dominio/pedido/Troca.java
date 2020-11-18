package br.edu.fatec.buiatchaka.dominio.pedido;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.enums.EnumStatusTroca;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class Troca extends EntidadeDominio {
	@OneToOne(cascade = {CascadeType.ALL})
	private ItemTroca item;
	private String motivo;
	private String mensagemCliente;
	private String mensagemAdmin;
	private double valor;
	@Column(nullable = false, columnDefinition = "boolean default FALSE")
	private boolean devolverEstoque = false;
	@Column(nullable = false, columnDefinition = "character varying default 'PENDENTE'")
	private String status = EnumStatusTroca.valueOf("PENDENTE").toString();

	public void atualizarValor() {
		this.valor += item.getItem().getItem().getProduto().getPrecoVenda();
	}

	public Troca() {
		this.setDataCadastro(LocalDate.now());
		this.setAtivo(true);
	}
}
