package br.edu.fatec.buiatchaka.negocio.endereco;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.cliente.Endereco;
import br.edu.fatec.buiatchaka.negocio.AbstractValidator;
import br.edu.fatec.buiatchaka.web.util.ValidadoraDeCampos;

public class ValidarEstado extends AbstractValidator{

	@Override
	public String validar(EntidadeDominio entidade) {
		Endereco endereco = (Endereco) entidade;
		mensagem = new StringBuilder();
		mensagem.append(validarEstado(endereco.getEstado()));
		return mensagem.toString();
	}

	private String validarEstado(String estado) {
		if (estado.trim().length() != 2 || !ValidadoraDeCampos.validarCampoTexto(estado)) {
			return "Selecione o estado. \n";
		}
		return "";
	}
	
}
