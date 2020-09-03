package br.edu.fatec.buiatchaka.negocio.telefone;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.cliente.Telefone;
import br.edu.fatec.buiatchaka.dominio.enums.EnumTipoTelefone;
import br.edu.fatec.buiatchaka.negocio.AbstractValidator;

public class ValidarTipoTelefone extends AbstractValidator{

	@Override
	public String validar(EntidadeDominio entidade) {
		Telefone telefone = (Telefone) entidade;
		mensagem = new StringBuilder();
		mensagem.append(validarTipo(telefone.getTipo()));
		return mensagem.toString();
	}

	private String validarTipo(EnumTipoTelefone tipo) {
		if (tipo.equals(null)) {
			return "Escolha um tipo de telefone. ";
		}
		return "";
	}
	
}
