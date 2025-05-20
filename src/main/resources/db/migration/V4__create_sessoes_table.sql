CREATE TABLE sessoes(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    filme_id BIGINT,
    sala_id BIGINT,
    horario DATETIME NOT NULL,
    preco DOUBLE NOT NULL,
    qtd_ingressos_disponiveis INT NOT NULL,
    FOREIGN KEY (filme_id) REFERENCES filmes(id),
    FOREIGN KEY (sala_id) REFERENCES salas(id)
);