package br.edu.fatec.buiatchaka.dominio.enums;

public enum EnumTipoLogradouro {
	RUA, 
	AVENIDA, 
	RODOVIA, 
	ESTRADA, 
	PRACA, 
	VIELA, 
	TRAVESSA;
	
	public String get(String tipoLogradouro) {
		for (EnumTipoLogradouro tipo : EnumTipoLogradouro.values()) {
			if (tipo.toString().equalsIgnoreCase(tipoLogradouro)) {
				return tipo.toString();
			}
		}
		return null;
	}
}
