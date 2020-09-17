package br.edu.fatec.buiatchaka.fachada;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import br.edu.fatec.buiatchaka.dominio.cliente.Telefone;
import br.edu.fatec.buiatchaka.negocio.IValidar;
import br.edu.fatec.buiatchaka.negocio.cartao.ValidarBandeira;
import br.edu.fatec.buiatchaka.negocio.cartao.ValidarCodigo;
import br.edu.fatec.buiatchaka.negocio.cartao.ValidarCpfTitular;
import br.edu.fatec.buiatchaka.negocio.cartao.ValidarNomeTitular;
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
import br.edu.fatec.buiatchaka.negocio.endereco.ValidarEstado;
import br.edu.fatec.buiatchaka.negocio.endereco.ValidarLogradouro;
import br.edu.fatec.buiatchaka.negocio.endereco.ValidarNomeEndereco;
import br.edu.fatec.buiatchaka.sistema.logging.Log;

public class Fachada implements IFachada {
	private Map<String, IDao> daos;
	private Map<String, List<IValidar>> regras;
	private StringBuilder sb;
	private ClienteDAO clienteDAO;
	private EnderecoDAO enderecoDAO;
	private CartaoDAO cartaoDAO;
	private TelefoneDAO telefoneDAO;

	Resultado resultado = null;
	IDao dao = null;
	String nomeClasse = null;
	List<IValidar> regra = null;

	public Fachada() {
		// Instanciando os objetos
		daos = new HashMap<String, IDao>();
		clienteDAO = new ClienteDAO();
		enderecoDAO = new EnderecoDAO();
		cartaoDAO = new CartaoDAO();
		telefoneDAO = new TelefoneDAO();
		regras = new HashMap<String, List<IValidar>>();

		sb = new StringBuilder();

		daos.put(Cliente.class.getName(), clienteDAO);
		daos.put(Endereco.class.getName(), enderecoDAO);
		daos.put(Cartao.class.getName(), cartaoDAO);
		daos.put(Telefone.class.getName(), telefoneDAO);
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

		regrasEndereco.add(validacaoNomeEndereco);
		regrasEndereco.add(validacaoLogradouro);
		regrasEndereco.add(validacaoCep);
		regrasEndereco.add(validacaoBairro);
		regrasEndereco.add(validacaoCidade);
		regrasEndereco.add(validacaoEstado);

		// Fim: Regras de Endereço

		// Início: Regras de Cartão
		List<IValidar> regrasCartao = new ArrayList<IValidar>();
		IValidar validacaoBandeira = new ValidarBandeira();
		IValidar validacaoCodigo = new ValidarCodigo();
		IValidar validacaoNomeImpresso = new ValidarNomeTitular();
		IValidar validacaoNumeroCartao = new ValidarNumeroCartao();
		IValidar validacaoCpfTitular = new ValidarCpfTitular();

		regrasCartao.add(validacaoBandeira);
		regrasCartao.add(validacaoCodigo);
		regrasCartao.add(validacaoNomeImpresso);
		regrasCartao.add(validacaoNumeroCartao);
		regrasCartao.add(validacaoCpfTitular);

		// Fim: Regras de Cartão

		regras.put(Cliente.class.getName(), regrasCliente);
		regras.put(Endereco.class.getName(), regrasEndereco);
		regras.put(Cartao.class.getName(), regrasCartao);
	}

	private String executarRegras(List<IValidar> regrasEntidade, EntidadeDominio entidade) {
		String msg = "";
		for (IValidar validacao : regrasEntidade) {
			msg = validacao.validar(entidade);
			sb.append(msg);
		}
		return msg;
	}

