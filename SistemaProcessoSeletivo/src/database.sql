CREATE DATABASE SistemaProcessoSeletivo;
CREATE TABLE Candidatos (
    idCandidato SERIAL PRIMARY KEY,
    nomeCandidato VARCHAR(100) NOT NULL,
    emailCandidato VARCHAR(100) NOT NULL UNIQUE,
    salarioCandidato DECIMAL(10, 2) NOT NULL
);