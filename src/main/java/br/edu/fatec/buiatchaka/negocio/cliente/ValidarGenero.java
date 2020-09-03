package br.edu.fatec.buiatchaka.negocio.cliente;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.cliente.Cliente;
import br.edu.fatec.buiatchaka.dominio.enums.EnumGenero;
import br.edu.fatec.buiatchaka.negocio.AbstractValidator;

public class ValidarGenero extends AbstractValidator{

	@Override
	public String validar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		mensagem = new StringBuilder();
		mensagem.append(validarGenero(cliente.getGenero()));
		return mensagem.toString();
	}
	
	private String validarGenero(EnumGenero genero) {
		if (genero.equals(null)) {
			return "Escolha um gênero. ";
		}
		return "";
	}

}
