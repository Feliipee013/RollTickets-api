CREATE TABLE ingressos_2d (
    id BIGINT PRIMARY KEY,
    FOREIGN KEY (id) REFERENCES ingressos(id)
);