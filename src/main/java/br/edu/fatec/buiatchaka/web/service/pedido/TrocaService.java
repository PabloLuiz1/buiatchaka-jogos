package br.edu.fatec.buiatchaka.web.service.pedido;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fatec.buiatchaka.dominio.pedido.Pedido;
import br.edu.fatec.buiatchaka.dominio.pedido.Troca;
import br.edu.fatec.buiatchaka.repository.pedido.TrocaRepository;
import br.edu.fatec.buiatchaka.web.service.exceptions.ObjectNotFoundException;

@Service
public class TrocaService {
	@Autowired
	private TrocaRepository repo;
	
	public void salvar(Troca troca) {
		repo.save(troca);
	}
	
	public Troca consultar(Long id) {
		Optional<Troca> troca = repo.findById(id);
		return troca.orElseThrow(() -> new ObjectNotFoundException("Item não encontrado. Tipo: " + Troca.class.getName()));
	}
	
	public List<Troca> listar(){
		return repo.findAll();
	}
	
	public List<Troca> listar(Pedido pedido){
		List<Troca> todasTrocas = repo.findAll();
		List<Troca> trocas = new ArrayList<Troca>();
		for (Troca t : todasTrocas) {
			Pedido p = t.getItem().getItem().getPedido();
			if (p.getId() == pedido.getId()) {
				trocas.add(t);
			}
		}
		return trocas;
	}
}
