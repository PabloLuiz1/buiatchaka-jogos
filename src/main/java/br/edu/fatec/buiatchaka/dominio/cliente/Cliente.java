package br.edu.fatec.buiatchaka.dominio.cliente;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
@Table(name = "cliente")
public class Cliente extends Usuario {
	@Column(nullable = false, columnDefinition = "integer default 0")
	private int qtdPedidos;
	@Column(nullable = false)
	@OneToMany(mappedBy = "cliente")
	private List<Endereco> enderecos;
	@Column(nullable = true)
	@OneToMany(mappedBy = "cliente")
	private List<Cartao> cartoes;
	@Column(nullable = true)
	@OneToMany(mappedBy = "cliente")
	private List<Cupom> cupons;
	@Column(nullable = false)
	private String telefone;
	@Override
	public String toString() {
		return "Cliente [qtdPedidos=" + qtdPedidos + ", dataUltimaCompra=" + ", enderecos="
				+ enderecos + ", cartoes=" + cartoes + ", cupons=" + cupons + ", telefone=" + telefone + "]";
	}
}
