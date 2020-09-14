package br.edu.fatec.buiatchaka.fachada;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.fatec.buiatchaka.dao.CartaoDAO;
import br.edu.fatec.buiatchaka.dao.ClienteDAO;
import br.edu.fatec.buiatchaka.dao.EnderecoDAO;
import br.edu.fatec.buiatchaka.dao.IDao;
import br.edu.fatec.buiatchaka.dao.TelefoneDAO;
import br.edu.fatec.buiatchaka.dominio.EntidadeDominio;
import br.edu.fatec.buiatchaka.dominio.Resultado;
import br.edu.fatec.buiatchaka.dominio.cliente.Cartao;
import br.edu.fatec.buiatchaka.dominio.cliente.Cliente;
import br.edu.fatec.buiatchaka.dominio.cliente.Endereco;
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
import br.edu.fatec.buiatchaka.repository.BandeiraRepository;
import br.edu.fatec.buiatchaka.repository.ClienteRepository;

public class FachadaVelha implements IFachada {
	@Autowired
	ClienteRepository repository;
	@Autowired
	private Map<String, IDao> daos;
	@Autowired
	private Map<String, List<IValidar>> regras;
	@Autowired
	private StringBuilder sb;
	@Autowired
	private ClienteDAO clienteDAO;
	@Autowired
	private EnderecoDAO enderecoDAO;
	@Autowired
	private CartaoDAO cartaoDAO;
	@Autowired
	private TelefoneDAO telefoneDAO;
	@Autowired
	private BandeiraRepository bandeiraRepository;

	Resultado resultado = null;
	IDao dao = null;
	String nomeClasse = null;
	List<IValidar> regra = null;

	public FachadaVelha() {
		daos.put(Cliente.class.getName(), clienteDAO);
		daos.put(Endereco.class.getName(), enderecoDAO);
		daos.put(Cartao.class.getName(), cartaoDAO);
		daos.put(Cartao.class.getName(), telefoneDAO);

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
		IValidar validacaoNomeEndereco = new ValidarNomeEndereco();
		IValidar validacaoLogradouro = new ValidarLogradouro();
		IValidar validacaoCep = new ValidarCep();
		IValidar validacaoBairro = new ValidarBairro();
		IValidar validacaoCidade = new ValidarCidade();
		IValidar validacaoEstado = new ValidarEstado();
		IValidar validacaoComplemento = new ValidarComplemento();

		regrasEndereco.add(validacaoNomeEndereco);
		regrasEndereco.add(validacaoLogradouro);
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

		regras.put(Cliente.class.getName(), regrasCliente);
		regras.put(Endereco.class.getName(), regrasEndereco);
		regras.put(Cartao.class.getName(), regrasCartao);
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
//		if (nomeClasse == Cliente.class.getName()) {
//			Cliente cliente = (Cliente) entidade;
//			// executar regras dos endereços
//			List<IValidar> regrasTelefone = regras.get(Telefone.class.getName());
//			if (sb.length() == 0 || sb.toString().trim().equals("")) {
//				try {
//					dao = daos.get(nomeClasse);
//					repository.save(cliente);
//					dao.salvar(cliente);
//					System.out.println("Salvando no banco...");
//					resultado.addEntidades(entidade);
//				} catch (Exception e) {
//					e.printStackTrace();
//					resultado.setMsg("Não foi possível salvar...");
//				}
//			} else {
//				System.out.println("Erro encontrado...");
//				resultado.addEntidades(entidade);
//				resultado.setMsg(sb.toString());
//			}
////			executarRegras(regrasTelefone, cliente.getTelefones());
//		}

		System.out.println("Erro:" + sb.toString());
		// se tem msg de erro ele não salva

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
			resultado.setEntidades(dao.listar(entidade));
			System.out.println("consultando no banco");
		} catch (Exception e) {
			e.printStackTrace();
			resultado.setMsg("Não foi possível realizar a consulta...");
		}
		resultado.addEntidades(entidade);

		return resultado;
	}

}
