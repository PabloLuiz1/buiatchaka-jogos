package br.edu.fatec.buiatchaka.negocio.produto;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.produto.Produto;
import br.edu.fatec.buiatchaka.negocio.AbstractValidator;

public class ValidarDescricao extends AbstractValidator{

	@Override
	public String validar(EntidadeDominio entidade) {
		Produto produto = (Produto) entidade;
		return validarDescricao(produto.getDescricao());
	}
	
	public String validarDescricao(String descricao) {
		if (descricao.length() < 10 || descricao.equals(null)) {
			return "Insira a descrição do produto corretamente. \n";
		}
		return "";
	}
}
