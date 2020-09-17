package br.edu.fatec.buiatchaka.dao;

import java.sql.Connection;
import java.sql.Date;
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
	private PreparedStatement stm = null;
	private Connection connection = null;

	@Override
	public EntidadeDominio salvar(EntidadeDominio entidade) {
		Cartao cartao = (Cartao) entidade;

		try {
			connection = Conexao.conectar();
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO cartao (nome_impresso, numero, bandeira, codigo, cpf_titular, data_vencimento, cliente_id)");
			sql.append("VALUES (?, ?, ?, ?, ?, ?, ?)");

			stm = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			stm.setString(1, cartao.getNomeTitular());
			stm.setString(2, cartao.getNumero());
			stm.setString(3, cartao.getBandeira());
			stm.setString(4, cartao.getCodigo());
			stm.setString(5, cartao.getCpfTitular());
			stm.setDate(6, Date.valueOf(cartao.getDataVencimento()));
			stm.setLong(7, cartao.getCliente().getId());

			stm.executeUpdate();

			ResultSet rs = stm.getGeneratedKeys();
			long idCartao = 0;
			if (rs.next())
				idCartao = rs.getInt(1);
			cartao.setId(idCartao);
			
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
		return cartao;
	}

	@Override
	public void alterar(EntidadeDominio entidade) {
		Cartao cartao = (Cartao) entidade;

		try {
			connection = Conexao.conectar();
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE cartao SET nome_impresso = ?, numero = ?, bandeira = ?, codigo = ?, cpf_titular = ?, data_vencimento = ?"
					+ "WHERE ativo = ? AND id = ?");

			stm = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			stm.setString(1, cartao.getNomeTitular());
			stm.setString(2, cartao.getNumero());
			stm.setString(3, cartao.getBandeira());
			stm.setString(4, cartao.getCodigo());
			stm.setString(5, cartao.getCpfTitular());
			stm.setDate(6, Date.valueOf(cartao.getDataVencimento()));

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
			cartoes = new ArrayList<EntidadeDominio>();
			while (rs.next()) {
				Cartao car = new Cartao();
//				nome_impresso, numero, bandeira, codigo, cpf_titular, data_vencimento, cliente_id
				car.setId(rs.getLong("id"));
				car.setNomeTitular(rs.getString("nome_titular"));
				car.setNumero(rs.getString("numero"));
				car.setBandeira(rs.getString("bandeira"));
				car.setCodigo(rs.getString("codigo"));
				car.setCpfTitular(rs.getString("cpf_titular"));
				car.setDataVencimento((rs.getDate(("data_vencimento")).toLocalDate()));
				car.setCliente(cliente);

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
				cartao.setNomeTitular(rs.getString("nome_titular"));
				cartao.setNumero(rs.getString("numero"));
				cartao.setBandeira(rs.getString("bandeira"));
				cartao.setCodigo(rs.getString("codigo"));
				cartao.setCpfTitular(rs.getString("cpf_titular"));
				cartao.setDataVencimento((rs.getDate(("data_vencimento")).toLocalDate()));
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
