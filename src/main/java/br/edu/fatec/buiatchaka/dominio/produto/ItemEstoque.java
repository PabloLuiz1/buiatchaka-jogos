package br.edu.fatec.buiatchaka.dominio.produto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class ItemEstoque extends EntidadeDominio {
	@OneToOne
	private Produto produto;
	@Column(nullable = false, columnDefinition = "integer default 0")
	private int quantidade;
}
