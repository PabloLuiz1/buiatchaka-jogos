package br.edu.fatec.buiatchaka.web.util;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Criptografia {
	
	public static String md5(String senha) {
		String retorno = "";
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
			retorno = hash.toString(16);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retorno;
	}

}
