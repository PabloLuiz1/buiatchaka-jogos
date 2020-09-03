package br.edu.fatec.buiatchaka.negocio.endereco;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.cliente.Endereco;
import br.edu.fatec.buiatchaka.negocio.AbstractValidator;
import br.edu.fatec.buiatchaka.web.util.ValidadoraDeCampos;

public class ValidarCidade extends AbstractValidator{

	@Override
	public String validar(EntidadeDominio entidade) {
		Endereco endereco = (Endereco) entidade;
		mensagem = new StringBuilder();
		mensagem.append(validarCidade(endereco.getCidade()));
		return mensagem.toString();
	}
	
	private String validarCidade(String cidade) {
		if (cidade.trim().length() < 4 || !ValidadoraDeCampos.validarCampoTexto(cidade)) {
			return "Insira a cidade corretamente. ";
		}
		return "";
	}

}
