package br.edu.fatec.buiatchaka.web.util;

public class ValidadoraDeCampos {
	public static boolean validarCampoTexto(String campo) {
		return campo.matches("[A-Z a-z Çç]{" + campo.length() + "}");
	}
	
	public static boolean validarCampoNumero(String campo) {
		return campo.matches("[1-9]{" + campo.length() + "}");
	}
	
	public static boolean validarCampoEspecial(String campo) {
		return campo.matches("[A-Z a-z 1-9]{" + campo.length() + "}");
	}
}
