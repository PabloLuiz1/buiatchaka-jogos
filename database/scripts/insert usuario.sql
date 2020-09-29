INSERT INTO public.cliente(
	cpf, data_nascimento, data_ultimo_login, email, genero, nome, rg, senha, telefone)
	VALUES ('31133269583', '31/01/1995', now(), 'joaosilva@gmail.com', 'MASCULINO', 'João Silva', '878745623', 'joaosilva123', '11978894876');
INSERT INTO public.endereco(
	bairro, cep, cidade, estado, logradouro, nome, numero, cliente_id)
	VALUES ( 'Jardim Brasil', '01234567', 'São Paulo', 'SP', 'Avenida Brasil', 'Casa', '123', 1);
INSERT INTO public.endereco(
	bairro, cep, cidade, estado, logradouro, nome, numero, cliente_id)
	VALUES ( 'Bela Vista', '98765432', 'São Paulo', 'SP', 'Avenida Paulista', 'Trabalho', '3123', 1);
INSERT INTO public.endereco(
	bairro, cep, cidade, estado, logradouro, nome, numero, cliente_id)
	VALUES ( 'Centro', '01478523', 'Ferraz de Vasconcelos', 'SP', 'Travessa dos Gorinos', 'Casa da vó', '95', 1);
INSERT INTO public.cartao(
	data_vencimento, bandeira, codigo, cpf_titular, nome_titular, numero, cliente_id)
	VALUES ('2028-02-01', 'MasterCard', '551', '12345678910', 'João da Silva', '5261400319746371', 1);
INSERT INTO public.cartao(
	data_vencimento, bandeira, codigo, cpf_titular, nome_titular, numero, cliente_id)
	VALUES ('2023-04-01', 'Visa', '421', '12345678910', 'João da Silva', '4929145634524261', 1);
INSERT INTO public.cartao(
	data_vencimento, bandeira, codigo, cpf_titular, nome_titular, numero, cliente_id)
	VALUES ('2025-12-01', 'MasterCard', '512', '12345678910', 'João da Silva', '4716402666880187', 1);
INSERT INTO public.cupom(
	data_vencimento, codigo, valor, cliente_id)
	VALUES ('2020-12-31', 'TROCA123', 199.90, 1);
INSERT INTO public.cupom(
	data_vencimento, codigo, valor, cliente_id)
	VALUES ('2020-12-31', 'TROCA456', 19.90, 1);