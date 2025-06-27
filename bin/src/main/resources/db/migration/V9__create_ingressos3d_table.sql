CREATE TABLE ingressos_3d (
    id BIGINT PRIMARY KEY,
    inclui_oculos BOOLEAN NOT NULL DEFAULT TRUE,
    taxa_extra_3d DOUBLE NOT NULL DEFAULT 5.0,
    FOREIGN KEY (id) REFERENCES ingressos(id)
);