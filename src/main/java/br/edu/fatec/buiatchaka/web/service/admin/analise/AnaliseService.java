package br.edu.fatec.buiatchaka.web.service.admin.analise;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fatec.buiatchaka.dominio.pedido.Pedido;
import br.edu.fatec.buiatchaka.repository.pedido.PedidoRepository;
import br.edu.fatec.buiatchaka.repository.produto.ProdutoRepository;

@Service
public class AnaliseService {
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public List<Object> processar (LocalDate dataInicio, LocalDate dataFim){
		return pedidoRepository.findAnaliseByData(dataInicio, dataFim);
	}
}
