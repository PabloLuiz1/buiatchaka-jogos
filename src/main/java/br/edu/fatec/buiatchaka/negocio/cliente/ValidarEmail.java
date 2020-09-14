package br.edu.fatec.buiatchaka.negocio.cliente;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.cliente.Cliente;
import br.edu.fatec.buiatchaka.negocio.AbstractValidator;

public class ValidarEmail extends AbstractValidator{
	private static final String EMAIL_PATTERN = 
	        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
	
	
	@Override
	public String validar(EntidadeDominio entidade) {
		mensagem = new StringBuilder();
		Cliente cliente = (Cliente) entidade;
		mensagem.append(validarEmail(cliente.getEmail()));
		return mensagem.toString();
	}
	
	public String validarEmail(String email) {
		Matcher matcher = pattern.matcher(email);
		if (email.trim().length() < 6 || !matcher.matches()) {
			return "Insira o e-mail corretamente. \n";
		}
		return "";
	}
}
