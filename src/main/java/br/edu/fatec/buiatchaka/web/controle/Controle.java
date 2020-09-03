package br.edu.fatec.buiatchaka.web.controle;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.fatec.buiatchaka.command.AlterarCommand;
import br.edu.fatec.buiatchaka.command.ConsultarCommand;
import br.edu.fatec.buiatchaka.command.ExcluirCommand;
import br.edu.fatec.buiatchaka.command.ICommand;
import br.edu.fatec.buiatchaka.command.SalvarCommand;
import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.Resultado;
import br.edu.fatec.buiatchaka.web.viewhelper.CartaoVH;
import br.edu.fatec.buiatchaka.web.viewhelper.ClienteVH;
import br.edu.fatec.buiatchaka.web.viewhelper.EnderecoVH;
import br.edu.fatec.buiatchaka.web.viewhelper.IViewHelper;

@WebServlet(urlPatterns = { "/ControleCliente", "/ControleCartao", "/ControleEndereco", 
		"/ControleTelefone", "/admin/ControleCliente" })
public class Controle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Map<String, ICommand> commands;
	private Map<String, IViewHelper> vhs;

	public Controle() {
		commands = new HashMap<String, ICommand>();
		commands.put("SALVAR", new SalvarCommand());
		commands.put("ALTERAR", new AlterarCommand());
		commands.put("EXCLUIR", new ExcluirCommand());
		commands.put("CONSULTAR", new ConsultarCommand());
		commands.put("PERFIL", new ConsultarCommand());

		vhs = new HashMap<String, IViewHelper>();
		vhs.put("/buiatchaka-smartphones/ControleCliente", new ClienteVH());
		vhs.put("/buiatchaka-smartphones/ControleEndereco", new EnderecoVH());
		vhs.put("/buiatchaka-smartphones/ControleCartao", new CartaoVH());
		vhs.put("/admin/ControleCliente", new ClienteVH());
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		String operacao = request.getParameter("btnOperacao");

		System.out.println("URI: " + uri);
		System.out.println("OPERACAO: " + request.getParameter("btnOperacao"));

		IViewHelper vh = vhs.get(uri);
		EntidadeDominio entidade = vh.getEntidade(request);

		ICommand command = null;
		Resultado resultado = null;

		command = commands.get(operacao);
		resultado = command.executar(entidade);

		vh.setView(resultado, request, response);
	}
}