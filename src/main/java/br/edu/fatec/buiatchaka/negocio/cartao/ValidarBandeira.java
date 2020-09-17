package br.edu.fatec.buiatchaka.negocio.cartao;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.cliente.Cartao;
import br.edu.fatec.buiatchaka.negocio.AbstractValidator;

public class ValidarBandeira extends AbstractValidator{

	@Override
	public String validar(EntidadeDominio entidade) {
		Cartao cartao = (Cartao) entidade;
		mensagem = new StringBuilder();
		mensagem.append(validarBandeira(cartao.getBandeira()));
		return mensagem.toString();
	}

	
	public String validarBandeira (String bandeira) {
		if (bandeira.length() < 3) {
			return "Selecione a bandeira corretamente. \n";
		}
		return "";
	}
}
