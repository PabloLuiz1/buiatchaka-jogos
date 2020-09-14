package br.edu.fatec.buiatchaka.dominio;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@MappedSuperclass
public class EntidadeDominio {
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false, columnDefinition = "date default 'now()'")
	private LocalDate dataCadastro;
	@Column(nullable = false, columnDefinition = "boolean default true")
	private boolean ativo;
}
