package br.edu.fatec.buiatchaka.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.fatec.buiatchaka.dominio.cliente.Cliente;
import br.edu.fatec.buiatchaka.web.service.cliente.ClienteService;

@WebFilter(filterName = "FiltroClienteLogado", urlPatterns = {"/perfil"})
public class AtualizarClienteLogadoFilter implements Filter{
	@Autowired
	private ClienteService service;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) request;
		Cliente clienteLogado = (Cliente) httpReq.getSession().getAttribute("cliente");
		httpReq.getSession().setAttribute("cliente", service.consultar(clienteLogado.getId()));
		chain.doFilter(request, response);
	}
}
