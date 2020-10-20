package br.edu.fatec.buiatchaka.dominio.cliente;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

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
	@Column(nullable = false, columnDefinition = "integer default 0")
	private int qtdPedidos;
	@OneToMany(mappedBy = "cliente")
	private List<Endereco> enderecos;
	@OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
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
