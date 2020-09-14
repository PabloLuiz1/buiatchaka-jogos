package br.edu.fatec.buiatchaka.negocio.endereco;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.cliente.Endereco;
import br.edu.fatec.buiatchaka.negocio.AbstractValidator;
import br.edu.fatec.buiatchaka.web.util.ValidadoraDeCampos;

public class ValidarLogradouro extends AbstractValidator {

	@Override
	public String validar(EntidadeDominio entidade) {
		Endereco endereco = (Endereco) entidade;
		mensagem = new StringBuilder();
		mensagem.append(validarLogradouro(endereco.getLogradouro()));
		return mensagem.toString();
	}

	private String validarLogradouro(String logradouro) {
		if (logradouro.trim().equals("") || !ValidadoraDeCampos.validarCampoTexto(logradouro)) {
			return "Insira o logradouro. \n";
		}
		return "";
	}
}
