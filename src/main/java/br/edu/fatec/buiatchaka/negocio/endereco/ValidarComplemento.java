package br.edu.fatec.buiatchaka.negocio.endereco;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.cliente.Endereco;
import br.edu.fatec.buiatchaka.negocio.AbstractValidator;
import br.edu.fatec.buiatchaka.web.util.ValidadoraDeCampos;

public class ValidarComplemento extends AbstractValidator {

	@Override
	public String validar(EntidadeDominio entidade) {
		Endereco endereco = (Endereco) entidade;
		mensagem = new StringBuilder();
		mensagem.append(validarComplemento(endereco.getComplemento()));
		return null;
	}
	
	private String validarComplemento(String complemento) {
		if (complemento.trim().length() > 0 && 
				!ValidadoraDeCampos.validarCampoTexto(complemento)) {
			return "Insira corretamente o complemento.";
		}
		return "";
	}
}
