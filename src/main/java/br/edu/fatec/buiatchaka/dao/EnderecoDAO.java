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
import br.edu.fatec.buiatchaka.web.util.Conexao;

public class EnderecoDAO implements IDao {

	private Connection connection = null;

	@Override
	public void salvar(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		Endereco endereco = (Endereco) entidade;

		try {
			connection = Conexao.conectar();
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append(
					"INSERT INTO tb_enderecos_cliente (end_cli_id, end_nome, end_logradouro,"
					+ "end_cep, end_numero, end_bairro, end_cidade, end_estado, end_complemento,"
					+ "end_tipo, end_tipo_logradouro, end_observacoes, end_pais, end_tipo_residencia)");
			sql.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			
			pst.setLong(1, endereco.getId());
			pst.setString(2, endereco.getNome());
			pst.setString(3, endereco.getLogradouro());
			pst.setString(4, endereco.getCep());
			pst.setString(5, endereco.getNumero());
			pst.setString(6, endereco.getBairro());
			pst.setString(7, endereco.getCidade());
			pst.setString(8, endereco.getEstado());
			pst.setString(9, endereco.getComplemento());
			pst.setString(10, endereco.getTipoEndereco().toString());
			pst.setString(12, endereco.getObservacoes().toString());
			pst.setString(13, endereco.getPais());

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

	}

	@Override
	public void alterar(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		Endereco endereco = (Endereco) entidade;

		try {
			connection = Conexao.conectar();

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE tb_enderecos_cliente SET end_nome=?, end_cep=?, end_logradouro=?, end_bairro=?, ");
			sql.append("end_complemento=?, end_numero=?, end_tiporesidencia=?, end_tipologradouro=?, ");
			sql.append("end_entrega=?, end_cobranca=?,end_cidade=?, end_estado=?, end_pais=? WHERE end_status = ? AND end_id = ? ");

			pst = connection.prepareStatement(sql.toString());

			pst.setString(2, endereco.getNome());
			pst.setString(3, endereco.getLogradouro());
			pst.setString(4, endereco.getCep());
			pst.setString(5, endereco.getNumero());
			pst.setString(6, endereco.getBairro());
			pst.setString(7, endereco.getCidade());
			pst.setString(8, endereco.getEstado());
			pst.setString(9, endereco.getComplemento());
			pst.setString(10, endereco.getTipoEndereco().toString());
			pst.setString(12, endereco.getObservacoes().toString());
			pst.setString(13, endereco.getPais());
			pst.setBoolean(15, true);
			pst.setLong(16, endereco.getId());

			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		Endereco endereco = (Endereco) entidade;

		try {
			connection = Conexao.conectar();

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE tb_endereco SET end_status = ? WHERE end_id = ?");

			pst = connection.prepareStatement(sql.toString());
			pst.setBoolean(1, false);
			pst.setLong(2, endereco.getId());

			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
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
			sql.append("SELECT * FROM tb_enderecos_cliente WHERE end_status = ?");
			stm = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
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
