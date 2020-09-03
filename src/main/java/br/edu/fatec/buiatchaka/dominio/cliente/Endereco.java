package br.edu.fatec.buiatchaka.dominio.cliente;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

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
public class Endereco extends EntidadeDominio {
	private EnumTipoEndereco tipoEndereco;
	private String nome;
	private String logradouro;
	private String numero;
	private String cep;
	private String bairro;
	private String cidade;
	private String estado;
	private String complemento;
	private String pais;
	private String observacoes;
	@ManyToOne
	private Cliente cliente;
}
