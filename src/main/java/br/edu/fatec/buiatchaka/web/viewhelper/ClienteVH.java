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
import br.edu.fatec.buiatchaka.dominio.enums.EnumGenero;

public class ClienteVH implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		HttpSession session = null;
		Cliente cliente = null;
		String operacao = request.getParameter("operacao");

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
		EnderecoVH enderecoVH = new EnderecoVH();
		cliente.setId((Long.parseLong(request.getParameter("id"))));
		cliente.setNome(request.getParameter("nome"));
		cliente.setRg(request.getParameter("rg"));
		cliente.setCpf(request.getParameter("cpf"));
		cliente.setGenero(EnumGenero.valueOf(request.getParameter("genero").toUpperCase()));
		cliente.setDataNascimento(LocalDate.parse(request.getParameter("dataNascimento")));
		cliente.setEmail(request.getParameter(("email")));
		cliente.setSenha(request.getParameter("senha"));
		
//		cliente.setTelefone((Telefone) telefoneVH.getEntidade(request));
		
		return cliente;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher d = null;
		String operacao = request.getParameter("operacao");

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
