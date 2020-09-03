package br.edu.fatec.buiatchaka.negocio.telefone;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.cliente.Telefone;
import br.edu.fatec.buiatchaka.dominio.enums.EnumTipoTelefone;
import br.edu.fatec.buiatchaka.negocio.AbstractValidator;
import br.edu.fatec.buiatchaka.web.util.ValidadoraDeCampos;

public class ValidarNumeroTelefone extends AbstractValidator{
	@Override
	public String validar(EntidadeDominio entidade) {
		mensagem = new StringBuilder();
		Telefone telefone = (Telefone) entidade;
		mensagem.append(validarNumero(telefone.getNumero()));
		return mensagem.toString();
	}

	
	private String validarNumero(String numero) {
		if (!ValidadoraDeCampos.validarCampoNumero(numero)) {
			System.out.println(numero);
			return "Insira o número de telefone corretamente. ";
		}
		return "";
	}
}
