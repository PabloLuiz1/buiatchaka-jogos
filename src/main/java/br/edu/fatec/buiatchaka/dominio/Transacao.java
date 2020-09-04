package br.edu.fatec.buiatchaka.dominio;

import javax.persistence.ManyToOne;

import br.edu.fatec.buiatchaka.dominio.enums.EnumTipoTransacaoCliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

public class Transacao extends EntidadeDominio {
	private EnumTipoTransacaoCliente tipo;
	private String campo;
	@ManyToOne
	private Usuario usuario;
}
