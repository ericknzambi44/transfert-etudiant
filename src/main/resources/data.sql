-- src/main/resources/data.sql
-- Insérer un établissement source et cible pour les tests
INSERT INTO etablissement (id, nom, adresse, email_contact, password, role, actif) VALUES
('11111111-1111-1111-1111-111111111111', 'Université Paris-Source', '1 rue de Paris', 'source@univ-paris.fr', '$2a$10$dummyhash', 'SOURCE', true),
('22222222-2222-2222-2222-222222222222', 'Université Lyon-Cible', '2 rue de Lyon', 'cible@univ-lyon.fr', '$2a$10$dummyhash', 'CIBLE', true);
-- Note: les mots de passe en clair seraient "password" mais il faut encoder réellement. En dev, on peut utiliser un encoder.