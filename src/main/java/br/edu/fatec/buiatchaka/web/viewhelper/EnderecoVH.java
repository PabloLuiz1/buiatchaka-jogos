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
import br.edu.fatec.buiatchaka.sistema.logging.Log;

public class EnderecoVH implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		HttpSession session = null;
		Endereco endereco = null;
		String operacao = request.getParameter("operacao");

		if (operacao.equals("SALVAR")) {
			endereco = criarEndereco(request);
		} 
		if (operacao.equals("ALTERAR")) {
			endereco = criarEndereco(request, Long.parseLong(request.getParameter("endereco")));
		} 
		if (operacao.equals("EXCLUIR")) {
			endereco = criarEndereco(request, Long.parseLong(request.getParameter("endereco")));
		} 
		if (operacao.equals("CONSULTAR")) {
			endereco = criarEndereco(request, Long.parseLong(request.getParameter("endereco")));
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
	
	private Endereco criarEndereco(HttpServletRequest request, long id) {
		Endereco endereco = new Endereco();
		endereco.setId(id);
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
		if (resultado.getMsg() != null && operacao.equals("SALVAR")) {
			request.setAttribute("resultado", resultado);
//			request.setAttribute("endereco", (Endereco) resultado.getEntidades().get(0));
			d = request.getRequestDispatcher("perfil");
		}
		if (operacao.equals("CONSULTAR")) {
//			resultado.setMsg("Consulta realizada com sucesso.");
//			request.getSession().setAttribute("resultado", (Resultado) resultado);
			Log.loggar("Entrou no IF do CONSULTAR na EnderecoVH");
			request.setAttribute("endereco", (Endereco) resultado.getEntidades().get(0));
			d = request.getRequestDispatcher("perfil/ver-endereco");
		}
		if (resultado.getMsg() == null && operacao.equals("ALTERAR")) {
//			resultado.setMsg("Alteração realizada com sucesso.");
			request.getSession().setAttribute("resultado", (Resultado) resultado);
			d = request.getRequestDispatcher("perfil/editar-endereco");
		}
		if (operacao.equals("EXCLUIR")) {
			
			request.getSession().setAttribute("resultado", (Resultado) resultado);
			d = request.getRequestDispatcher("perfil");
		}
		if (resultado.getMsg() == "" || resultado.getMsg() == null) {
			List<Endereco> enderecos = new ArrayList<Endereco>();
			Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
			request.getSession().setAttribute("cliente", resultado.getEntidades().get(0));
			request.setAttribute("endereco", (Endereco) resultado.getEntidades().get(0));
			if (operacao.equals("SALVAR")) {
				d = request.getRequestDispatcher("perfil");
			}
			if (operacao.equals("ALTERAR")) {
				d = request.getRequestDispatcher("ver-endereco");
			}
		}

		d.forward(request, response);
	}

}
