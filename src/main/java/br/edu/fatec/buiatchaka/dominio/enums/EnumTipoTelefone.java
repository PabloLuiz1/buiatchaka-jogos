package br.edu.fatec.buiatchaka.dominio.enums;

public enum EnumTipoTelefone {
	RESIDENCIAL, 
	CELULAR, 
	RECADO;
	
	public String get(String tipoTelefone) {
		for (EnumTipoTelefone tipo : EnumTipoTelefone.values()) {
			if (tipo.toString().equalsIgnoreCase(tipoTelefone)) {
				return tipo.toString();
			}
		}
		return null;
	}
}
