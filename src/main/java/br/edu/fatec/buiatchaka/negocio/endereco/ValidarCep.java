package br.edu.fatec.buiatchaka.negocio.endereco;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.cliente.Endereco;
import br.edu.fatec.buiatchaka.negocio.AbstractValidator;
import br.edu.fatec.buiatchaka.web.util.ValidadoraDeCampos;

public class ValidarCep extends AbstractValidator {

	@Override
	public String validar(EntidadeDominio entidade) {
		Endereco endereco = (Endereco) entidade;
		mensagem = new StringBuilder();
		mensagem.append(validarCep(endereco.getCep()));
		return mensagem.toString();
	}

	private String validarCep(String cep) {
		if (cep.trim().length() != 8) {
			return "Insira o CEP corretamente. \n";
		}
		return "";
	}
	
//	|| !ValidadoraDeCampos.validarCampoNumero(cep)
}
