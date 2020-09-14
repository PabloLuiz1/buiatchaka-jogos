package br.edu.fatec.buiatchaka.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.cliente.Cartao;
import br.edu.fatec.buiatchaka.dominio.cliente.Cliente;
import br.edu.fatec.buiatchaka.web.util.Conexao;

public class CartaoDAO implements IDao {
	PreparedStatement stm = null;
	private Connection connection = null;

	@Override
	public EntidadeDominio salvar(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		Cartao cartao = (Cartao) entidade;

		try {
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO cartao (numero, codigo, nome_impresso, cliente_id, bandeira_id)");
			sql.append("VALUES (?, ?, ?, ?, ?)");

			pst = connection.prepareStatement(sql.toString());

			pst.setString(1, cartao.getNumero());
			pst.setString(2, cartao.getCodigo());
			pst.setString(3, cartao.getNomeImpresso());
			pst.setLong(4, cartao.getCliente().getId());
			pst.setLong(5, cartao.getBandeira().getId());

			pst.executeUpdate();

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
				pst.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cartao;
	}

	@Override
	public void alterar(EntidadeDominio entidade) {
		Cartao cartao = (Cartao) entidade;

		try {
			connection = Conexao.conectar();
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE cartao SET numero = ?, codigo = ?, nome_impresso = ?, bandeira_id = ?"
					+ "WHERE ativo = ? AND id = ?");

			stm = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			stm.setString(1, cartao.getNumero());
			stm.setString(2, cartao.getCodigo());
			stm.setString(3, cartao.getNomeImpresso());
			stm.setLong(4, cartao.getBandeira().getId());
			stm.setBoolean(5, true);
			stm.setLong(6, cartao.getId());

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
		Cartao cartao = (Cartao) entidade;

		try {
			connection = Conexao.conectar();
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE cartao SET ativo = ? WHERE id = ?");

			stm = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			stm.setBoolean(1, false);
			stm.setLong(2, cartao.getId());

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
		List<EntidadeDominio> cartoes;
		Cartao cartao = (Cartao) entidade;

		try {
			connection = Conexao.conectar();
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM cartao WHERE ativo = ? AND cliente_id = ?");

			stm = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			stm.setBoolean(1, true);
			stm.setLong(2, cartao.getCliente().getId());

			ResultSet rs = stm.executeQuery();
			cartoes = new ArrayList<EntidadeDominio>();
			while (rs.next()) {
				Cartao car = new Cartao();
				car.setId(rs.getLong("id"));
				car.setNumero(rs.getString("numero"));
				car.setCodigo(rs.getString("codigo"));
				car.setNomeImpresso(rs.getString("nome_impresso"));
				cartoes.add(car);
			}
			return cartoes;
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
		Cartao cartao = null;
		Cliente cliente = (Cliente) entidade;

		try {
			connection = Conexao.conectar();
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM cartao WHERE ativo = ? AND cliente_id = ?");

			stm = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			stm.setBoolean(1, true);
			stm.setLong(2, cliente.getId());

			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				cartao = new Cartao();
				cartao.setId(rs.getLong("id"));
				cartao.setNumero(rs.getString("numero"));
				cartao.setCodigo(rs.getString("codigo"));
				cartao.setNomeImpresso(rs.getString("nome_impresso"));
				cartao.setCliente(cliente);
			}
			return cartao;
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
		return cartao;
	}

}
