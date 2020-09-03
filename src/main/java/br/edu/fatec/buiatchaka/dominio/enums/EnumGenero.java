package br.edu.fatec.buiatchaka.dominio.enums;

public enum EnumGenero {
	MASCULINO, 
	FEMININO;
	
	public String get(String genero) {
		for (EnumGenero tipo : EnumGenero.values()) {
			if (tipo.toString().equalsIgnoreCase(genero)) {
				return tipo.toString();
			}
		}
		return null;
	}
}	
