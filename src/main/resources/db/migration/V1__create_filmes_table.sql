CREATE TABLE filmes(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    sinopse TEXT,
    duracao INT NOT NULL,
    classificacao VARCHAR(10),
    genero VARCHAR(50),
    image_url VARCHAR(255),
    formato ENUM('2D', '3D') NOT NULL
);