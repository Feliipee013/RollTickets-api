CREATE TABLE ingressos(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    assento VARCHAR(10) NOT NULL,
    sessao_id BIGINT,
    cliente_id BIGINT,
    tipo_ingresso ENUM('2D', '3D') NOT NULL,
    FOREIGN KEY (sessao_id) REFERENCES sessoes(id),
    FOREIGN KEY (cliente_id) REFERENCES clientes(id)
);