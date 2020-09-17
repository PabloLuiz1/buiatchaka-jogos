package br.edu.fatec.buiatchaka.negocio.cartao;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.cliente.Cartao;
import br.edu.fatec.buiatchaka.negocio.AbstractValidator;

public class ValidarNumeroCartao extends AbstractValidator{

	@Override
	public String validar(EntidadeDominio entidade) {
		Cartao cartao = (Cartao) entidade;
		mensagem = new StringBuilder();
		mensagem.append(validarNumeroCartao(cartao.getNumero()));
		return mensagem.toString();
	}

	private String validarNumeroCartao(String numero) {
		if (numero.length() != 16) {
			return "Insira o número do cartão corretamente. \n";
		}
		return "";
	}

	
}
