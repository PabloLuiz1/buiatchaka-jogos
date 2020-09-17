package br.edu.fatec.buiatchaka.negocio.cliente;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.cliente.Cliente;
import br.edu.fatec.buiatchaka.negocio.AbstractValidator;


public class ValidarNome extends AbstractValidator {

	@Override
	public String validar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		mensagem = new StringBuilder();
		mensagem.append(validarNome(cliente.getNome()));
		return mensagem.toString();
	}
	
	private String validarNome(String nome) {
		if (nome.length() < 3) {
			return "Insira o seu nome completo corretamente. \n";
		}
		return "";
	}
}
