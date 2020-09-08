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
import br.edu.fatec.buiatchaka.dominio.cliente.Endereco;
import br.edu.fatec.buiatchaka.dominio.cliente.Telefone;
import br.edu.fatec.buiatchaka.dominio.enums.EnumGenero;
import br.edu.fatec.buiatchaka.web.util.Conexao;

public class ClienteDAO implements IDao {
	private EnderecoDAO enderecoDAO = new EnderecoDAO();
	private TelefoneDAO telefoneDAO = new TelefoneDAO();
	private CartaoDAO cartaoDAO = new CartaoDAO();
	private Connection connection = null;

	@Override
	public void salvar(EntidadeDominio entidade) {
		PreparedStatement stm = null;
		Cliente cliente = (Cliente) entidade;

		try {
			connection = Conexao.conectar();
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO cliente (nome, rg, cpf, email, genero, data_nascimento, senha)");
			sql.append("VALUES (?, ?, ?, ?, ?, ?, ?)");

			stm = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			stm.setString(1, cliente.getNome());
			stm.setString(2, cliente.getRg());
			stm.setString(3, cliente.getCpf());
			stm.setString(4, cliente.getEmail());
			stm.setString(5, cliente.getGenero().toString());
			stm.setDate(6, Date.valueOf(cliente.getDataNascimento()));
			stm.setString(7, cliente.getSenha());

			stm.executeUpdate();

			ResultSet rs = stm.getGeneratedKeys();
			int idCliente = 0;
			if (rs.next())
				idCliente = rs.getInt(1);
			cliente.setId(idCliente);

			for (Telefone t : cliente.getTelefones()) {
				t.setCliente(cliente);
			}
			for (Endereco e : cliente.getEnderecos()) {
				e.setCliente(cliente);
			}

			cliente.getTelefones().forEach(telefoneDAO::salvar);
			cliente.getEnderecos().forEach(enderecoDAO::salvar);
			cliente.getCartoes().forEach(cartaoDAO::salvar);
			
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
		Cliente cliente = (Cliente) entidade;

		try {
			connection = Conexao.conectar();
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append(
					"UPDATE cliente SET nome = ?, rg = ?, cpf = ?, email = ?, genero = ?, data_nascimento = ?, senha  = ? "
							+ "WHERE ativo = ? AND id = ?");

			stm = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			stm.setString(1, cliente.getNome());
			stm.setString(2, cliente.getRg());
			stm.setString(3, cliente.getCpf());
			stm.setString(4, cliente.getEmail());
			stm.setString(5, cliente.getGenero().toString());
			stm.setDate(6, Date.valueOf(cliente.getDataNascimento()));
			stm.setString(7, cliente.getSenha());
			stm.setBoolean(8, true);
			stm.setLong(9, cliente.getId());

			stm.executeUpdate();

			ResultSet rs = stm.getGeneratedKeys();
			int idCliente = 0;
			if (rs.next())
				idCliente = rs.getInt(1);
			cliente.setId(idCliente);

			for (Telefone t : cliente.getTelefones()) {
				t.setCliente(cliente);
			}
			for (Endereco e : cliente.getEnderecos()) {
				e.setCliente(cliente);
			}

			cliente.getTelefones().forEach(telefoneDAO::alterar);
			cliente.getEnderecos().forEach(enderecoDAO::alterar);
			cliente.getCartoes().forEach(cartaoDAO::alterar);
			
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
		Cliente cliente = (Cliente) entidade;

		try {
			connection = Conexao.conectar();
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE cliente SET ativo = ? WHERE id = ?");

			stm = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			stm.setBoolean(1, false);
			stm.setLong(2, cliente.getId());

			stm.executeUpdate();

			ResultSet rs = stm.getGeneratedKeys();
			int idCliente = 0;
			if (rs.next())
				idCliente = rs.getInt(1);
			cliente.setId(idCliente);

			for (Telefone t : cliente.getTelefones()) {
				t.setCliente(cliente);
			}
			for (Endereco e : cliente.getEnderecos()) {
				e.setCliente(cliente);
			}
			
			cliente.getTelefones().forEach(telefoneDAO::excluir);
			cliente.getEnderecos().forEach(enderecoDAO::excluir);
			cliente.getCartoes().forEach(cartaoDAO::excluir);
			
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

	@SuppressWarnings("unchecked")
	@Override
	public List<EntidadeDominio> listar(EntidadeDominio entidade) {
		PreparedStatement stm = null;
		List<EntidadeDominio> clientes = null;
		try {
			StringBuilder sql = new StringBuilder();
			connection = Conexao.conectar();
			connection.setAutoCommit(false);
			sql.append("SELECT * FROM cliente");
			stm = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stm.executeQuery();
			clientes = new ArrayList<EntidadeDominio>();
			List<? extends EntidadeDominio> telefones = new ArrayList<Telefone>();
			List<? extends EntidadeDominio> enderecos = new ArrayList<Endereco>();
			List<? extends EntidadeDominio> cartoes = new ArrayList<Cartao>();
			while (rs.next()) {
				Cliente cli = new Cliente();
				cli.setId(rs.getLong("id"));
				cli.setNome(rs.getString("nome"));
				cli.setGenero(EnumGenero.valueOf(rs.getString("genero")));
				cli.setRg(rs.getString("rg"));
				cli.setCpf(rs.getString("cpf"));
				cli.setEmail(rs.getString("email"));
				cli.setSenha(rs.getString("senha"));
				cli.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
				cli.setDataUltimoLogin((rs.getDate(("data_ultimo_login")).toLocalDate()));
				cli.setDataUltimaCompra(rs.getDate("data_ultima_compra").toLocalDate());
				cli.setQtdPedidos(rs.getInt("qtd_pedidos"));
				telefones = telefoneDAO.listar(cli);
				enderecos = enderecoDAO.listar(cli);
				cartoes = cartaoDAO.listar(cli);
				cli.setTelefones((List<Telefone>) telefones);
				cli.setEnderecos((List<Endereco>) enderecos);
				cli.setCartoes((List<Cartao>) cartoes);
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

	@SuppressWarnings("unchecked")
	@Override
	public EntidadeDominio consultar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		PreparedStatement stm = null;
		try {
			StringBuilder sql = new StringBuilder();
			connection = Conexao.conectar();
			connection.setAutoCommit(false);
			sql.append("SELECT * FROM cliente WHERE ativo = ? AND id = ?");
			stm = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			stm.setBoolean(1, true);
			stm.setLong(2, cliente.getId());
			ResultSet rs = stm.executeQuery();
			List<? extends EntidadeDominio> telefones = new ArrayList<Telefone>();
			List<? extends EntidadeDominio> enderecos = new ArrayList<Endereco>();
			List<? extends EntidadeDominio> cartoes = new ArrayList<Cartao>();
			while (rs.next()) {
				cliente.setId(rs.getLong("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setGenero(EnumGenero.valueOf(rs.getString("genero")));
				cliente.setRg(rs.getString("rg"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setEmail(rs.getString("email"));
				cliente.setSenha(rs.getString("senha"));
				cliente.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
				cliente.setDataUltimoLogin((rs.getDate(("data_ultimo_login")).toLocalDate()));
				cliente.setDataUltimaCompra(rs.getDate("data_ultima_compra").toLocalDate());
				cliente.setQtdPedidos(rs.getInt("qtd_pedidos"));
				telefones = telefoneDAO.listar(cliente);
				enderecos = enderecoDAO.listar(cliente);
				cartoes = cartaoDAO.listar(cliente);
				cliente.setTelefones((List<Telefone>) telefones);
				cliente.setEnderecos((List<Endereco>) enderecos);
				cliente.setCartoes((List<Cartao>) cartoes);
			}
			return cliente;
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

}