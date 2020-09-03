package br.edu.fatec.buiatchaka.command;

import br.edu.fatec.buiatchaka.fachada.Fachada;
import br.edu.fatec.buiatchaka.fachada.IFachada;

public abstract class AbstractCommand implements ICommand {
	protected IFachada fachada = new Fachada();
}
