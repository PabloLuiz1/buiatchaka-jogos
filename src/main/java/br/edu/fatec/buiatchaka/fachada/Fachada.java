package br.edu.fatec.buiatchaka.fachada;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.fatec.buiatchaka.dao.IDao;
import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.Resultado;
import br.edu.fatec.buiatchaka.dominio.cliente.Cartao;
import br.edu.fatec.buiatchaka.dominio.cliente.Cliente;
import br.edu.fatec.buiatchaka.dominio.cliente.Endereco;
import br.edu.fatec.buiatchaka.dominio.cliente.Telefone;
import br.edu.fatec.buiatchaka.negocio.IValidar;
import br.edu.fatec.buiatchaka.negocio.cartao.ValidarBandeira;
import br.edu.fatec.buiatchaka.negocio.cartao.ValidarCodigo;
import br.edu.fatec.buiatchaka.negocio.cartao.ValidarNomeImpresso;
import br.edu.fatec.buiatchaka.negocio.cartao.ValidarNumeroCartao;
import br.edu.fatec.buiatchaka.negocio.cliente.ValidarCpf;
import br.edu.fatec.buiatchaka.negocio.cliente.ValidarDataNascimento;
import br.edu.fatec.buiatchaka.negocio.cliente.ValidarEmail;
import br.edu.fatec.buiatchaka.negocio.cliente.ValidarGenero;
import br.edu.fatec.buiatchaka.negocio.cliente.ValidarNome;
import br.edu.fatec.buiatchaka.negocio.cliente.ValidarRg;
import br.edu.fatec.buiatchaka.negocio.cliente.ValidarSenha;
import br.edu.fatec.buiatchaka.negocio.endereco.ValidarBairro;
import br.edu.fatec.buiatchaka.negocio.endereco.ValidarCep;
import br.edu.fatec.buiatchaka.negocio.endereco.ValidarCidade;
import br.edu.fatec.buiatchaka.negocio.endereco.ValidarComplemento;
import br.edu.fatec.buiatchaka.negocio.endereco.ValidarEstado;
import br.edu.fatec.buiatchaka.negocio.endereco.ValidarLogradouro;
import br.edu.fatec.buiatchaka.negocio.endereco.ValidarNomeEndereco;
import br.edu.fatec.buiatchaka.negocio.endereco.ValidarTipoEndereco;
import br.edu.fatec.buiatchaka.negocio.telefone.ValidarDdd;
import br.edu.fatec.buiatchaka.negocio.telefone.ValidarNumeroTelefone;
import br.edu.fatec.buiatchaka.negocio.telefone.ValidarTipoTelefone;
import br.edu.fatec.buiatchaka.repository.CartaoRepository;
import br.edu.fatec.buiatchaka.repository.ClienteRepository;
import br.edu.fatec.buiatchaka.repository.EnderecoRepository;
import br.edu.fatec.buiatchaka.repository.TelefoneRepository;

public class Fachada implements IFachada {
	@Autowired
	private Map<String, JpaRepository<? extends EntidadeDominio, Long>> daos;
	@Autowired
	private Map<String, List<IValidar>> regras;
	@Autowired
	private StringBuilder sb;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private CartaoRepository cartaoRepository;
	@Autowired
	private TelefoneRepository telefoneRepository;
	
	Resultado resultado = null;
	IDao dao = null;
	String nomeClasse = null;
	List<IValidar> regra = null;

