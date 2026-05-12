CREATE DATABASE icomprasprodutos;

CREATE TABLE produtos (
	codigo serial NOT NULL PRIMARY KEY,
	nome varchar(100) NOT NULL,
	valor_unitario decimal(16,2) NOT NULL
);

CREATE DATABASE icomprasclientes;

CREATE TABLE clientes (
codigo serial NOT NULL PRIMARY KEY,
nome varchar(150) NOT NULL,
cpf char(11) NOT NULL,
logradouro varchar(100),
numero varchar(10),
bairro varchar(100),
email varchar(150),
telefone varchar(20)
);

CREATE DATABASE icompraspedidos;

CREATE TABLE pedido (
	codigo serial NOT NULL PRIMARY KEY,
	codigo_cliente bigint NOT NULL,
	data_pedido timestamp NOT NULL DEFAULT now(),
	chave_pagamento text,
	observacoes text,
	status varchar(20) CHECK (status IN ('REALIZADO','PAGO','ENVIADO','ERRO_PAGAMENTO','PREPARANDO_ENVIO')),
	total decimal(16,2) NOT NULL,
	codigo_rastreio varchar(255),
	url_nf text
);

CREATE TABLE item_pedido (
	codigo serial NOT NULL PRIMARY KEY,
	codigo_pedido bigint NOT NULL REFERENCES pedido (codigo),
	codigo_produto bigint NOT NULL,
	quantidade int NOT NULL,
	valor_unitario decimal(16,2) NOT NULL
);

