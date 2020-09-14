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
import br.edu.fatec.buiatchaka.sistema.logging.Log;

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
			cliente = criarCliente(request);

		} else if (operacao.equals("CONSULTAR")) {
			cliente = criarCliente(request);
		}
		return cliente;
	}

	private Cliente criarCliente(HttpServletRequest request) {
		Cliente cliente = new Cliente();
//		cliente.setId((Long.parseLong(request.getParameter("id"))));
		cliente.setNome(request.getParameter("nome"));
		cliente.setRg(request.getParameter("rg"));
		cliente.setCpf(request.getParameter("cpf"));
		cliente.setTelefone(request.getParameter("telefone"));
//		System.out.println(request.getParameter("telefone"));
//		System.out.println(request.getParameter("dataNascimento"));
//		System.out.println(request.getParameter("genero"));
		cliente.setDataNascimento(LocalDate.parse((request.getParameter("dataNascimento"))));
		cliente.setGenero(EnumGenero.valueOf(request.getParameter("genero").toString()));
		cliente.setEmail(request.getParameter(("email")));
		cliente.setSenha(request.getParameter("senha"));

		return cliente;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher d = null;
		String operacao = request.getParameter("operacao");

		if (resultado.getMsg().trim().equals("") && operacao.equals("SALVAR")) {
			System.out.println("Adicionando resultado na request.");
			request.setAttribute("resultado", (Resultado) resultado);
			request.getSession().setAttribute("cliente", (Cliente) resultado.getEntidades().get(0));
			Log.loggar("ID do Cliente a ser passado para a página de endereço: " + resultado.getEntidades().get(0).getId());
			d = request.getRequestDispatcher("perfil-dados");
		}
		if (resultado.getMsg() == null && operacao.equals("CONSULTAR")) {
			resultado.setMsg("Consulta realizada com sucesso.");
			request.getSession().setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("ver-cliente");
		}
		if (resultado.getMsg() == null && operacao.equals("ALTERAR")) {
			resultado.setMsg("Alteração realizada com sucesso.");
			request.getSession().setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("editar-cliente");
		}
		if (resultado.getMsg() == null && operacao.equals("EXCLUIR")) {
			resultado.setMsg("Cliente inativado com sucesso.");
			request.getSession().setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("clientes");
		}
		if (!resultado.getMsg().trim().equals("")) {
			request.setAttribute("resultado", (Resultado) resultado);
			request.setAttribute("cliente", (Cliente) resultado.getEntidades().get(0));
			if (operacao.equals("SALVAR")) {
				d = request.getRequestDispatcher("sign-up");
			}
			if (operacao.equals("ALTERAR")) {
				d = request.getRequestDispatcher("editar-cliente");
			}
		}

		d.forward(request, response);

	}

}
