package br.edu.fatec.buiatchaka.dominio;

import br.edu.fatec.buiatchaka.dominio.enums.EnumTipoTransacaoCliente;

public class Transacao extends EntidadeDominio {
	private EnumTipoTransacaoCliente tipo;
	private String campo;

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public EnumTipoTransacaoCliente getTipo() {
		return tipo;
	}

	public void setTipo(EnumTipoTransacaoCliente tipo) {
		this.tipo = tipo;
	}
}
