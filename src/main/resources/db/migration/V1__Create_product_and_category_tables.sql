-- V1__Create_product_and_category_tables.sql

-- Tabela de produtos com ID auto-gerado
CREATE TABLE products (
    id BIGSERIAL NOT NULL,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

-- Tabela de categorias com chave primária composta (id e code)
-- E uma referência para o produto (relação um-para-muitos)
CREATE TABLE categories (
    id VARCHAR(36) NOT NULL,
    code VARCHAR(50) NOT NULL,
    product_id BIGINT NOT NULL,
    summary numeric(15,4) not null,
    PRIMARY KEY (id, code),
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);

-- Índices para melhorar a performance de consultas
CREATE INDEX idx_products_name ON products(name);
CREATE INDEX idx_categories_code ON categories(code);
CREATE INDEX idx_categories_product_id ON categories(product_id);