	public Fachada() {
		daos.put(Cliente.class.getName(), clienteRepository);
		daos.put(Endereco.class.getName(), enderecoRepository);
		daos.put(Cartao.class.getName(), cartaoRepository);
		daos.put(Cartao.class.getName(), telefoneRepository);

		// Início: Regras de Cliente 
		IValidar validacaoNomeCliente = new ValidarNome();
		IValidar validacaoGenero = new ValidarGenero();
		IValidar validacaoCpf = new ValidarCpf();
		IValidar validacaoRg = new ValidarRg();
		IValidar validacaoEmail = new ValidarEmail();
		IValidar validacaoDataNascimento = new ValidarDataNascimento();
		IValidar validacaoSenha = new ValidarSenha();

		List<IValidar> regrasCliente = new ArrayList<IValidar>();
		regrasCliente.add(validacaoNomeCliente);
		regrasCliente.add(validacaoGenero);
		regrasCliente.add(validacaoCpf);
		regrasCliente.add(validacaoRg);
		regrasCliente.add(validacaoEmail);
		regrasCliente.add(validacaoDataNascimento);
		regrasCliente.add(validacaoSenha);

		
		// Fim: Regras de Cliente
		
		// Início: Regras de Endereço
		List<IValidar> regrasEndereco = new ArrayList<IValidar>();
		IValidar validacaoTipoEndereco = new ValidarTipoEndereco();
		IValidar validacaoNomeEndereco = new ValidarNomeEndereco();
		IValidar validacaoLogradouro = new ValidarLogradouro();
		IValidar validacaoNumero = new ValidarNumeroTelefone();
		IValidar validacaoCep = new ValidarCep();
		IValidar validacaoBairro = new ValidarBairro();
		IValidar validacaoCidade = new ValidarCidade();
		IValidar validacaoEstado = new ValidarEstado();
		IValidar validacaoComplemento = new ValidarComplemento();

		regrasEndereco.add(validacaoTipoEndereco);
		regrasEndereco.add(validacaoNomeEndereco);
		regrasEndereco.add(validacaoLogradouro);
		regrasEndereco.add(validacaoNumero);
		regrasEndereco.add(validacaoCep);
		regrasEndereco.add(validacaoBairro);
		regrasEndereco.add(validacaoCidade);
		regrasEndereco.add(validacaoEstado);
		regrasEndereco.add(validacaoComplemento);
		
		// Fim: Regras de Endereço
		
		// Início: Regras de Cartão
		List<IValidar> regrasCartao = new ArrayList<IValidar>();
		IValidar validacaoBandeira = new ValidarBandeira();
		IValidar validacaoCodigo = new ValidarCodigo();
		IValidar validacaoNomeImpresso = new ValidarNomeImpresso();
		IValidar validacaoNumeroCartao = new ValidarNumeroCartao();
		
		regrasCartao.add(validacaoBandeira);
		regrasCartao.add(validacaoCodigo);
		regrasCartao.add(validacaoNomeImpresso);
		regrasCartao.add(validacaoNumeroCartao);

		// Fim: Regras de Cartão
		
		// Início: Regras de Telefone
		List<IValidar> regrasTelefone = new ArrayList<IValidar>();
		IValidar validacaoTipoTelefone = new ValidarTipoTelefone();
		IValidar validacaoDdd = new ValidarDdd();
		IValidar validacaoNumeroTelefone = new ValidarNumeroTelefone();

		regrasTelefone.add(validacaoDdd);
		regrasTelefone.add(validacaoTipoTelefone);
		regrasTelefone.add(validacaoNumeroTelefone);
		
		//Fim: Regras de Telefone

		regras.put(Cliente.class.getName(), regrasCliente);
		regras.put(Endereco.class.getName(), regrasEndereco);
		regras.put(Cartao.class.getName(), regrasCartao);
		regras.put(Telefone.class.getName(), regrasTelefone);
	}

	private void executarRegras(List<IValidar> regrasEntidade, EntidadeDominio entidade) {
		String msg = "";
		for (IValidar validacao : regrasEntidade) {
			msg = validacao.validar(entidade);
			sb.append(msg);
		}
	}

	@Override
	public Resultado salvar(EntidadeDominio entidade) {
		resultado = new Resultado();
		nomeClasse = entidade.getClass().getName();
		regra = regras.get(nomeClasse);
		sb.setLength(0);

		executarRegras(regra, entidade);

		// verificar se é um cliente pq cliente tem q verificar alem dos dados dele
		// tem q validar os dados de end, senha e usu
		if (nomeClasse == Cliente.class.getName()) {
			Cliente cliente = (Cliente) entidade;
			// executar regras dos endereços
			List<IValidar> regrasTelefone = regras.get(Telefone.class.getName());
			executarRegras(regrasTelefone, cliente.getTelefone());
		}

		System.out.println("Erro:" + sb.toString());
		// se tem msg de erro ele não salva
		if (sb.length() == 0 || sb.toString().trim().equals("")) {
			try {
//				dao = daos.get(nomeClasse);
				dao.salvar(entidade);
				System.out.println("Salvando no banco...");
				resultado.addEntidades(entidade);
			} catch (Exception e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível salvar...");
			}
		} else {
			System.out.println("Erro encontrado...");
			resultado.addEntidades(entidade);
			resultado.setMsg(sb.toString());
		}
		return resultado;
	}

	@Override
	public Resultado alterar(EntidadeDominio entidade) {
		resultado = new Resultado();
		nomeClasse = entidade.getClass().getName();
		regra = regras.get(nomeClasse);
		sb.setLength(0);

		executarRegras(regra, entidade);
		System.out.println("Erro:" + sb.toString());

		// se tem msg de erro ele não salva
		if (sb.length() == 0 || sb.toString().trim().equals("")) {
			try {
//				dao = daos.get(nomeClasse);
				dao.alterar(entidade);
				System.out.println("alterando no banco....");
			} catch (Exception e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível Salvar...");
			}
		} else {
			System.out.println("erro encontrado....");
			resultado.addEntidades(entidade);
			resultado.setMsg(sb.toString());
		}

		return resultado;
	}

	@Override
	public Resultado excluir(EntidadeDominio entidade) {
		sb.setLength(0);
		resultado = new Resultado();

		nomeClasse = entidade.getClass().getName();

//		dao = daos.get(nomeClasse);

		try {
			dao.excluir(entidade);
			System.out.println("excluindo do banco");
		} catch (Exception e) {
			e.printStackTrace();
			resultado.setMsg("Não foi possível realizar a consulta...");
		}

		return resultado;
	}

	@Override
	public Resultado consultar(EntidadeDominio entidade) {
		sb.setLength(0);
		resultado = new Resultado();

		nomeClasse = entidade.getClass().getName();

//		dao = daos.get(nomeClasse);

		try {
			resultado.setEntidades(dao.consultar(entidade));
			System.out.println("consultando no banco");
		} catch (Exception e) {
			e.printStackTrace();
			resultado.setMsg("Não foi possível realizar a consulta...");
		}
		resultado.addEntidades(entidade);

		return resultado;
	}

}
