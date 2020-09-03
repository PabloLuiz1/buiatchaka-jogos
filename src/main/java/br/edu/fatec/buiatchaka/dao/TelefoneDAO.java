package br.edu.fatec.buiatchaka.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.cliente.Cliente;
import br.edu.fatec.buiatchaka.dominio.cliente.Telefone;
import br.edu.fatec.buiatchaka.web.util.Conexao;

public class TelefoneDAO implements IDao{

	private Connection connection = null;
	
	@Override
	public void salvar(EntidadeDominio entidade) {
		PreparedStatement stm = null;
		Telefone telefone = (Telefone) entidade;

		try {
			connection = Conexao.conectar();
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append(
					"INSERT INTO tb_telefone_cliente (tel_cli_id, tel_tipo, tel_ddd, tel_numero)");
			sql.append("VALUES (?, ?, ?, ?)");

			stm = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			stm.setLong(1, telefone.getId());
			stm.setString(2, telefone.getTipo().toString());
			stm.setString(3, telefone.getDdd());
			stm.setString(4, telefone.getNumero());

			stm.executeUpdate();

			ResultSet rs = stm.getGeneratedKeys();
			int idTelefone = 0;
			if (rs.next())
				idTelefone = rs.getInt(1);
			telefone.setId(idTelefone);
			
			
			
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				stm.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void alterar(EntidadeDominio entidade) {
		PreparedStatement stm = null;
		Telefone telefone = (Telefone) entidade;

		try {
			connection = Conexao.conectar();
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append(
					"UPDATE tb_telefone_cliente SET tel_tipo = ?, tel_ddd = ?, tel_numero = ?"
					+ "WHERE tel_status = ? AND tel_id = ?");

			stm = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			stm.setString(1, telefone.getTipo().toString());
			stm.setString(2, telefone.getDdd());
			stm.setString(3, telefone.getNumero());
			stm.setBoolean(4, true);
			stm.setLong(5, telefone.getId());

			stm.executeUpdate();

			ResultSet rs = stm.getGeneratedKeys();
			int idTelefone = 0;
			if (rs.next())
				idTelefone = rs.getInt(1);
			telefone.setId(idTelefone);
			
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				stm.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		PreparedStatement stm = null;
		Telefone telefone = (Telefone) entidade;

		try {
			connection = Conexao.conectar();
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE tb_telefone_cliente SET tel_status = ? WHERE tel_cli_id = ?");

			stm = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			stm.setBoolean(1, false);
			stm.setLong(2, telefone.getId());

			stm.executeUpdate();

			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				stm.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}

}
