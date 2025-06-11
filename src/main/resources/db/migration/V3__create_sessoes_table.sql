CREATE TABLE sessoes(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    filme_id BIGINT,
    horario DATETIME NOT NULL,
    quantidade_ingressos_disponiveis  INT NOT NULL,
    FOREIGN KEY (filme_id) REFERENCES filmes(id)
);