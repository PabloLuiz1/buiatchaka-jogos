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
import br.edu.fatec.buiatchaka.dominio.cliente.Endereco;
import br.edu.fatec.buiatchaka.dominio.enums.EnumGenero;
import br.edu.fatec.buiatchaka.dominio.enums.EnumTipoEndereco;
import br.edu.fatec.buiatchaka.web.util.Conexao;

public class EnderecoDAO implements IDao {

	private Connection connection = null;
	private PreparedStatement pst = null;
	@Override
	public EntidadeDominio salvar(EntidadeDominio entidade) {
		Endereco endereco = (Endereco) entidade;

		try {
			connection = Conexao.conectar();
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append(
					"INSERT INTO endereco (nome, cep, logradouro, numero, complemento, bairro, cidade, estado, cliente_id)");
			sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			
			pst.setString(1, endereco.getNome());
			pst.setString(2, endereco.getCep());
			pst.setString(3, endereco.getLogradouro());
			pst.setString(4, endereco.getNumero());
			pst.setString(5, endereco.getComplemento());
			pst.setString(6, endereco.getBairro());
			pst.setString(7, endereco.getCidade());
			pst.setString(8, endereco.getEstado());
			pst.setLong(9, endereco.getCliente().getId());
			
			pst.executeUpdate();

			ResultSet rs = pst.getGeneratedKeys();
			long idEndereco = 0;
			if (rs.next())
				idEndereco = rs.getInt(1);
			endereco.setId(idEndereco);
			
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
		return endereco;
	}

	@Override
	public void alterar(EntidadeDominio entidade) {
		Endereco endereco = (Endereco) entidade;

		try {
			connection = Conexao.conectar();

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE endereco SET nome = ?, cep = ?, logradouro = ?, numero = ?, complemento = ?, bairro = ?, cidade = ?, estado = ?, "
					+ "tipo_endereco = ?"
					+ " WHERE ativo = ? AND id = ? ");
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, endereco.getNome());
			pst.setString(2, endereco.getCep());
			pst.setString(3, endereco.getLogradouro());
			pst.setString(4, endereco.getNumero());
			pst.setString(5, endereco.getComplemento());
			pst.setString(6, endereco.getBairro());
			pst.setString(7, endereco.getCidade());
			pst.setString(8, endereco.getEstado());
			pst.setBoolean(9, true);
			pst.setLong(10, endereco.getId());

			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		Endereco endereco = (Endereco) entidade;

		try {
			connection = Conexao.conectar();

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE endereco SET ativo = ? WHERE id = ?");
			pst = connection.prepareStatement(sql.toString());
			pst.setBoolean(1, false);
			pst.setLong(2, endereco.getId());
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<EntidadeDominio> listar(EntidadeDominio entidade) {
		List<EntidadeDominio> enderecos = null;
		Cliente cliente = (Cliente) entidade;
		try {
			StringBuilder sql = new StringBuilder();
			connection = Conexao.conectar();
			connection.setAutoCommit(false);
			sql.append("SELECT * FROM endereco WHERE ativo = ? AND cliente_id = ?");
			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pst.setBoolean(1, true);
			pst.setLong(2, cliente.getId());
			ResultSet rs = pst.executeQuery();
			enderecos = new ArrayList<EntidadeDominio>();
			while (rs.next()) {
				Endereco end = new Endereco();
				end.setId(rs.getLong("id"));
				end.setNome(rs.getString("nome"));
				end.setCep(rs.getString("cep"));
				end.setLogradouro(rs.getString("logradouro"));
				end.setNumero(rs.getString("numero"));
				end.setComplemento(rs.getString("complemento"));
				end.setBairro(rs.getString("bairro"));
				end.setCidade(rs.getString("cidade"));
				end.setEstado(rs.getString("estado"));
				end.setDataCadastro(rs.getDate("data_cadastro").toLocalDate());
				end.setAtivo(rs.getBoolean("ativo"));
				end.setCliente(cliente);
				enderecos.add(end);
			}
			return enderecos;
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
		return enderecos;
	}

	@Override
	public EntidadeDominio consultar(EntidadeDominio entidade) {
		Endereco endereco = null;
		Cliente cliente = (Cliente) entidade;
		try {
			StringBuilder sql = new StringBuilder();
			connection = Conexao.conectar();
			connection.setAutoCommit(false);
			sql.append("SELECT * FROM endereco WHERE ativo = ? AND cliente_id = ?");
			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pst.setBoolean(1, true);
			pst.setLong(2, cliente.getId());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				endereco = new Endereco();
				endereco.setId(rs.getLong("id"));
				endereco.setNome(rs.getString("nome"));
				endereco.setCep(rs.getString("cep"));
				endereco.setLogradouro(rs.getString("logradouro"));
				endereco.setNumero(rs.getString("numero"));
				endereco.setComplemento(rs.getString("complemento"));
				endereco.setBairro(rs.getString("bairro"));
				endereco.setCidade(rs.getString("cidade"));
				endereco.setEstado(rs.getString("estado"));
				endereco.setDataCadastro(rs.getDate("data_cadastro").toLocalDate());
				endereco.setAtivo(rs.getBoolean("ativo"));
				endereco.setCliente(cliente);
			}
			return endereco;
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
		return endereco;
	}
}
