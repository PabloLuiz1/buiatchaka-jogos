package br.edu.fatec.buiatchaka.web.viewhelper;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.Resultado;
import br.edu.fatec.buiatchaka.dominio.cliente.Telefone;

public class TelefoneVH implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		HttpSession session = null;
		Telefone telefone = null;
		String operacao = request.getParameter("btnOperacao");

		if (operacao.equals("SALVAR")) {
			telefone = criarTelefone(request);

		} else if (operacao.equals("ALTERAR")) {
			telefone = criarTelefone(request);

		} else if (operacao.equals("EXCLUIR")) {
			telefone = criarTelefone(request);

		} else if (operacao.equals("CONSULTAR")) {
			telefone = criarTelefone(request);

		}

		return telefone;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		RequestDispatcher d = null;
		String operacao = request.getParameter("btnOperacao");

		if (resultado.getMsg() != null && !resultado.getMsg().trim().equals("")) {
			System.out.println("adicionando resultado na sessão");
			request.getSession().setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("register.jsp");

		} else if (operacao.equals("SALVAR")) {
			resultado.setMsg("Cadastro realizado com sucesso.");
			request.getSession().setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("register.jsp");

		} else if (operacao.equals("CONSULTAR")) {
			resultado.setMsg("Consulta realizada com sucesso.");
			request.getSession().setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("AlterarCliente.jsp");

		} else if (operacao.equals("ALTERAR")) {
			resultado.setMsg("Alteração realizada com sucesso.");
			request.getSession().setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("AlterarCliente.jsp");

		} else if (operacao.equals("EXCLUIR")) {
			resultado.setMsg("Cartao excluido com sucesso.");
			request.getSession().setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("AlterarCliente.jsp");

		}

		d.forward(request, response);

	}

	private Telefone criarTelefone(HttpServletRequest request) {
		Telefone telefone = new Telefone();
		

		telefone.setDdd(request.getParameter("txtDdd"));
		telefone.setNumero(request.getParameter("txtTelefone"));

		return telefone;
	}

}
