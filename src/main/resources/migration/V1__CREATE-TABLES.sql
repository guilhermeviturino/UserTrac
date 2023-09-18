CREATE TABLE IF NOT EXISTS tb_usuarios(
    idUsuario BIGINT NOT NULL AUTO_INCREMENT,
    login VARCHAR(50) NOT NULL,
    senha VARCHAR(50) NOT NULL,
    status TEXT,
    PRIMARY KEY(idUsuario)
);

CREATE TABLE IF NOT EXISTS tb_clientes(
    idCliente BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    sobrenome VARCHAR(255) NOT NULL,
    cpf VARCHAR(15) NOT NULL,
    dataNascimento DATE,
    telefone VARCHAR(12) NOT NULL,
    email VARCHAR(255),
    PRIMARY KEY(idCliente)
);

CREATE TABLE IF NOT EXISTS tb_ordemservicos(
    idOrdemServico BIGINT NOT NULL AUTO_INCREMENT,
    idCliente BIGINT NOT NULL,
    dataAbertura DATE,
    descricaoProblema TEXT,
    descricaoSolucao TEXT,
    status TEXT,
    PRIMARY KEY(idOrdemServico),
    FOREIGN KEY(idCliente) REFERENCES tb_clientes(idCliente)
);

CREATE TABLE IF NOT EXISTS tb_instalacoes(
    idInstalacao BIGINT NOT NULL AUTO_INCREMENT,
    idCliente BIGINT NOT NULL,
    valor DOUBLE,
    dataAgendada DATE,
    status TEXT,
    PRIMARY KEY(idInstalacao),
    FOREIGN KEY(idCliente) REFERENCES tb_clientes(idCliente)
);