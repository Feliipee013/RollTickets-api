CREATE TABLE ingressos2d (
    id BIGINT PRIMARY KEY,
    FOREIGN KEY (id) REFERENCES ingressos(id)
);