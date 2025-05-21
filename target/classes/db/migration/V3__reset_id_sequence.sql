-- Limpar a tabela primeiro
TRUNCATE TABLE artigos;

-- Recriar a tabela do zero
DROP TABLE IF EXISTS artigos;

-- Criar nova tabela usando serial para IDs auto-incrementados
CREATE TABLE artigos (
  id SERIAL PRIMARY KEY,
  titulo VARCHAR(255) NOT NULL,
  conteudo TEXT NOT NULL,
  autor VARCHAR(255) NOT NULL,
  data_publicacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  categoria VARCHAR(50) NOT NULL
);