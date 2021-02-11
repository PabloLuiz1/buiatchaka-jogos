package br.edu.fatec.buiatchaka.dominio;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Transacao extends EntidadeDominio {
	private String tipoTransacao;
}
