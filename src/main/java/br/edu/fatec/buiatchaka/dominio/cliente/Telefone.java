package br.edu.fatec.buiatchaka.dominio.cliente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "telefone", schema = "public")
public class Telefone extends EntidadeDominio {
	@Column(nullable = false)
	private String ddd;
	@Column(nullable = false)
	private String numero;
	@ManyToOne
	private Cliente cliente;
}
