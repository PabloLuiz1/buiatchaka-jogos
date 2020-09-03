package br.edu.fatec.buiatchaka.dominio.enums;

public enum EnumTipoResidencia {
	CASA, 
	APARTAMENTO, 
	OUTRO;
	
	public String get(String tipoResidencia) {
		for (EnumTipoResidencia tipo : EnumTipoResidencia.values()) {
			if (tipo.toString().equalsIgnoreCase(tipoResidencia)) {
				return tipo.toString();
			}
		}
		return null;
	}
}
