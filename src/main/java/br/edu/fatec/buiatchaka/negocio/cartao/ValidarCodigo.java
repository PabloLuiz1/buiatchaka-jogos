package br.edu.fatec.buiatchaka.negocio.cartao;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.cliente.Cartao;
import br.edu.fatec.buiatchaka.negocio.AbstractValidator;

public class ValidarCodigo extends AbstractValidator{

	@Override
	public String validar(EntidadeDominio entidade) {
		Cartao cartao = (Cartao) entidade;
		mensagem = new StringBuilder();
		mensagem.append(validarCodigo(cartao.getCodigo()));
		return mensagem.toString();
	}

	public String validarCodigo (String codigo) {
		if (codigo.length() != 3) {
			return "Insira o código de segurança corretamente. \n";
		}
		return "";
	}
}
