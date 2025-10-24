CREATE DATABASE IF NOT EXISTS biblioteca CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE biblioteca;

DROP TABLE IF EXISTS libros;
DROP TABLE IF EXISTS autores; 
CREATE TABLE autores (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(200) NOT NULL,
  fechaNac DATE NOT NULL,
  nacionalidad VARCHAR(50)  NOT NULL
);
CREATE TABLE libros (
  id INT AUTO_INCREMENT PRIMARY KEY,
  titulo VARCHAR(200) NOT NULL,
  isbn VARCHAR(20) NOT NULL,
  precio INT UNSIGNED NOT NULL,
  autor_id INT NOT NULL,
  CONSTRAINT fk_libros_autor
    FOREIGN KEY (autor_id)
    REFERENCES autores(id)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
);


INSERT INTO autores (id, nombre, fechaNac, nacionalidad) VALUES (22,'Gabriel García Márquez', '1927-03-06', 'Colombiana');
INSERT INTO libros (titulo, isbn, precio, autor_id) VALUES ('Cien años de soledad', '9780307474728', 18, 22);

select * from autores;
select * from libros;
SHOW CREATE TABLE libros;


