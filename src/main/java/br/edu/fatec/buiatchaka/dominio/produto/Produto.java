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
	@Column(nullable = false, columnDefinition = "text")
	private String descricao;
	@Column(nullable = false)
	private String plataforma;
	@Column(nullable = false)
	private String genero;
	@Column(nullable = false)
	private int faixaEtaria;
	@Column(nullable = false)
	private int jogadoresOffline;
	@Column(nullable = false)
	private int jogadoresOnline;
	@Column(nullable = false)
	private String marca;
	@Column(nullable = false)
	private String tipoDeMidia;
	@Column(nullable = false)
	private double grupoPrecificacao;
	@Column(nullable = false)
	private String codigoDeBarras;
	private String razaoInativo;
	@Column(nullable = false)
	private double precoCompra;
	@Column(nullable = false)
	private double precoVenda;
	@ManyToOne
	private Categoria categoria;
}
