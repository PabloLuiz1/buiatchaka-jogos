package br.edu.fatec.buiatchaka.web.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	static 
	{
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection conectar() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection
					("jdbc:postgresql://ec2-174-129-227-146.compute-1.amazonaws.com/deh9ut08774ce7",
							"fqrtzpvrsidylm?sslmode=require", "4ff05dd9e6a47e815cad8bbe62dd1cb6797e9f0ac4500164b1ddfe9b45210407");
		} catch (SQLException e) {
			throw new RuntimeException("Não foi possível exceutar o acesso", e);
		}
		return conn;
	}
	
	public static void desconectar(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
