CREATE TABLE transaction (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    operation_number VARCHAR(255) NOT NULL,
    source_channel VARCHAR(255) NOT NULL,
    amount DECIMAL(19, 4) NOT NULL
);