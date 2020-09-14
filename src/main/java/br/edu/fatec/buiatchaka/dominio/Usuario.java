package br.edu.fatec.buiatchaka.dominio;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import br.edu.fatec.buiatchaka.dominio.enums.EnumGenero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

@MappedSuperclass
public class Usuario extends EntidadeDominio {
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false, columnDefinition = "character varying(255)")
	private EnumGenero genero;
	@Column(nullable = false)
	private String rg;
	@Column(nullable = false)
	private String cpf;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String senha;
	@Column(nullable = false)
	private LocalDate dataNascimento;
	@Column(nullable = false, columnDefinition = "date default 'now()'")
	private LocalDate dataUltimoLogin;
}
