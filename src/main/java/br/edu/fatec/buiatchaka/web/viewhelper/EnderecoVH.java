package br.edu.fatec.buiatchaka.web.viewhelper;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.Resultado;
import br.edu.fatec.buiatchaka.dominio.cliente.Endereco;
import br.edu.fatec.buiatchaka.dominio.enums.EnumTipoEndereco;

public class EnderecoVH implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		HttpSession session = null;
		Endereco endereco = null;
		String operacao = request.getParameter("btnOperacao");

		if (operacao.equals("SALVAR")) {
			endereco = criarEndereco(request);

		} else if (operacao.equals("ALTERAR")) {
			endereco = criarEndereco(request);

		} else if (operacao.equals("EXCLUIR")) {
			endereco = criarEndereco(request);

		} else if (operacao.equals("CONSULTAR")) {
			endereco = criarEndereco(request);

		}
		return endereco;
	}

	private Endereco criarEndereco(HttpServletRequest request) {
		Endereco endereco = new Endereco();

		endereco.setNome(request.getParameter("txtNomeEndereco"));
		endereco.setTipoEndereco(EnumTipoEndereco.valueOf(request.getParameter("cbTipoEndereco").toUpperCase()));
		endereco.setCep(request.getParameter("txtCep"));
		endereco.setLogradouro(request.getParameter("txtLogradouro"));
		endereco.setNumero(request.getParameter("txtNumero"));
		endereco.setBairro(request.getParameter("txtBairro"));
		endereco.setCidade(request.getParameter("txtCidade"));
		endereco.setEstado(request.getParameter("cbEstado"));
		endereco.setPais(request.getParameter("cbPais"));
		endereco.setComplemento(request.getParameter("txtComplemento"));
		endereco.setObservacoes(request.getParameter("txtObservacoes"));

		Resultado resultado = (Resultado) request.getSession().getAttribute("resultado");
		System.out.println(resultado.getEntidades().get(0).getId());
		endereco.setId(resultado.getEntidades().get(0).getId());

		return endereco;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher d = null;
		String operacao = request.getParameter("btnOperacao");

		if (resultado.getMsg() != null && !resultado.getMsg().trim().equals("")) {
			System.out.println("adicionando resultado na sessão");
			request.getSession().setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("register2.jsp");

		} else if (operacao.equals("SALVAR")) {
			resultado.setMsg("Cadastro realizado com sucesso.");
			request.getSession().setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("register2.jsp");

		} else if (operacao.equals("CONSULTAR")) {
			resultado.setMsg("Consulta realizada com sucesso.");
			request.getSession().setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("endereco.jsp");

		} else if (operacao.equals("ALTERAR")) {
			resultado.setMsg("Alteração realizada com sucesso.");
			request.getSession().setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("EditarEndereco.jsp");

		} else if (operacao.equals("EXCLUIR")) {
			resultado.setMsg("Funcionario inativado com sucesso.");
			request.getSession().setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("endereco.jsp");

		}

		d.forward(request, response);
	}

}
