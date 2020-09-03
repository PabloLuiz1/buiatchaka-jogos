package br.edu.fatec.buiatchaka.web.viewhelper;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.Resultado;
import br.edu.fatec.buiatchaka.dominio.cliente.Bandeira;
import br.edu.fatec.buiatchaka.dominio.cliente.Cartao;

public class CartaoVH implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		HttpSession session = null;
		Cartao cartao = null;
		String operacao = request.getParameter("btnOperacao");

		if (operacao.equals("SALVAR")) {
			cartao = criarCartao(request);

		} else if (operacao.equals("ALTERAR")) {
			cartao = criarCartao(request);

		} else if (operacao.equals("EXCLUIR")) {
			cartao = criarCartao(request);

		} else if (operacao.equals("CONSULTAR")) {
			cartao = criarCartao(request);

		}
		
		return cartao;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		RequestDispatcher d = null;
		String operacao = request.getParameter("btnOperacao");

		if (resultado.getMsg() != null && !resultado.getMsg().trim().equals("")) {
			System.out.println("adicionando resultado na sessão");
			request.getSession().setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("register3.jsp");

		} else if (operacao.equals("SALVAR")) {
			resultado.setMsg("Cadastro realizado com sucesso.");
			request.getSession().setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("register3.jsp");

		} else if (operacao.equals("CONSULTAR")) {
			resultado.setMsg("Consulta realizada com sucesso.");
			request.getSession().setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("cartao.jsp");

		} else if (operacao.equals("ALTERAR")) {
			resultado.setMsg("Alteração realizada com sucesso.");
			request.getSession().setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("cartao.jsp");

		} else if (operacao.equals("EXCLUIR")) {
			resultado.setMsg("Cartao excluido com sucesso.");
			request.getSession().setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("cartao.jsp");

		}

		d.forward(request, response);
	}
	
	
	private Cartao criarCartao(HttpServletRequest request) {
		Cartao cartao = new Cartao();
		cartao.setNome(request.getParameter("txtNomeCartao"));
		cartao.setNomeImpresso(request.getParameter("txtNomeImpresso"));
		cartao.setNumero(request.getParameter("txtNumero"));
		cartao.setBandeira(new Bandeira(request.getParameter("cbBandeira")));
		cartao.setCodigo(request.getParameter("txtCodigo"));
		
		Resultado resultado = (Resultado) request.getSession().getAttribute("resultado");
		cartao.setId(resultado.getEntidades().get(0).getId());
		
		return cartao;
	}

}
