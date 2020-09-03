package br.edu.fatec.buiatchaka.negocio.endereco;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.cliente.Endereco;
import br.edu.fatec.buiatchaka.negocio.AbstractValidator;
import br.edu.fatec.buiatchaka.web.util.ValidadoraDeCampos;

public class ValidarNomeEndereco extends AbstractValidator {

	@Override
	public String validar(EntidadeDominio entidade) {
		Endereco endereco = (Endereco) entidade;
		mensagem = new StringBuilder();
		mensagem.append(validarNome(endereco.getNome()));
		return mensagem.toString();
	}
	
	private String validarNome(String nome) {
		if (nome.trim().equals("") || !ValidadoraDeCampos.validarCampoTexto(nome)) {
			return "Insira o nome do endereço. ";
		}
		return "";
	}

}
