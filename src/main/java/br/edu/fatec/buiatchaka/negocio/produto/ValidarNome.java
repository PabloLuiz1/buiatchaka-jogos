package br.edu.fatec.buiatchaka.negocio.produto;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.produto.Produto;
import br.edu.fatec.buiatchaka.negocio.AbstractValidator;

public class ValidarNome extends AbstractValidator{

	@Override
	public String validar(EntidadeDominio entidade) {
		Produto produto = (Produto) entidade;
		return validarNome(produto.getNome());
	}
	
	public String validarNome(String nome) {
		if (nome.length() < 3 || nome.equals(null)) {
			return "Insira o nome do produto corretamente. \n";
		}
		return "";
	}
}
