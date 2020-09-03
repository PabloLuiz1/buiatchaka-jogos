package br.edu.fatec.buiatchaka.dominio.cliente;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.edu.fatec.buiatchaka.dominio.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Cliente extends Usuario {
	private int qtdPedidos;
	private LocalDate dataUltimaCompra;
	@OneToMany(mappedBy = "cliente")
	private List<Endereco> enderecos;
	@OneToMany(mappedBy = "cliente")
	private List<Cartao> cartoes;
	@OneToMany(mappedBy = "cliente")
	private List<Cupom> cupons;
	@OneToOne(cascade=CascadeType.ALL)
	private Telefone telefone;
}
