package es.dam.accesodatos.service;

import es.dam.accesodatos.dao.LibroDaoJdbc;
import es.dam.accesodatos.model.Libro;
import es.dam.accesodatos.model.exceptions.ReglaNegocioException;

import java.util.List;
import java.util.Optional;

public class LibroService {
    private final LibroDaoJdbc dao = new LibroDaoJdbc();

    /** Crear un libro nuevo (con validaciones) */
    public Integer crearLibro(String titulo,  String isbn, int precio, int autorId) {
        validarCamposComunes(titulo, isbn, precio);
        return dao.create(new Libro(titulo, isbn, precio, autorId));
    }

    /** Listar todos los libros */
    public List<Libro> listar() {
        return dao.findAll();
    }

    /** Buscar un libro por ID */
    public Optional<Libro> obtenerPorId(Integer id) {
        return dao.findById(id);
    }

    /** Actualizar un libro existente */
    public boolean actualizar(Integer id, String titulo, String isbn, int precio, int autorId) {
        if (id == null)
            throw new ReglaNegocioException("Id requerido");
        validarCamposComunes(titulo, isbn, precio);
        return dao.update(new Libro(id, titulo, isbn, precio, autorId));
    }

    /** Borrar un libro */
    public boolean borrar(Integer id) {
        if (id == null)
            throw new ReglaNegocioException("Id requerido");
        return dao.delete(id);
    }

    /** Validaciones más generales*/
    private void validarCamposComunes(String titulo, String isbn, int precio) {
        if (titulo == null || titulo.isBlank())
            throw new ReglaNegocioException("El título es obligatorio");
        if (isbn == null || isbn.isBlank())
            throw new ReglaNegocioException("El ISBN es obligatorio");
        if (precio <= 0)
            throw new ReglaNegocioException("El precio debe ser un entero positivo");
    }
}