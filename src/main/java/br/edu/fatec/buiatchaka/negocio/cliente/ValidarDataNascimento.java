package br.edu.fatec.buiatchaka.negocio.cliente;

import java.time.LocalDate;
import java.time.Period;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.cliente.Cliente;
import br.edu.fatec.buiatchaka.negocio.AbstractValidator;

public class ValidarDataNascimento extends AbstractValidator{
	@Override
	public String validar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		mensagem = new StringBuilder();
		mensagem.append(validarDataNascimento(cliente.getDataNascimento()));
		return mensagem.toString();
	}
	
	private String validarDataNascimento(LocalDate dataNascimento) {
		if (dataNascimento.equals(null)) {
			return "Insira uma data de nascimento. ";
		}
		if (Period.between(dataNascimento, LocalDate.now()).getYears() < 18) {
			return "É necessário ter no mínimo 18 anos.";
		}
		if (dataNascimento.isAfter(LocalDate.now())) {
			return "Insira uma data de nascimento válida.";
		}
		return "";
	}
}
