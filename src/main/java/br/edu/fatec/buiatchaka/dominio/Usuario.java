package br.edu.fatec.buiatchaka.dominio;

import java.time.LocalDate;
import java.util.List;

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

public class Usuario extends EntidadeDominio {
	private String nome;
	private EnumGenero genero;
	private String rg;
	private String cpf;
	private String email;
	private String senha;
	private LocalDate dataNascimento;
	private LocalDate dataUltimoLogin;
	@OneToMany(mappedBy = "usuario")
	private List<Transacao> transacoes;

}
