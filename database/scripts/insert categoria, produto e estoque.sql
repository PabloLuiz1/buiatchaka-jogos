INSERT INTO public.categoria
    (
    id, ativo, data_cadastro, descricao)
VALUES
    (1, true, now(), 'Jogo para PS4'),
    (2, true, now(), 'Jogo para XBOX One'),
    (3, true, now(), 'Jogo para PC');
-----------------------------------------------------------------------------------------
INSERT INTO public.produto
    (
    id, ativo, data_cadastro, codigo_de_barras, descricao, faixa_etaria, genero, grupo_precificacao, jogadores_offline, jogadores_online, marca, nome, plataforma, preco_compra, preco_venda, tipo_de_midia, categoria_id)
VALUES
    (1, true, now(), '1277365409876', 'Um novo começo ousado: sua vingança contra os deuses do Olimpo agora é passado e Kratos vive como um homem comum no reino dos monstros e deuses nórdicos. 
    E é nesse mundo inóspito e implacável que ele precisa lutar para sobreviver... e ensinar seu filho a fazer o mesmo. 
    Essa nova versão surpreendente de God of War desconstrói os principais elementos que definiram a série (combates gratificantes, proporção espetacular e uma narrativa poderosa), 
    combinando-os de uma forma diferente.', 18, 'ACAO', 55.5, 1, 0, 'Sony', 'God of War', 'PS4', 29.90, 119.90, 'Blu-ray', 1),
    (2, true, now(), '543223549441', 'Cinco anos depois da jornada perigosa pelos Estados Unidos pós-pandêmicos, 
    Ellie e Joel se estabelecem em Jackson, Wyoming. A vida em uma próspera comunidade de sobreviventes lhes trouxe paz e estabilidade, 
    apesar da ameaça constante dos infectados e de outros sobreviventes mais desesperados. Quando um evento violento interrompe essa paz, 
    Ellie embarca em uma jornada implacável para fazer justiça e encontrar uma solução. Enquanto vai atrás de cada um dos responsáveis, 
    ela se confronta com as repercussões físicas e emocionais devastadoras de suas ações.', 18, 'ACAO', 55.5, 1, 16, 'Sony', 'The Last of Us Part II', 'PS4', 89.90, 279.90, 'Blu-ray', 1),
    (3, true, now(), '846031082901', 'Esse não é o Spider-Man que você conhece ou já viu antes. É um Peter Parker experiente, 
    que domina melhor a arte de combater os grandes criminosos na cidade de Nova York. Ao mesmo tempo, 
    ele está tendo dificuldades para equilibrar sua vida pessoal caótica e sua carreira, 
    tudo isso enquanto o destino da Nova York da Marvel está em suas mãos.', 14, 'ACAO', 30, 1, 0, 'Sony', 'Marvels Spider-Man: Game of the Year Edition', 'PS4', 49.90, 159.90, 'Blu-ray', 1),
    (4, true, now(), '001239844653', 'Com a tecnologia Frostbite™, o EA SPORTS™ FIFA 20 dá vida a dois aspectos do Maior Jogo do Mundo: 
    o prestígio dos gramados profissionais e uma nova experiência realista de futebol de rua com EA SPORTS VOLTA.', 10, 'ESPORTE', 40, 4, 22, 'Eletronic Arts', 'EA SPORTS™ FIFA 20', 'XBOX One', 69.90, 199.90, 'DVD', 2),
    (5, true, now(), '874653100293', 'Quando um malandro de rua, um ladrão de bancos aposentado e um psicopata aterrorizante se envolvem com alguns dos criminosos mais 
    assustadores e loucos do submundo, o governo dos EUA e a indústria do entretenimento, eles devem realizar golpes ousados para sobreviver na cruel cidade, 
    onde não podem confiar em ninguém, nem mesmo entre si.', 18, 'ACAO', 50, 1, 32, 'Rockstar', 'Grand Theft Auto V', 'XBOX One', 49.90, 149.90, 'DVD', 2),
    (6, true, now(), '443627818906', 'Marvel\\''s Avengers é um jogo épico de ação e aventura em terceira pessoa que combina uma história original cinematográfica com jogabilidade individual e 
    cooperativa*. Monte uma equipe com até quatro jogadores online, domine habilidades extraordinárias, 
    personalize um elenco crescente de heróis e proteja a Terra de ameaças cada vez maiores. 
    Em um futuro onde os super-heróis foram proibidos, a jovem Kamala Khan precisa reunir os Avengers para deter a AIM. Marvel\\''s Avengers expande a jornada épica com novos 
    heróis e histórias adicionais com lançamentos periódicos, oferecendo a experiência definitiva de jogo com os Avengers.', 14, 'AVENTURA', 20, 2, 4, 'Square Enix', 'Marvel\\''s Avengers', 'XBOX One', 79.90, 249.90, 'DVD', 2),
    (7, true, now(), '234189776430', 'Counter-Strike: Global Offensive (CS: GO) expandirá na jogabilidade de ação baseada em equipes na qual foi pioneiro quando foi lançado há 12 anos. 
    CS: GO contém novos mapas, personagens e armas, 
    além de conter versões atualizadas de conteúdos do CS clássico (como de_dust2).', 18, 'FPS', 15, 1, 32, 'Valve', 'Counter-Strike: Global Offensive', 'PC', 15.90, 69.90, 'DVD', 3),
    (8, true, now(), '77465329871', 'Travel across Europe as king of the road, a trucker who delivers important cargo across impressive distances! 
    With dozens of cities to explore, your endurance, skill and speed will all be pushed to their limits.', 10, 'SIMULACAO', 45, 1, 0, 'SCS Software', 'Euro Truck Simulator 2', 'PC', 8.90, 24.90, 'DVD', 3),
    (9, true, now(), '543567265310', 'SAIA NAS MANCHETES no F1® 2018. F1® 2018 é o videogame oficial da TEMPORADA 2018 DO CAMPEONATO DE FÓRMULA 1 DA FIA™. 
    Mergulhe no mundo da Fórmula 1® mais do que nunca com o lançamento mundial do F1 2018 no dia 24 de agosto de 2018 (sexta-feira).', 10, 'SIMULACAO', 25, 2, 24, 'Codemasters', 'F1 2018', 'PC', 12.90, 54.90, 'DVD', 3);
-----------------------------------------------------------------------------------------
INSERT INTO public.item_estoque(
	id, ativo, data_cadastro, quantidade, produto_id)
	VALUES 
    (1, true, now(), 10, 1),
    (2, true, now(), 10, 2),
	(3, true, now(), 10, 3),
    (4, true, now(), 10, 4),
	(5, true, now(), 10, 5),
    (6, true, now(), 10, 6),
    (7, true, now(), 10, 7),
    (8, true, now(), 1, 8),
    (9, true, now(), 1, 9);