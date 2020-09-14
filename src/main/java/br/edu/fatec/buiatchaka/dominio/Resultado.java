package br.edu.fatec.buiatchaka.dominio;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

public class Resultado {
	private String msg;
	private List<EntidadeDominio> entidades;

	public List<EntidadeDominio> addEntidades(EntidadeDominio entidade) {
		if (entidades == null) {
			entidades = new ArrayList<EntidadeDominio>();
			entidades.add(entidade);
		} else {
			entidades.add(entidade);
		}
		
		return entidades;
	}
}
