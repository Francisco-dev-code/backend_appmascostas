-- Datos iniciales para testing
-- Usuario: Francisco Gonzalez
INSERT INTO users (id, name, email, password, phone) VALUES (1, 'Francisco Gonzalez', 'fa.gonzalez@duoc.cl', 'Fran%123', '9 3344 5566');

-- Mascotas de Francisco
INSERT INTO pets (id, name, type, user_id) VALUES (1, 'Bob', 'Perro', 1);
INSERT INTO pets (id, name, type, user_id) VALUES (2, 'Pepe', 'Ave', 1);
