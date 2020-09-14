package br.edu.fatec.buiatchaka.dao;

import java.util.List;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;

public interface IDao {
	public EntidadeDominio salvar(EntidadeDominio entidade);
	public void alterar(EntidadeDominio entidade);
	public void excluir(EntidadeDominio entidade);
	public List<EntidadeDominio> listar(EntidadeDominio entidade);
	public EntidadeDominio consultar(EntidadeDominio entidade);
}
