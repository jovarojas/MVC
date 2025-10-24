package es.dam.accesodatos.controller;

import es.dam.accesodatos.model.exceptions.ReglaNegocioException;
import es.dam.accesodatos.service.AutorService;
import es.dam.accesodatos.service.LibroService;
import es.dam.accesodatos.view.ConsolaView;

import java.util.Date;

/**
 * El Controlador: coordina Vista ↔ Servicio. No muestra ni accede a BD directamente!!!! Por favor, tened esto muy en cuenta!!!.
 * Revisad el fichero excel de los apuntes en Aules si tenéis dudas
 */
public class Controller {

    private final ConsolaView view;
    private final LibroService libroService;
    private final AutorService autorService;

    public Controller(ConsolaView view, LibroService libroService, AutorService autorService) {
        this.view = view;
        this.libroService = libroService;
        this.autorService = autorService;
    }

    public void run() {
        int op;
        do {
            op = view.menu();
            try {
                switch (op) {
                    case 1 -> crearLibro();
                    case 2 -> listarLibros();
                    case 3 -> actualizarLibro();
                    case 4 -> borrarLibro();
                    case 5 -> verLibroPorId();
                    case 6 -> crearAutor();
                    case 7 -> listarAutores();
                    case 8 -> actualizarAutor();
                    case 9 -> borrarAutor();
                    case 10 -> verAutorPorId();
                    case 11 -> informacionCompleta();
                    case 0 -> view.info("¡Hasta luego!");
                    default -> view.error("Opción inválida, inténtalo de nuevo.");
                }
            } catch (ReglaNegocioException e) {
                view.error("Atención!" + e.getMessage());
            } catch (RuntimeException e) {
                view.error("Error inesperado: " + e.getMessage());
            }
        } while (op != 0);
    }

    private void crearLibro() {
        String titulo = view.pedir("Título");
        String isbn = view.pedir("ISBN");
        int precio = view.pedirEntero("Precio (entero positivo)");
        int autorId = view.pedirEntero("ID del autor");

        Integer id = libroService.crearLibro(titulo, isbn, precio, autorId);
        view.info(" Libro creado con id " + id);
    }


    private void listarLibros() {
        view.mostrarLista(libroService.listar(), "LIBROS");
    }

    private void actualizarLibro() {
        int id = view.pedirEntero("ID del libro a actualizar");
        String titulo = view.pedir("Nuevo título");
        String isbn = view.pedir("Nuevo ISBN");
        int precio = view.pedirEntero("Nuevo precio (entero positivo)");
        int autorId = view.pedirEntero("Nuevo ID del autor");

        boolean ok = libroService.actualizar(id, titulo, isbn, precio, autorId);
        view.info(ok ? "Libro actualizado correctamente" : "No se encontró el libro con ese ID");
    }

    private void borrarLibro() {
        int id = view.pedirEntero("ID del libro a borrar");
        boolean ok = libroService.borrar(id);
        view.info(ok ? "Libro borrado correctamente" : "No se encontró el libro con ese ID");
    }

    private void verLibroPorId() {
        int id = view.pedirEntero("ID del libro a consultar");
        libroService.obtenerPorId(id).ifPresentOrElse(libro -> view.info("📘 " + libro), () -> view.info("Atención! No se encontró ningún libro con ese ID"));
    }

    //---------------------------------AUTOR---------------------------------------------
    private void crearAutor() {
        String nombre = view.pedir("Nombre");
        Date fechaNac = view.pedirFecha("Fecha de nacimiento");
        String nacionalidad = view.pedir("Nacionalidad");

        Integer id = autorService.crear(nombre, fechaNac, nacionalidad);
        view.info(" Autor creado con id " + id);
    }


    private void listarAutores() {
        view.mostrarLista(autorService.listar(), "AUTORES");
    }

    private void actualizarAutor() {
        int id = view.pedirEntero("ID del autor a actualizar");
        String nombre = view.pedir("Nombre");
        Date fechaNac = view.pedirFecha("Fecha de nacimiento");
        String nacionalidad = view.pedir("Nacionalidad");

        boolean ok = autorService.actualizar(id, nombre, fechaNac, nacionalidad);
        view.info(ok ? "Autor actualizado correctamente" : "No se encontró el autor con ese ID");
    }

    private void borrarAutor() {
        int id = view.pedirEntero("ID del autor a borrar");
        boolean ok = autorService.borrar(id);
        view.info(ok ? "Autor borrado correctamente" : "No se encontró el autor con ese ID");
    }

    private void verAutorPorId() {
        int id = view.pedirEntero("ID del autor a consultar");
        autorService.obtenerPorId(id).ifPresentOrElse(autor -> view.info("📘 " + autor), () -> view.info("Atención! No se encontró ningún autor con ese ID"));
    }

    //---------------------------------GENERAL---------------------------------------------
    private void informacionCompleta() {
        int id = view.pedirEntero("ID del libro a consultar");
        //titulo del libro, nombre del autor, fecha nac y nacionalidad
        libroService.obtenerPorId(id).ifPresentOrElse(libro -> {
                    String titulo = libro.getTitulo();

                    autorService.obtenerPorId(libro.getAutorId()).ifPresentOrElse(
                            autor -> view.info(
                                    "Título: " + titulo +
                                            "\nAutor: " + autor.getNombre() +
                                            "\nFecha de nacimiento: " + autor.getFechaNacimiento() +
                                            "\nNacionalidad: " + autor.getNacionalidad()
                            ),
                            () -> view.info("Título: " + titulo + "\nAutor: No disponible")
                    );
                },
                () -> view.info("Atención! No se encontró ningún libro con ese ID"));
    }
}