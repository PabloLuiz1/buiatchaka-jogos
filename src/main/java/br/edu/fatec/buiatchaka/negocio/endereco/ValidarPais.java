package br.edu.fatec.buiatchaka.negocio.endereco;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.cliente.Endereco;
import br.edu.fatec.buiatchaka.negocio.AbstractValidator;
import br.edu.fatec.buiatchaka.web.util.ValidadoraDeCampos;

public class ValidarPais extends AbstractValidator{

	@Override
	public String validar(EntidadeDominio entidade) {
		Endereco endereco = (Endereco) entidade;
		mensagem = new StringBuilder();
		mensagem.append(validarPais(endereco.getPais()));
		return mensagem.toString();
	}

	private String validarPais(String pais) {
		if (pais.trim().length() < 2 || !ValidadoraDeCampos.validarCampoTexto(pais)) {
			return "Selecione o país. ";
		}
		return "";
	}
	
}
