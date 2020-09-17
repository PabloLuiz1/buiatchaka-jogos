package br.edu.fatec.buiatchaka.web.viewhelper;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.Resultado;
import br.edu.fatec.buiatchaka.dominio.cliente.Bandeira;
import br.edu.fatec.buiatchaka.dominio.cliente.Cartao;
import br.edu.fatec.buiatchaka.dominio.cliente.Cliente;
import br.edu.fatec.buiatchaka.dominio.cliente.Endereco;

public class CartaoVH implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		HttpSession session = null;
		Cartao cartao = null;
		String operacao = request.getParameter("operacao");

		if (operacao.equals("SALVAR")) {
			cartao = criarCartao(request);

		} else if (operacao.equals("ALTERAR")) {
			cartao = criarCartao(request, Long.parseLong(request.getParameter("cartao")));

		} else if (operacao.equals("EXCLUIR")) {
			cartao = criarCartao(request, Long.parseLong(request.getParameter("cartao")));

		} else if (operacao.equals("CONSULTAR")) {
			cartao = criarCartao(request, Long.parseLong(request.getParameter("cartao")));

		}
		
		return cartao;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		RequestDispatcher d = null;
		String operacao = request.getParameter("operacao");


//		if (resultado.getMsg().trim().equals("") && operacao.equals("SALVAR")) {
//			System.out.println("Adicionando resultado na request.");
//			request.setAttribute("resultado", (Resultado) resultado);
//			d = request.getRequestDispatcher("perfil-dados.html");
//		}
		if (resultado.getMsg() != null && operacao.equals("SALVAR")) {
			request.setAttribute("resultado", resultado);
//			request.setAttribute("endereco", (Endereco) resultado.getEntidades().get(0));
			d = request.getRequestDispatcher("perfil-dados");
		}
		if (resultado.getMsg() == null && operacao.equals("CONSULTAR")) {
//			resultado.setMsg("Consulta realizada com sucesso.");
			request.getSession().setAttribute("resultado", (Resultado) resultado);
			d = request.getRequestDispatcher("ver-cartao");
		}
		if (resultado.getMsg() == null && operacao.equals("ALTERAR")) {
//			resultado.setMsg("Alteração realizada com sucesso.");
			request.getSession().setAttribute("resultado", (Resultado) resultado);
			d = request.getRequestDispatcher("editar-cartao");
		}
		if (operacao.equals("EXCLUIR")) {
			
			request.getSession().setAttribute("resultado", (Resultado) resultado);
			d = request.getRequestDispatcher("perfil-dados");
		}
		if (resultado.getMsg() == "" || resultado.getMsg() == null) {
			List<Cartao> cartoes = new ArrayList<Cartao>();
			Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
//			request.getSession().setAttribute("cliente", resultado.getEntidades().get(0));
//			request.setAttribute("endereco", (Endereco) resultado.getEntidades().get(0));
			if (operacao.equals("SALVAR")) {
				d = request.getRequestDispatcher("perfil-dados");
			}
			if (operacao.equals("ALTERAR")) {
				d = request.getRequestDispatcher("editar-cartao");
			}
		}

		d.forward(request, response);
	}
	
	
	private Cartao criarCartao(HttpServletRequest request) {
		Cartao cartao = new Cartao();
		cartao.setNumero(request.getParameter("numeroCartao"));
		cartao.setNomeTitular(request.getParameter("nomeTitular"));
		cartao.setBandeira(request.getParameter("bandeira"));
		cartao.setCodigo(request.getParameter("codigo"));
		cartao.setDataVencimento(LocalDate.of(Integer.parseInt(request.getParameter("ano")), Integer.parseInt(request.getParameter("mes")), 1));
		cartao.setCpfTitular(request.getParameter("cpfTitular"));
		cartao.setCliente((Cliente) request.getSession().getAttribute("cliente"));
		
//		Resultado resultado = (Resultado) request.getSession().getAttribute("resultado");
//		cartao.setId(resultado.getEntidades().get(0).getId());
		
		return cartao;
	}
	
	private Cartao criarCartao(HttpServletRequest request, long id) {
		Cartao cartao = new Cartao();
		cartao.setId(Long.parseLong(request.getParameter("cartao")));
		Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
		cartao.setCliente(cliente);
		return cartao;
	}

}
