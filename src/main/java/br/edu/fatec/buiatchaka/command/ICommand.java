package br.edu.fatec.buiatchaka.command;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.Resultado;

public interface ICommand {
	public Resultado executar(EntidadeDominio entidade);
}
