package br.edu.fatec.buiatchaka.dominio.cliente;

import javax.persistence.Entity;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Bandeira extends EntidadeDominio{
	private String nome;
}
