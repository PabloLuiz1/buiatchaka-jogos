package br.edu.fatec.buiatchaka.dao;

import java.util.List;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;

public interface IDao {
	public void salvar(EntidadeDominio entidade);
	public void alterar(EntidadeDominio entidade);
	public void excluir(EntidadeDominio entidade);
	public List<EntidadeDominio> consultar(EntidadeDominio entidade);
}
