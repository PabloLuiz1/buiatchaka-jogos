package br.edu.fatec.buiatchaka.negocio.telefone;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.cliente.Telefone;
import br.edu.fatec.buiatchaka.negocio.AbstractValidator;
import br.edu.fatec.buiatchaka.web.util.ValidadoraDeCampos;

public class ValidarDdd extends AbstractValidator{

	@Override
	public String validar(EntidadeDominio entidade) {
		mensagem = new StringBuilder();
		Telefone telefone = (Telefone) entidade;
		mensagem.append(validarDdd(telefone.getDdd()));
		return mensagem.toString();
	}
	
	private String validarDdd(String ddd) {
		if (ddd.trim().length() != 2 || !ValidadoraDeCampos.validarCampoNumero(ddd)) {
			return "Insira o DDD corretamente. ";
		}
		return "";
	}
}
