CREATE TABLE assentos_sessao(
        id BIGINT PRIMARY KEY AUTO_INCREMENT,
        assento_id BIGINT,
        FOREIGN KEY (assento_id) REFERENCES assentos(id),
        sessao_id BIGINT,
        FOREIGN KEY (sessao_id) REFERENCES sessoes(id),
        reservado boolean

);