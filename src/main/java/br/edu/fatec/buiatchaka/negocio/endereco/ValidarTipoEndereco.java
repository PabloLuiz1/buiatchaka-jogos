package br.edu.fatec.buiatchaka.negocio.endereco;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.cliente.Endereco;
import br.edu.fatec.buiatchaka.dominio.enums.EnumTipoEndereco;
import br.edu.fatec.buiatchaka.negocio.AbstractValidator;

public class ValidarTipoEndereco extends AbstractValidator{

	@Override
	public String validar(EntidadeDominio entidade) {
		Endereco endereco = (Endereco) entidade;
		mensagem = new StringBuilder();
		mensagem.append(validarTipoEndereco(endereco.getTipoEndereco()));
		return mensagem.toString();
	}
	
	public String validarTipoEndereco(EnumTipoEndereco tipo) {
		if (tipo.equals(null)) {
			return "Escolha um tipo de endereço. ";
		}
		return "";
	}
}
