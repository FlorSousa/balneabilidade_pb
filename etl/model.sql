CREATE TABLE praia (
    id SERIAL PRIMARY KEY,
    nome_praia VARCHAR(255),
    cidade VARCHAR(255)
);

CREATE TABLE medicao (
    id SERIAL PRIMARY KEY,
    data_medicao TIMESTAMP,
    praia_id INTEGER NOT NULL,
    qualidade BOOLEAN,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT fk_medicao_praia
        FOREIGN KEY (praia_id)
        REFERENCES praia(id)
        ON DELETE CASCADE
);
