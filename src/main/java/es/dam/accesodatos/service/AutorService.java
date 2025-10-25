package es.dam.accesodatos.service;

import es.dam.accesodatos.dao.AutorDaoJdbc;
import es.dam.accesodatos.model.Autor;
import es.dam.accesodatos.model.exceptions.ReglaNegocioException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class AutorService {

    private final AutorDaoJdbc dao = new AutorDaoJdbc();
    /** Crear un autor nuevo (con validaciones) */
    public <Date> Integer crear(String nombre, Date fechaNac, String nacionalidad) {
        //validarCamposComunes(titulo, isbn, precio);
        return dao.create(new Autor(nombre, (java.util.Date) fechaNac, nacionalidad));
    }

    /** Listar todos los autores */
    public List<Autor> listar() {
        return dao.findAll();
    }

    /** Buscar un autor por ID */
    public Optional<Autor> obtenerPorId(Integer id) {
        return dao.findById(id);
    }

    /** Actualizar un autor existente */
    public boolean actualizar(Integer id, String nombre, Date fechaN, String nacionalidad) {
        if (id == null)
            throw new ReglaNegocioException("Id requerido");
        validarCamposComunes(nombre, fechaN, nacionalidad);
        return dao.update(new Autor(id, nombre, fechaN, nacionalidad));
    }

    /** Borrar un autor */
    public boolean borrar(Integer id) {
        if (id == null)
            throw new ReglaNegocioException("Id requerido");
        return dao.delete(id);
    }

    /** Validaciones más generales */
    private void validarCamposComunes(String nombre, Date fecha, String nacionalidad) {
        if (nombre == null || nombre.isBlank())
            throw new ReglaNegocioException("El nombre es obligatorio");
        if (fecha == null)
            throw new ReglaNegocioException("La fecha de nacimiento es obligatoria");
        if (fecha.after(new Date()))
            throw new ReglaNegocioException("La fecha de nacimiento es posterior a hoy");
        if (nacionalidad == null || nacionalidad.isBlank())
            throw new ReglaNegocioException("La nacionalidad es obligatorio");
    }
}
