package br.edu.fatec.buiatchaka.dominio.produto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class Produto extends EntidadeDominio{
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private String descricao;
	@Column(nullable = false)
	private double precoCompra;
	@Column(nullable = false)
	private double precoVenda;
	@ManyToOne
	private Categoria categoria;
}
