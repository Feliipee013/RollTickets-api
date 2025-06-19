CREATE TABLE pagamentos(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    compra_id BIGINT,
    metodo_pagamento ENUM('CREDITO', 'DEBITO', 'PIX') NOT NULL,
    status ENUM('PAGO', 'PENDENTE', 'CANCELADO') NOT NULL,
    data_hora_pagamento DATETIME,
    FOREIGN KEY (compra_id) REFERENCES compras(id)
);