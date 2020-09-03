package br.edu.fatec.buiatchaka.dominio.cliente;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.enums.EnumTipoTelefone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Telefone extends EntidadeDominio {
	private EnumTipoTelefone tipo;
	private String ddd;
	private String numero;
	@ManyToOne
	private Cliente cliente;
}
