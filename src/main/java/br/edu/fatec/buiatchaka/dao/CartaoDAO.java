package br.edu.fatec.buiatchaka.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.cliente.Cartao;
import br.edu.fatec.buiatchaka.dominio.cliente.Endereco;
import br.edu.fatec.buiatchaka.web.util.Conexao;

public class CartaoDAO implements IDao{
	
	private Connection connection = null;
	
	@Override
	public void salvar(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		Cartao cartao = (Cartao) entidade;

		try {
			connection = Conexao.conectar();
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append(
					"INSERT INTO tb_cartoes_cliente (car_cli_id, car_nome, car_numero, car_codigo,"
					+ "car_nome_impresso, car_bandeira)");
			sql.append("VALUES (?,?,?,?,?,?)");

			pst = connection.prepareStatement(sql.toString());

			pst.setLong(1, cartao.getId());
			pst.setString(2, cartao.getNome());
			pst.setString(3, cartao.getNumero());
			pst.setString(4, cartao.getCodigo());
			pst.setString(5, cartao.getNomeImpresso());
			pst.setString(6, cartao.getBandeira().getNome());

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
