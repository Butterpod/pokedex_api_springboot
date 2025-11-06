https://java-jedi.medium.com/the-evolution-of-primary-keys-in-postgresql-from-serial-to-identity-and-beyond-f62662bc2595

TRUNCATE TABLE pokemon RESTART IDENTITY;

INSERT INTO pokemon (name, type1, type2, color, hp, attack, defense, speed) VALUES
('Bulbizarre','Plante','Poison','Vert',45,49,49,45),
('Herbizarre','Plante','Poison','Vert',60,62,63,60),
('Florizarre','Plante','Poison','Vert',80,82,83,80),
('Salamèche','Feu',NULL,'Rouge',39,52,43,65),
('Reptincel','Feu',NULL,'Rouge',58,64,58,80),
('Dracaufeu','Feu','Vol','Orange',78,84,78,100),
('Carapuce','Eau',NULL,'Bleu',44,48,65,43),
('Carabaffe','Eau',NULL,'Bleu',59,63,80,58),
('Tortank','Eau',NULL,'Bleu',79,83,100,78),
('Chenipan','Insecte',NULL,'Vert',45,30,35,45);



table : 


DROP TABLE IF EXISTS public.pokemon;


CREATE TABLE IF NOT EXISTS public.pokemon
(
id      BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
name    VARCHAR(255) NOT NULL,
type1   VARCHAR(255) NOT NULL,
type2   VARCHAR(255),
color   VARCHAR(255) NOT NULL,
hp      INTEGER CONSTRAINT pokemon_hp_check CHECK (hp >= 0),
attack  INTEGER CONSTRAINT pokemon_attack_check CHECK (attack >= 0),
defense INTEGER CONSTRAINT pokemon_defense_check CHECK (defense >= 0),
speed   INTEGER CONSTRAINT pokemon_speed_check CHECK (speed >= 0),
CONSTRAINT pokemon_name_key UNIQUE (name)
)
TABLESPACE pg_default;


ALTER TABLE IF EXISTS public.pokemon
OWNER TO postgres;
