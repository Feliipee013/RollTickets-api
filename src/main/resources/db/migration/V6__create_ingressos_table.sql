CREATE TABLE ingressos(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    preco decimal(5,2),
    assento_id BIGINT,
    sessao_id BIGINT,
    cliente_id BIGINT,
    FOREIGN KEY (sessao_id) REFERENCES sessoes(id),
    FOREIGN KEY (cliente_id) REFERENCES clientes(id),
    FOREIGN KEY (assento_id) REFERENCES assentos(id)
);