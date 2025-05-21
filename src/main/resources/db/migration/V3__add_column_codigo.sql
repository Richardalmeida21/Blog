ALTER TABLE artigos ADD COLUMN codigo VARCHAR(10);
ALTER TABLE artigos ADD CONSTRAINT artigos_codigo_uk UNIQUE (codigo);
UPDATE artigos SET codigo = 'A' || floor(random() * 900000 + 100000)::text WHERE codigo IS NULL;