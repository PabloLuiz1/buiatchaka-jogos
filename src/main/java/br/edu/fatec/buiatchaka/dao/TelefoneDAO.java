package br.edu.fatec.buiatchaka.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.cliente.Cliente;
import br.edu.fatec.buiatchaka.dominio.cliente.Telefone;
import br.edu.fatec.buiatchaka.web.util.Conexao;

public class TelefoneDAO implements IDao {
	PreparedStatement stm = null;
	private Connection connection = null;

	@Override
	public void salvar(EntidadeDominio entidade) {
		Telefone telefone = (Telefone) entidade;

		try {
			connection = Conexao.conectar();
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO telefone (ddd, numero, cliente_id)");
			sql.append("VALUES (?, ?, ?)");

			stm = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			stm.setString(1, telefone.getDdd());
			stm.setString(2, telefone.getNumero());
			stm.setLong(3, telefone.getCliente().getId());

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
	public void alterar(EntidadeDominio entidade) {
		Telefone telefone = (Telefone) entidade;

		try {
			connection = Conexao.conectar();
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE telefone SET ddd = ?, numero = ?" + "WHERE ativo = ? AND id = ?");

			stm = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			stm.setString(1, telefone.getDdd());
			stm.setString(2, telefone.getNumero());
			stm.setBoolean(3, true);
			stm.setLong(4, telefone.getId());

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
	public void excluir(EntidadeDominio entidade) {
		Telefone telefone = (Telefone) entidade;

		try {
			connection = Conexao.conectar();
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE telefone SET ativo = ? WHERE id = ?");

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
	public List<EntidadeDominio> listar(EntidadeDominio entidade) {
		List<EntidadeDominio> telefones;
		Telefone telefone = (Telefone) entidade;

		try {
			connection = Conexao.conectar();
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM telefone WHERE ativo = ? AND cliente_id = ?");

			stm = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			stm.setBoolean(1, true);
			stm.setLong(2, telefone.getCliente().getId());

			ResultSet rs = stm.executeQuery();
			telefones = new ArrayList<EntidadeDominio>();
			while (rs.next()) {
				Telefone tel = new Telefone();
				tel.setId(rs.getLong("id"));
				tel.setNumero(rs.getString("numero"));
				tel.setDataCadastro(rs.getDate("data_cadastro").toLocalDate());
				telefones.add(tel);
			}
			return telefones;
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
		return null;
	}

	@Override
	public EntidadeDominio consultar(EntidadeDominio entidade) {
		Telefone telefone = null;
		Cliente cliente = (Cliente) entidade;

		try {
			connection = Conexao.conectar();
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM telefone WHERE ativo = ? AND cliente_id = ?");

			stm = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			stm.setBoolean(1, true);
			stm.setLong(2, cliente.getId());

			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				telefone = new Telefone();
				telefone.setId(rs.getLong("id"));
				telefone.setNumero(rs.getString("numero"));
				telefone.setDataCadastro(rs.getDate("data_cadastro").toLocalDate());
				telefone.setCliente(cliente);
			}
			return telefone;
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
		return telefone;
	}

}
