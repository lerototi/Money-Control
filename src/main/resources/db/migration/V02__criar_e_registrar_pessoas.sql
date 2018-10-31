CREATE TABLE pessoa (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    ativo BOOLEAN NOT NULL,
    logradouro VARCHAR(100),
    numero BIGINT(10),
    bairro VARCHAR(25),
    cidade VARCHAR(25),
    estado VARCHAR(25),
    cep VARCHAR(15)
)	ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO pessoa (nome, ativo, logradouro, numero, bairro, cidade, estado, cep) values ('Leonardo',true, 'Cond. Beija-Flor', 2, 'Sobradinho', 'Brasília', 'Distrito Federal', '73090-907'); 
INSERT INTO pessoa (nome, ativo, logradouro, numero, bairro, cidade, estado, cep) values ('Beatriz',true, 'Asa Norte', 1, 'Plano Piloto', 'Brasília', 'Distrito Federal', '73090-000'); 
INSERT INTO pessoa (nome, ativo, logradouro, numero, bairro, cidade, estado, cep) values ('Frango',true, 'Cond. Beija-Flor', 10, 'Sobradinho', 'Brasília', 'Distrito Federal', '73090-907'); 
INSERT INTO pessoa (nome, ativo, logradouro, numero, bairro, cidade, estado, cep) values ('Fabio',true, 'Cond. Beija-Flor', 4, 'Sobradinho', 'Brasília', 'Distrito Federal', '73090-907'); 
INSERT INTO pessoa (nome, ativo, logradouro, numero, bairro, cidade, estado, cep) values ('Marcio',true, 'Asa Norte', 1, 'Plano Piloto', 'Brasília', 'Distrito Federal', '73090-907'); 
INSERT INTO pessoa (nome, ativo, logradouro, numero, bairro, cidade, estado, cep) values ('Filipe',true, 'Quadra 01', 1, 'Sobradinho', 'Brasília', 'Distrito Federal', '73090-907'); 
INSERT INTO pessoa (nome, ativo, logradouro, numero, bairro, cidade, estado, cep) values ('Isadora',true, 'Alto da Boa vista', 20, 'Sobradinho', 'Brasília', 'Distrito Federal', '73090-907'); 
