package br.edu.fatec.buiatchaka.web.viewhelper;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.Resultado;
import br.edu.fatec.buiatchaka.dominio.cliente.Cliente;
import br.edu.fatec.buiatchaka.dominio.cliente.Telefone;
import br.edu.fatec.buiatchaka.dominio.enums.EnumGenero;

public class ClienteVH implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		HttpSession session = null;
		Cliente cliente = null;
		String operacao = request.getParameter("btnOperacao");

		if (operacao.equals("SALVAR")) {
			cliente = criarCliente(request);
		} else if (operacao.equals("ALTERAR")) {
			cliente = criarCliente(request);

		} else if (operacao.equals("EXCLUIR")) {
			cliente = new Cliente();
			cliente.setId(10);

		} else if (operacao.equals("CONSULTAR")) {
			cliente = criarCliente(request);
		}
		return cliente;
	}

	private Cliente criarCliente(HttpServletRequest request) {
		Cliente cliente = new Cliente();
		TelefoneVH telefoneVH = new TelefoneVH();
		
		cliente.setNome(request.getParameter("txtNome"));
		cliente.setRg(request.getParameter("txtRg"));
		cliente.setCpf(request.getParameter("txtCpf"));
		cliente.setGenero(EnumGenero.valueOf(request.getParameter("cbGenero").toUpperCase()));
		cliente.setDataNascimento(LocalDate.parse(request.getParameter("dataNascimento")));
		cliente.setEmail(request.getParameter(("txtEmail")));
		cliente.setSenha(request.getParameter("txtSenha"));
		
		cliente.setTelefone((Telefone) telefoneVH.getEntidade(request));
		
		return cliente;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher d = null;
		String operacao = request.getParameter("btnOperacao");

		if (resultado.getMsg() != null && !resultado.getMsg().trim().equals("")) {
			System.out.println("adicionando resultado na request");
			request.setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("register.jsp");

		} else if (operacao.equals("SALVAR")) {
			resultado.setMsg("Cadastro realizado com sucesso.");
			request.setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("register.jsp");

		} else if (operacao.equals("CONSULTAR")) {
			resultado.setMsg("Consulta realizada com sucesso.");
			request.getSession().setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("PesquisaCliente.jsp");

		} else if (operacao.equals("ALTERAR")) {
			resultado.setMsg("Alteração realizada com sucesso.");
			request.getSession().setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("AlterarCliente.jsp");

		} else if (operacao.equals("EXCLUIR")) {
			resultado.setMsg("Cliente inativado com sucesso.");
			request.getSession().setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("PesquisaCliente.jsp");

		}

		d.forward(request, response);

	}

}
