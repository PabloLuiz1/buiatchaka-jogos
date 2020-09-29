package br.edu.fatec.buiatchaka.dominio.pedido;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.cliente.Cartao;
import br.edu.fatec.buiatchaka.dominio.cliente.Cliente;
import br.edu.fatec.buiatchaka.dominio.cliente.Cupom;
import br.edu.fatec.buiatchaka.dominio.cliente.Endereco;
import br.edu.fatec.buiatchaka.dominio.produto.ItemEstoque;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Scope(scopeName = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class Carrinho extends EntidadeDominio {
	private Cliente cliente;
	private Endereco endereco;
	private final List<ItemEstoque> itens = new ArrayList<ItemEstoque>();
	private final List<Cartao> cartoes = new ArrayList<Cartao>();
	private final List<Cupom> cupons = new ArrayList<Cupom>();
	private double total = 0;

	public void addItem(ItemEstoque item) {
		this.itens.add(item);
		setTotal();
	}

	public void limparItens() {
		this.itens.clear();
		setTotal();
	}
	
	public void limparCartoes() {
		this.cartoes.clear();
	}
	
	public void limparCupons() {
		this.cupons.clear();
	}

	public void removerItem(ItemEstoque item) {
		itens.remove(item);
		setTotal();
	}

	public void setTotal() {
		this.total = 0;
		for (ItemEstoque i : this.itens) {
			this.total = this.total + i.getProduto().getPrecoVenda();
		}
		for (Cupom c : this.cupons) { 
			this.total = this.total - c.getValor();
		}
	}
	
	public void adicionarCartao(Cartao cartao) {
		this.cartoes.add(cartao);
	}
	
	public void removerCartao(Cartao cartao) {
		this.cartoes.remove(cartao);
	}
	
	public void adicionarCupom(Cupom cupom) {
		this.cupons.add(cupom);
		setTotal();
	}
	
	public void removerCupom(Cupom cupom) {
		this.cupons.remove(cupom);
		setTotal();
	}
	
	public void resetarCarrinho() {
		this.endereco = null;
		this.limparItens();
		this.limparCartoes();
		this.limparCupons();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof ItemEstoque))
			return false;
		ItemEstoque i = (ItemEstoque) obj;
		return i.getId() == this.getId();
	}
}