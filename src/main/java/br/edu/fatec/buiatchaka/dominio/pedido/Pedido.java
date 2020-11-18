package br.edu.fatec.buiatchaka.dominio.pedido;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.cliente.Cartao;
import br.edu.fatec.buiatchaka.dominio.cliente.Cliente;
import br.edu.fatec.buiatchaka.dominio.cliente.Cupom;
import br.edu.fatec.buiatchaka.dominio.cliente.Endereco;
import br.edu.fatec.buiatchaka.dominio.enums.EnumStatusPedido;
import br.edu.fatec.buiatchaka.sistema.logging.Log;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@NoArgsConstructor

@Entity
public class Pedido extends EntidadeDominio {
	@ManyToOne
	private Cliente cliente;
	@ManyToOne
	private Endereco endereco;
	@ManyToMany
	private List<Cartao> cartoes;
	@ManyToMany
	private List<Cupom> cupons;
	@OneToMany(cascade = CascadeType.ALL)
	private List<ItemPedido> itens;
	@Column(nullable = false, columnDefinition = "character varying (255) default 'PROCESSAMENTO'")
	private String status;
	private double valorTotal;
	
	public Pedido(Carrinho carrinho) {
		this.itens = new ArrayList<ItemPedido>();
		this.cliente = carrinho.getCliente();
		this.endereco = carrinho.getEndereco();
		this.cartoes = carrinho.getCartoes();
		this.cupons = carrinho.getCupons();
		this.setAtivo(true);
		this.valorTotal = carrinho.getTotal();
		this.setDataCadastro(LocalDate.now());
		this.status = EnumStatusPedido.EM_PROCESSAMENTO.toString();
		
		Log.loggar("Logando as informações do PEDIDO no construtor da classe Pedido: ValorTotal: " + this.getValorTotal());
		
		for (ItemCarrinho item : carrinho.getItens()) {
			ItemPedido i = new ItemPedido();
			i.setPedido(this);
			i.setItem(item.getItem());
			i.setQuantidade(item.getQuantidade());
			i.setSubtotal(item.getSubtotal());
			itens.add(i);
		}
		
		for (int i = 0; i < this.cupons.size(); i++) {
			this.cupons.get(i).setAtivo(false);
		}
	}
}
