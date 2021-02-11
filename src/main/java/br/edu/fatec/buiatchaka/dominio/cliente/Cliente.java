package br.edu.fatec.buiatchaka.dominio.cliente;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Endereco> enderecos;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Cartao> cartoes;
	@Column(nullable = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Cupom> cupons;
	@Column(nullable = false)
	private String telefone;
	@Override
	public String toString() {
		return "Cliente [qtdPedidos=" + qtdPedidos + ", dataUltimaCompra=" + ", enderecos="
				+ enderecos + ", cartoes=" + cartoes + ", cupons=" + cupons + ", telefone=" + telefone + "]";
	}
}
