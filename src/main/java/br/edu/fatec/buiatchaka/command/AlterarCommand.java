package br.edu.fatec.buiatchaka.command;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.Resultado;
import br.edu.fatec.buiatchaka.fachada.IFachada;

public class AlterarCommand extends AbstractCommand{
	@Override
	public Resultado executar(EntidadeDominio entidade) {
		return fachada.alterar(entidade);
	}
	
}
