INSERT INTO public.categoria
    (
    id, ativo, data_cadastro, descricao)
VALUES
    (1, true, now(), 'Jogo para PS4');
INSERT INTO public.categoria
    (
    id, ativo, data_cadastro, descricao)
VALUES
    (2, true, now(), 'Jogo para XBOX One');
INSERT INTO public.categoria
    (
    id, ativo, data_cadastro, descricao)
VALUES
    (3, true, now(), 'Jogo para PC');
-----------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------
INSERT INTO public.produto
    (
    id, ativo, data_cadastro, descricao, nome, preco_compra, preco_venda, categoria_id)
VALUES
    (1, true, now(), 'Um novo começo ousado: sua vingança contra os deuses do Olimpo agora é passado e Kratos vive como um homem comum no reino dos monstros e deuses nórdicos. 
    E é nesse mundo inóspito e implacável que ele precisa lutar para sobreviver... e ensinar seu filho a fazer o mesmo. 
    Essa nova versão surpreendente de God of War desconstrói os principais elementos que definiram a série (combates gratificantes, proporção espetacular e uma narrativa poderosa), 
    combinando-os de uma forma diferente.', 'God of War', 29.90, 119.90, 1);
INSERT INTO public.produto
    (
    id, ativo, data_cadastro, descricao, nome, preco_compra, preco_venda, categoria_id)
VALUES
    (2, true, now(), 'Cinco anos depois da jornada perigosa pelos Estados Unidos pós-pandêmicos, 
    Ellie e Joel se estabelecem em Jackson, Wyoming. A vida em uma próspera comunidade de sobreviventes lhes trouxe paz e estabilidade, 
    apesar da ameaça constante dos infectados e de outros sobreviventes mais desesperados. Quando um evento violento interrompe essa paz, 
    Ellie embarca em uma jornada implacável para fazer justiça e encontrar uma solução. Enquanto vai atrás de cada um dos responsáveis, 
    ela se confronta com as repercussões físicas e emocionais devastadoras de suas ações.', 'The Last of Us Part II', 89.90, 279.90, 1);
INSERT INTO public.produto
    (
    id, ativo, data_cadastro, descricao, nome, preco_compra, preco_venda, categoria_id)
VALUES
    (3, true, now(), 'Esse não é o Spider-Man que você conhece ou já viu antes. É um Peter Parker experiente, 
    que domina melhor a arte de combater os grandes criminosos na cidade de Nova York. Ao mesmo tempo, 
    ele está tendo dificuldades para equilibrar sua vida pessoal caótica e sua carreira, 
    tudo isso enquanto o destino da Nova York da Marvel está em suas mãos.', 'Marvels Spider-Man: Game of the Year Edition', 49.90, 159.90, 1);
INSERT INTO public.produto
    (
    id, ativo, data_cadastro, descricao, nome, preco_compra, preco_venda, categoria_id)
VALUES
    (4, true, now(), 'Com a tecnologia Frostbite™, o EA SPORTS™ FIFA 20 dá vida a dois aspectos do Maior Jogo do Mundo: 
    o prestígio dos gramados profissionais e uma nova experiência realista de futebol de rua com EA SPORTS VOLTA.', 'EA SPORTS™ FIFA 20', 69.90, 199.90, 2);
INSERT INTO public.produto
    (
    id, ativo, data_cadastro, descricao, nome, preco_compra, preco_venda, categoria_id)
VALUES
    (5, true, now(), 'Quando um malandro de rua, um ladrão de bancos aposentado e um psicopata aterrorizante se envolvem com alguns dos criminosos mais 
    assustadores e loucos do submundo, o governo dos EUA e a indústria do entretenimento, eles devem realizar golpes ousados para sobreviver na cruel cidade, 
    onde não podem confiar em ninguém, nem mesmo entre si.', 'Grand Theft Auto V', 49.90, 149.90, 2);
INSERT INTO public.produto
    (
    id, ativo, data_cadastro, descricao, nome, preco_compra, preco_venda, categoria_id)
VALUES
    (6, true, now(), 'Marvel\''s Avengers é um jogo épico de ação e aventura em terceira pessoa que combina uma história original cinematográfica com jogabilidade individual e 
    cooperativa*. Monte uma equipe com até quatro jogadores online, domine habilidades extraordinárias, 
    personalize um elenco crescente de heróis e proteja a Terra de ameaças cada vez maiores. 
    Em um futuro onde os super-heróis foram proibidos, a jovem Kamala Khan precisa reunir os Avengers para deter a AIM. Marvel\''s Avengers expande a jornada épica com novos 
    heróis e histórias adicionais com lançamentos periódicos, oferecendo a experiência definitiva de jogo com os Avengers.', 'Marvel\''s Avengers', 79.90, 249.90, 2);
INSERT INTO public.produto
    (
    id, ativo, data_cadastro, descricao, nome, preco_compra, preco_venda, categoria_id)
VALUES
    (7, true, now(), 'Counter-Strike: Global Offensive (CS: GO) expandirá na jogabilidade de ação baseada em equipes na qual foi pioneiro quando foi lançado há 12 anos. 
    CS: GO contém novos mapas, personagens e armas, 
    além de conter versões atualizadas de conteúdos do CS clássico (como de_dust2).', 'Counter-Strike: Global Offensive', 15.90, 69.90, 3);
INSERT INTO public.produto
    (
    id, ativo, data_cadastro, descricao, nome, preco_compra, preco_venda, categoria_id)
VALUES
    (8, true, now(), 'Travel across Europe as king of the road, a trucker who delivers important cargo across impressive distances! 
    With dozens of cities to explore, your endurance, skill and speed will all be pushed to their limits.', 'Euro Truck Simulator 2', 8.90, 24.90, 3);
INSERT INTO public.produto
    (
    id, ativo, data_cadastro, descricao, nome, preco_compra, preco_venda, categoria_id)
VALUES
    (9, true, now(), 'SAIA NAS MANCHETES no F1® 2018. F1® 2018 é o videogame oficial da TEMPORADA 2018 DO CAMPEONATO DE FÓRMULA 1 DA FIA™. 
    Mergulhe no mundo da Fórmula 1® mais do que nunca com o lançamento mundial do F1 2018 no dia 24 de agosto de 2018 (sexta-feira).', 'F1 2018', 12.90, 54.90, 3);
-----------------------------------------------------------------------------------------
INSERT INTO public.item_estoque(
	id, ativo, data_cadastro, quantidade, produto_id)
	VALUES (1, true, now(), 10, 1);
INSERT INTO public.item_estoque(
	id, ativo, data_cadastro, quantidade, produto_id)
	VALUES (2, true, now(), 10, 2);
INSERT INTO public.item_estoque(
	id, ativo, data_cadastro, quantidade, produto_id)
	VALUES (3, true, now(), 10, 3);
INSERT INTO public.item_estoque(
	id, ativo, data_cadastro, quantidade, produto_id)
	VALUES (4, true, now(), 10, 4);
INSERT INTO public.item_estoque(
	id, ativo, data_cadastro, quantidade, produto_id)
	VALUES (5, true, now(), 10, 5);    
INSERT INTO public.item_estoque(
	id, ativo, data_cadastro, quantidade, produto_id)
	VALUES (6, true, now(), 10, 6);
INSERT INTO public.item_estoque(
	id, ativo, data_cadastro, quantidade, produto_id)
	VALUES (7, true, now(), 10, 7);
INSERT INTO public.item_estoque(
	id, ativo, data_cadastro, quantidade, produto_id)
	VALUES (8, true, now(), 1, 8);
INSERT INTO public.item_estoque(
	id, ativo, data_cadastro, quantidade, produto_id)
	VALUES (9, true, now(), 1, 9);    