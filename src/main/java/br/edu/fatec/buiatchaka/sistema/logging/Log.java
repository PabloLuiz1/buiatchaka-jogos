package br.edu.fatec.buiatchaka.sistema.logging;

import java.time.LocalDateTime;

public class Log {
	
	public static void loggar(String mensagem) {
		System.out.println("LOG: [" + LocalDateTime.now() + "] " + mensagem);
	}
}
