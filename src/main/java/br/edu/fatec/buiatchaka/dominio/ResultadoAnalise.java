package br.edu.fatec.buiatchaka.dominio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

public class ResultadoAnalise {
	private int quantidade;
	private String mes;
	private String descricao;
}
