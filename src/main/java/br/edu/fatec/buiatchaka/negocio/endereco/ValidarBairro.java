package br.edu.fatec.buiatchaka.negocio.endereco;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.cliente.Endereco;
import br.edu.fatec.buiatchaka.negocio.AbstractValidator;
import br.edu.fatec.buiatchaka.web.util.ValidadoraDeCampos;

public class ValidarBairro extends AbstractValidator{

	@Override
	public String validar(EntidadeDominio entidade) {
		Endereco endereco = (Endereco) entidade;
		mensagem = new StringBuilder();
		mensagem.append(validarBairro(endereco.getBairro()));
		return mensagem.toString();
	}
	
	private String validarBairro(String bairro) {
		if (bairro.trim().length() < 4 || !ValidadoraDeCampos.validarCampoTexto(bairro)) {
			return "Insira o bairro corretamente. \n";
		}
		return "";
	}

}
