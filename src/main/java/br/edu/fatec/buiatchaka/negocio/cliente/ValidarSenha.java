package br.edu.fatec.buiatchaka.negocio.cliente;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.cliente.Cliente;
import br.edu.fatec.buiatchaka.negocio.AbstractValidator;

public class ValidarSenha extends AbstractValidator{

	@Override
	public String validar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		mensagem = new StringBuilder();
		mensagem.append(validarSenha(cliente.getSenha()));
		return mensagem.toString();
	}

	private String validarSenha(String senha) {
		if (senha.length() < 8) {
			return "A senha precisa ter no mínimo 8 digitos.";
		}
		return "";
	}
}
