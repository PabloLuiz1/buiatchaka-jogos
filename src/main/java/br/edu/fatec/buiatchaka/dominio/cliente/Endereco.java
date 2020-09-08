package br.edu.fatec.buiatchaka.dominio.cliente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.enums.EnumTipoEndereco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter

@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "endereco", schema = "public")
public class Endereco extends EntidadeDominio {
	@Column(nullable = false, columnDefinition = "character varying(255)")
	private EnumTipoEndereco tipoEndereco;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private String logradouro;
	@Column(nullable = false)
	private String numero;
	@Column(nullable = false)
	private String cep;
	@Column(nullable = false)
	private String bairro;
	@Column(nullable = false)
	private String cidade;
	@Column(nullable = false)
	private String estado;
	@Column(nullable = true)
	private String complemento;
	@ManyToOne
	private Cliente cliente;
}
