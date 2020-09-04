package br.edu.fatec.buiatchaka.command;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.fatec.buiatchaka.fachada.IFachada;

public abstract class AbstractCommand implements ICommand {
	@Autowired
	protected IFachada fachada;
}
