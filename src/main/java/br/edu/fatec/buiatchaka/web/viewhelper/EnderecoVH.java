package br.edu.fatec.buiatchaka.web.viewhelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.Resultado;
import br.edu.fatec.buiatchaka.dominio.cliente.Cliente;
import br.edu.fatec.buiatchaka.dominio.cliente.Endereco;

public class EnderecoVH implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		HttpSession session = null;
		Endereco endereco = null;
		String operacao = request.getParameter("operacao");

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

		endereco.setNome(request.getParameter("nome"));
		endereco.setCep(request.getParameter("cep"));
		endereco.setLogradouro(request.getParameter("logradouro"));
		endereco.setNumero(request.getParameter("numero"));
		endereco.setComplemento(request.getParameter("complemento"));
		endereco.setBairro(request.getParameter("bairro"));
		endereco.setCidade(request.getParameter("cidade"));
		endereco.setEstado(request.getParameter("estado"));
		Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
		endereco.setCliente(cliente);
		return endereco;
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
		if (!resultado.getMsg().trim().equals("") && operacao.equals("SALVAR")) {
			request.setAttribute("resultado", resultado);
			request.setAttribute("endereco", (Endereco) resultado.getEntidades().get(0));
			d = request.getRequestDispatcher("perfil-dados");
		}
		if (resultado.getMsg() == null && operacao.equals("CONSULTAR")) {
//			resultado.setMsg("Consulta realizada com sucesso.");
			request.getSession().setAttribute("resultado", (Resultado) resultado);
			d = request.getRequestDispatcher("ver-endereco");
		}
		if (resultado.getMsg() == null && operacao.equals("ALTERAR")) {
//			resultado.setMsg("Alteração realizada com sucesso.");
			request.getSession().setAttribute("resultado", (Resultado) resultado);
			d = request.getRequestDispatcher("editar-endereco");
		}
		if (resultado.getMsg() == null && operacao.equals("EXCLUIR")) {
//			resultado.setMsg("Cliente inativado com sucesso.");
			request.getSession().setAttribute("resultado", (Resultado) resultado);
			d = request.getRequestDispatcher("perfil-dados");
		}
		if (resultado.getMsg().trim().equals("")) {
			List<Endereco> enderecos = new ArrayList<Endereco>();
			Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
			for (EntidadeDominio e : resultado.getEntidades()) {
				enderecos.add((Endereco) e);
			}
			cliente.setEnderecos(enderecos);
			
			request.setAttribute("resultado", (Resultado) resultado);
			request.setAttribute("endereco", (Endereco) resultado.getEntidades().get(0));
			if (operacao.equals("SALVAR")) {
				d = request.getRequestDispatcher("perfil-dados");
			}
			if (operacao.equals("ALTERAR")) {
				d = request.getRequestDispatcher("editar-endereco");
			}
		}

		d.forward(request, response);
	}

}
