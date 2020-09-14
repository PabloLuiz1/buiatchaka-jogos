package br.edu.fatec.buiatchaka.negocio.cliente;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.cliente.Cliente;
import br.edu.fatec.buiatchaka.negocio.AbstractValidator;
import br.edu.fatec.buiatchaka.web.util.ValidadoraDeCampos;

public class ValidarRg extends AbstractValidator{

	@Override
	public String validar(EntidadeDominio entidade) {
		mensagem = new StringBuilder();
		Cliente cliente = (Cliente) entidade;
		mensagem.append(validarRg(cliente.getRg()));
		return mensagem.toString();
	}

	private String validarRg(String rg) {
		if (rg.trim().length() < 4 || !ValidadoraDeCampos.validarCampoEspecial(rg)) {
			return "Insira o RG corretamente. \n";
		}
		return "";
	}
}
