package br.edu.fatec.buiatchaka.dominio.pedido;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.cliente.Cartao;
import br.edu.fatec.buiatchaka.dominio.cliente.Cliente;
import br.edu.fatec.buiatchaka.dominio.cliente.Cupom;
import br.edu.fatec.buiatchaka.dominio.cliente.Endereco;
import br.edu.fatec.buiatchaka.sistema.logging.Log;
import br.edu.fatec.buiatchaka.web.util.TimerCarrinho;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Scope(scopeName = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class Carrinho extends EntidadeDominio {
	private Cliente cliente;
	private Endereco endereco;
	private final List<ItemCarrinho> itens = new ArrayList<ItemCarrinho>();
	private final List<Cartao> cartoes = new ArrayList<Cartao>();
	private final List<Cupom> cupons = new ArrayList<Cupom>(); 
	private double valorCartaoUm;
	private double valorCartaoDois;
	private double valorPago = 0;
	private double total = 0;
	private final TimerCarrinho timer = new TimerCarrinho(this);
	private final Timer t = new Timer();
	public Carrinho () {
		t.schedule(timer, 900000, 900000);
	}
	
	public void addItem(ItemCarrinho item) {
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

	public void removerItem(ItemCarrinho item) {
		devolverEstoque(item);
		itens.remove(item);
		setTotal();
	}

	public void setTotal() {
		this.total = 0;
		for (ItemCarrinho i : this.itens) {
			this.total = this.total + i.getSubtotal();
		}
		for (Cupom c : this.cupons) {
			this.total = this.total - c.getValor();
			this.valorPago = c.getValor();
			if (this.total <= 0)
				this.total = 0;
		}
	}

	public void adicionarCartao(Cartao cartao, double valor) {
		this.valorPago = this.valorPago + valor;
		this.cartoes.add(cartao);
	}

	public void removerCartao(Cartao cartao) {
		this.cartoes.remove(cartao);
	}

	public void adicionarCupom(Cupom cupom) {
		this.cupons.add(cupom);
		this.valorPago += cupom.getValor();
	}

	public void removerCupom(Cupom cupom) {
		this.cupons.remove(cupom);
		setTotal();
	}

	public void resetarCarrinho() {
		this.valorPago = 0;
		this.valorCartaoUm = 0;
		this.valorCartaoDois = 0;
		this.endereco = null;
		this.itens.forEach(this::devolverEstoque);
		this.limparItens();
		this.limparCartoes();
		this.limparCupons();
	}
	
	public void atualizarEstoque(ItemCarrinho item, int quantidade) {
	}
	
	public void retirarDoEstoque(ItemCarrinho item) {
		item.getItem().setQuantidade(item.getItem().getQuantidade() - item.getQuantidade());
		Log.loggar("RETIRANDO DO ESTOQUE\nTESTE DE QUANTIDADE DO ITEM NA CLASSE DE CARINHO: ITEM CARRINHO: " + item.getQuantidade() + " ITEM ESTOQUE: " + item.getItem().getQuantidade());
	}
	
	public void devolverEstoque(ItemCarrinho item) {
		item.getItem().setQuantidade(item.getItem().getQuantidade() + item.getQuantidade());
	}
	
	public boolean validarValorPago() {
		return (this.valorPago == this.total);
	}
}