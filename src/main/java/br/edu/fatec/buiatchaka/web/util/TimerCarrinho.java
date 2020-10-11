package br.edu.fatec.buiatchaka.web.util;

import java.util.TimerTask;

import br.edu.fatec.buiatchaka.dominio.pedido.Carrinho;

public class TimerCarrinho extends TimerTask{
	private Carrinho carrinho;
	
	public TimerCarrinho (Carrinho carrinho) {
		this.carrinho = carrinho;
	}
	
	@Override
	public void run() {
		this.carrinho.resetarCarrinho();
//		Log.loggar("Testando o timer do carrinho.");
	}

}
