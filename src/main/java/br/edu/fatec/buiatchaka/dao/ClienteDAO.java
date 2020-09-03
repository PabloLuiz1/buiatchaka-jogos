package br.edu.fatec.buiatchaka.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.cliente.Cliente;
import br.edu.fatec.buiatchaka.dominio.cliente.Endereco;
import br.edu.fatec.buiatchaka.dominio.enums.EnumGenero;
import br.edu.fatec.buiatchaka.web.util.Conexao;

public class ClienteDAO implements IDao {

	private Connection connection = null;

	@Override
	public void salvar(EntidadeDominio entidade) {
		PreparedStatement stm = null;
		Cliente cliente = (Cliente) entidade;

		try {
			connection = Conexao.conectar();
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tb_cliente (cli_nome, cli_rg, cli_cpf, cli_data_nascimento, "
					+ "cli_genero, cli_email, cli_senha)");
			sql.append("VALUES (?, ?, ?, ?, ?, ?, ?)");

			stm = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			stm.setString(1, cliente.getNome());
			stm.setString(2, cliente.getRg());
			stm.setString(3, cliente.getCpf());
			stm.setDate(4, Date.valueOf(cliente.getDataNascimento()));
			stm.setString(5, cliente.getGenero().toString());
			stm.setString(6, cliente.getEmail());
			stm.setString(7, cliente.getSenha());

			stm.executeUpdate();

			ResultSet rs = stm.getGeneratedKeys();
			int idCliente = 0;
			if (rs.next())
				idCliente = rs.getInt(1);
			cliente.setId(idCliente);
			cliente.getTelefone().setId(idCliente);

			connection.commit();

			TelefoneDAO telefoneDAO = new TelefoneDAO();
			telefoneDAO.salvar(cliente.getTelefone());
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
		Cliente cliente = (Cliente) entidade;

		try {
			connection = Conexao.conectar();
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE tb_cliente SET cli_nome = ?, cli_rg = ?, cli_cpf = ?, cli_data_nascimento = ?, "
					+ "cli_genero = ?, cli_email = ?, cli_senha = ? WHERE cli_status = ? AND cli_id = ?");

			stm = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			stm.setString(1, cliente.getNome());
			stm.setString(2, cliente.getRg());
			stm.setString(3, cliente.getCpf());
			stm.setDate(4, Date.valueOf(cliente.getDataNascimento()));
			stm.setString(5, cliente.getGenero().toString());
			stm.setString(6, cliente.getEmail());
			stm.setString(7, cliente.getSenha());
			stm.setBoolean(8, true);
			stm.setLong(9, cliente.getId());
			
			stm.executeUpdate();

			ResultSet rs = stm.getGeneratedKeys();
			int idCliente = 0;
			if (rs.next())
				idCliente = rs.getInt(1);
			cliente.setId(idCliente);
			cliente.getTelefone().setId(idCliente);

			connection.commit();

			TelefoneDAO telefoneDAO = new TelefoneDAO();
			telefoneDAO.alterar(cliente.getTelefone());
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
		Cliente cliente = (Cliente) entidade;

		try {
			connection = Conexao.conectar();
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE tb_cliente SET cli_status = ? WHERE cli_id = ?");

			stm = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			stm.setBoolean(1, false);
			stm.setLong(2, cliente.getId());

			stm.executeUpdate();

			ResultSet rs = stm.getGeneratedKeys();
			int idCliente = 0;
			if (rs.next())
				idCliente = rs.getInt(1);
			cliente.setId(idCliente);
			cliente.getTelefone().setId(idCliente);

			connection.commit();

			TelefoneDAO telefoneDAO = new TelefoneDAO();
			telefoneDAO.excluir(cliente.getTelefone());
			EnderecoDAO enderecoDAO = new EnderecoDAO();
			cliente.getEnderecos().forEach(enderecoDAO::excluir);
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
		PreparedStatement stm = null;
		List<EntidadeDominio> clientes = null;
		try {
			StringBuilder sql = new StringBuilder();
			connection = Conexao.conectar();
			connection.setAutoCommit(false);
			sql.append("SELECT * FROM tb_cliente WHERE cli_status = ?");
			stm = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			stm.setBoolean(1, true);
			ResultSet rs = stm.executeQuery();
			clientes = new ArrayList<EntidadeDominio>();
			while (rs.next()) {
				Cliente cli = new Cliente();
				cli.setNome(rs.getString("cli_nome"));
				cli.setGenero(EnumGenero.valueOf(rs.getString("cli_genero")));
				cli.setRg(rs.getString("cli_rg"));
				cli.setCpf(rs.getString("cli_cpf"));
				cli.setEmail(rs.getString("cli_email"));
				cli.setSenha(rs.getString("cli_senha"));
				cli.setDataNascimento(rs.getDate("cli_data_nascimento").toLocalDate());
				cli.setDataUltimoLogin(rs.getDate("cli_data_ultimo_login").toLocalDate());
				cli.setDataUltimaCompra(rs.getDate("cli_data_ultima_compra").toLocalDate());
				cli.setQtdPedidos(rs.getInt("cli_qtd_pedidos"));
				clientes.add(cli);
			}
			return clientes;
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
		return clientes;
	}

}
