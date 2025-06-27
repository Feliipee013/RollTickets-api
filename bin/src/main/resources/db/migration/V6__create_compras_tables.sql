CREATE TABLE compras(
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    cliente_id BIGINT,
    data_hora DATETIME,
    FOREIGN KEY (cliente_id) REFERENCES clientes(id)
);