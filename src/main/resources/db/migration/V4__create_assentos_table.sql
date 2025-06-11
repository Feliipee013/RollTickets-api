CREATE TABLE assentos(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    numero INT NOT NULL,
    fileira VARCHAR(10) NOT NULL,
    sala_id BIGINT,
    sessao_id BIGINT,
    FOREIGN KEY (sala_id) REFERENCES salas(id),
    FOREIGN KEY (sessao_id) REFERENCES sessoes(id)
);