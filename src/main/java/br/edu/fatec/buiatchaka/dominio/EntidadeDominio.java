package br.edu.fatec.buiatchaka.dominio;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@MappedSuperclass
public class EntidadeDominio {
	@Id
	@GeneratedValue
	private long id;
	private LocalDateTime dataCadastro;

}
