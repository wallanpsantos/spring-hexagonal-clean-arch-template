-- Flyway Migration V1: criação da tabela de produtos
CREATE TABLE IF NOT EXISTS products (
    id          UUID         NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    sku         VARCHAR(20)  NOT NULL UNIQUE,
    price       NUMERIC(15, 2) NOT NULL,
    currency    CHAR(3)      NOT NULL,
    discontinued BOOLEAN     NOT NULL DEFAULT FALSE,
    created_at  TIMESTAMP    NOT NULL DEFAULT now(),
    updated_at  TIMESTAMP    NOT NULL DEFAULT now()
);

CREATE INDEX idx_products_sku ON products(sku);
