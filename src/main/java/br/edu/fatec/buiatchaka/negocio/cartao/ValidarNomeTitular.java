package br.edu.fatec.buiatchaka.negocio.cartao;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.cliente.Cartao;
import br.edu.fatec.buiatchaka.negocio.AbstractValidator;

public class ValidarNomeTitular extends AbstractValidator{

	@Override
	public String validar(EntidadeDominio entidade) {
		Cartao cartao = (Cartao) entidade;
		mensagem = new StringBuilder();
		mensagem.append(validarNomeImpresso(cartao.getNomeTitular()));
		return mensagem.toString();
	}

	public String validarNomeImpresso(String nomeImpresso) {
		if (nomeImpresso.length() < 5) {
			return "Insira o nome do titular (assim como impresso no cartão) corretamente. \n";
		}
		return "";
	}
	
}