	@Override
	public Resultado salvar(EntidadeDominio entidade) {
		Cliente cliente = null;
		Endereco endereco = null;
		Cartao cartao = null;
		resultado = new Resultado();
		nomeClasse = entidade.getClass().getName();
		regra = regras.get(nomeClasse);
		sb.setLength(0);

		if (nomeClasse.equals(Cliente.class.getName())) {
			Log.loggar("ENTROU NO IF DA CLASSE DO CLIENTE!!!");
			cliente = (Cliente) entidade;
			sb.append(executarRegras(regra, cliente));
			if (sb.length() == 0 || sb.toString().trim().equals("")) {
				try {
					dao = daos.get(nomeClasse);
					entidade.setId(dao.salvar(cliente).getId());
					System.out.println("Salvando no banco... " + cliente.getId());
					resultado.addEntidades(cliente);
				} catch (Exception e) {
					e.printStackTrace();
					resultado.setMsg("Não foi possível salvar...");
				}
			} else {
				System.out.println("Erro encontrado...");
				resultado.addEntidades((Cliente) cliente);
			}
		}
		if (nomeClasse.equals(Endereco.class.getName())) {
			Log.loggar("ENTROU NO IF DA CLASSE DO ENDEREÇO!!!");
			endereco = (Endereco) entidade;
			sb.append(executarRegras(regra, endereco));
			if (sb.length() == 0 || sb.toString().trim().equals("")) {
				try {
					dao = daos.get(nomeClasse);
					endereco.setId(dao.salvar(endereco).getId());
					Log.loggar(("Salvando no banco... " + endereco.getId()));
					consultar(endereco.getCliente()).getEntidades().get(0);
					resultado.addEntidades(consultar(endereco.getCliente()).getEntidades().get(0));
				} catch (Exception e) {
					e.printStackTrace();
					resultado.setMsg("Não foi possível salvar...");
				}
			} else {
				System.out.println("Erro encontrado...");
				resultado.addEntidades((Endereco) endereco);
			}
		}

		if (nomeClasse.equals(Cartao.class.getName())) {
			Log.loggar("ENTROU NO IF DA CLASSE DO CARTÃO!!!");
			cartao = (Cartao) entidade;
			sb.append(executarRegras(regra, cartao));
			if (sb.length() == 0 || sb.toString().trim().equals("")) {
				try {
					dao = daos.get(nomeClasse);
					cartao.setId(dao.salvar(cartao).getId());
					Log.loggar(("Salvando no banco... " + cartao.getId()));
					consultar(cartao.getCliente()).getEntidades().get(0);
					resultado.addEntidades(consultar(cartao.getCliente()).getEntidades().get(0));
				} catch (Exception e) {
					e.printStackTrace();
					resultado.setMsg("Não foi possível salvar...");
				}
			} else {
				System.out.println("Erro encontrado...");
				resultado.addEntidades((Cartao) cartao);
			}
		}

		resultado.setMsg(sb.toString());
		Log.loggar("Printando o resultado das validações de negócio do Cliente/Endereço/Cartao na Fachada:"
				+ resultado.getMsg());
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
				dao = daos.get(nomeClasse);
				dao.alterar(entidade);
				System.out.println("Alterando no banco...");
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
	public Resultado excluir(EntidadeDominio entidade) {
		Endereco endereco;
		Cartao cartao;
		sb.setLength(0);
		resultado = new Resultado();

		nomeClasse = entidade.getClass().getName();

		dao = daos.get(nomeClasse);

		if (nomeClasse.equals(Endereco.class.getName())) {
			endereco = (Endereco) entidade;
			try {
				dao.excluir(endereco);
				System.out.println("Excluindo do banco");
				consultar(endereco.getCliente()).getEntidades().get(0);
				resultado.addEntidades(consultar(endereco.getCliente()).getEntidades().get(0));
			} catch (Exception e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível realizar a consulta...");
			}
		}

		if (nomeClasse.equals(Cartao.class.getName())) {
			cartao = (Cartao) entidade;
			try {
				dao.excluir(cartao);
				System.out.println("Excluindo do banco");
				consultar(cartao.getCliente()).getEntidades().get(0);
				resultado.addEntidades(consultar(cartao.getCliente()).getEntidades().get(0));
			} catch (Exception e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível realizar a consulta...");
			}
		}

		return resultado;
	}

	@Override
	public Resultado listar(EntidadeDominio entidade) {
		List<? extends EntidadeDominio> clientes = null;
		List<? extends EntidadeDominio> enderecos = null;
		List<? extends EntidadeDominio> cartoes = null;
		resultado = new Resultado();

		nomeClasse = entidade.getClass().getName();
		sb.setLength(0);

		if (nomeClasse.equals(Cliente.class.getName())) {
			Cliente cli = (Cliente) entidade;
			try {
				dao = daos.get(nomeClasse);
				cartoes = dao.listar(cli);
				enderecos = dao.listar(cli);
				for (EntidadeDominio e : cartoes) {
					resultado.addEntidades(e);
				}
			} catch (Exception e) {
				e.printStackTrace();
				Log.loggar("Não foi possível salvar...");
			}
		}

		if (nomeClasse.equals(Cartao.class.getName())) {
			Cliente cli = (Cliente) entidade;
			try {
				dao = daos.get(nomeClasse);
				clientes = dao.listar(cli);
				for (EntidadeDominio e : clientes) {
					resultado.addEntidades(e);
				}
			} catch (Exception e) {
				e.printStackTrace();
				Log.loggar("Não foi possível salvar...");
			}
		}

		if (nomeClasse.equals(Endereco.class.getName())) {
			Cliente cli = (Cliente) entidade;

			try {
				dao = daos.get(nomeClasse);
				enderecos = dao.listar(cli);
				for (EntidadeDominio e : enderecos) {
					resultado.addEntidades(e);
				}
			} catch (Exception e) {
				e.printStackTrace();
				Log.loggar("Não foi possível salvar...");
			}
		}

		return resultado;
	}

	@Override
	public Resultado consultar(EntidadeDominio entidade) {
		Endereco endereco = null;
		Cliente cliente = null;
		Cartao cartao = null;
		sb.setLength(0);
		resultado = new Resultado();

		nomeClasse = entidade.getClass().getName();
		if (nomeClasse.equals(Endereco.class.getName())) {
			try {
				dao = daos.get(nomeClasse);
				endereco = (Endereco) dao.consultar(entidade);
				Log.loggar("Consultando no banco...");
				resultado.addEntidades(endereco);
			} catch (Exception e) {
				e.printStackTrace();
				Log.loggar("Não foi possível consultar...");
			}

		}
		return resultado;
	}

}
