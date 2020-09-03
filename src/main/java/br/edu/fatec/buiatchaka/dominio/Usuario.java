package br.edu.fatec.buiatchaka.dominio;

import java.time.LocalDate;

import java.util.List;

import br.edu.fatec.buiatchaka.dominio.enums.EnumGenero;

public class Usuario extends EntidadeDominio {
	private String nome;
	private EnumGenero genero;
	private String rg;
	private String cpf;
	private String email;
	private String senha;
	private LocalDate dataNascimento;
	private LocalDate dataUltimoLogin;
	private List<Transacao> transacoes;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public EnumGenero getGenero() {
		return genero;
	}

	public void setGenero(EnumGenero genero) {
		this.genero = genero;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public LocalDate getDataUltimoLogin() {
		return dataUltimoLogin;
	}

	public void setDataUltimoLogin(LocalDate dataUltimoLogin) {
		this.dataUltimoLogin = dataUltimoLogin;
	}
	
	public List<Transacao> getTransacoes() {
		return transacoes;
	}

	public void setTransacoes(List<Transacao> transacoes) {
		this.transacoes = transacoes;
	}

}
