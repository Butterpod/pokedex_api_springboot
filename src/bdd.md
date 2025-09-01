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