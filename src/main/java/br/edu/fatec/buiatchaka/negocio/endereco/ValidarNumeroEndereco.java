package br.edu.fatec.buiatchaka.negocio.endereco;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.cliente.Endereco;
import br.edu.fatec.buiatchaka.negocio.AbstractValidator;
import br.edu.fatec.buiatchaka.web.util.ValidadoraDeCampos;

public class ValidarNumeroEndereco extends AbstractValidator {

	@Override
	public String validar(EntidadeDominio entidade) {
		Endereco endereco = (Endereco) entidade;
		mensagem = new StringBuilder();
		mensagem.append(validarNumero(endereco.getNumero()));
		return mensagem.toString();
	}
	
	private String validarNumero(String numero) {
		if (numero.trim().equals("") || !ValidadoraDeCampos.validarCampoEspecial(numero)) {
			return "Insira o número. ";
		}
		return "";
	}

}
