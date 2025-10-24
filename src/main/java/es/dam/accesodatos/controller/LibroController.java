package es.dam.accesodatos.controller;

import es.dam.accesodatos.model.exceptions.ReglaNegocioException;
import es.dam.accesodatos.service.LibroService;
import es.dam.accesodatos.view.ConsolaView;

/**
 * El Controlador: coordina Vista ↔ Servicio. No muestra ni accede a BD directamente!!!! Por favor, tened esto muy en cuenta!!!.
 Revisad el fichero excel de los apuntes en Aules si tenéis dudas*/
public class LibroController {

    private final ConsolaView view;
    private final LibroService service;

    public LibroController(ConsolaView view, LibroService service) {
        this.view = view;
        this.service = service;
    }

    public void run() {
        int op;
        do {
            op = view.menu();
            try {
                switch (op) {
                    case 1 -> crear();
                    case 2 -> listar();
                    case 3 -> actualizar();
                    case 4 -> borrar();
                    case 5 -> verPorId();
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

    private void crear() {
        String titulo = view.pedir("Título");
        String autor  = view.pedir("Autor");
        String isbn   = view.pedir("ISBN");
        int precio    = view.pedirEntero("Precio (entero positivo)");

        Integer id = service.crearLibro(titulo, autor, isbn, precio);
        view.info(" Libro creado con id " + id);
    }

    private void listar() {
        view.mostrarLista(service.listar());
    }

    private void actualizar() {
        int id = view.pedirEntero("ID del libro a actualizar");
        String titulo = view.pedir("Nuevo título");
        String autor  = view.pedir("Nuevo autor");
        String isbn   = view.pedir("Nuevo ISBN");
        int precio    = view.pedirEntero("Nuevo precio (entero positivo)");

        boolean ok = service.actualizar(id, titulo, autor, isbn, precio);
        view.info(ok ? "Libro actualizado correctamente" : "No se encontró el libro con ese ID");
    }

    private void borrar() {
        int id = view.pedirEntero("ID del libro a borrar");
        boolean ok = service.borrar(id);
        view.info(ok ? "Libro borrado correctamente" : "No se encontró el libro con ese ID");
    }

    private void verPorId() {
        int id = view.pedirEntero("ID del libro a consultar");
        service.obtenerPorId(id)
                .ifPresentOrElse(
                        libro -> view.info("📘 " + libro),
                        () -> view.info("Atención! No se encontró ningún libro con ese ID")
                );
    }
}