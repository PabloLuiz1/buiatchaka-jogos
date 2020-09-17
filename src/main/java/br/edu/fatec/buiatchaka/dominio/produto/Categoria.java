package br.edu.fatec.buiatchaka.dominio.produto;

import javax.persistence.Column;
import javax.persistence.Entity;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;

@Entity
public class Categoria extends EntidadeDominio{
	@Column(nullable = false)
	private String descricao;
}
