CREATE TABLE filmes(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    sinopse TEXT,
    duracao INT NOT NULL,
    classificacao VARCHAR(10),
    image_url VARCHAR(255),
    formato ENUM('DOIS_D', 'TRES_D') NOT NULL,
    avaliacao FLOAT 
);