package es.dam.accesodatos.view;

import es.dam.accesodatos.model.Libro;


import java.util.List;
import java.util.Scanner;


public class ConsolaView {
    private final Scanner sc = new Scanner(System.in);

    /**
     * Muestra el menú principal y devuelve la opción seleccionada por el usuario.
     */
    public int menu() {
        System.out.println("\n=== GESTIÓN DE LIBROS ===");
        System.out.println("1. Crear nuevo libro");
        System.out.println("2. Listar todos los libros");
        System.out.println("3. Actualizar un libro");
        System.out.println("4. Borrar un libro");
        System.out.println("5. Ver libro por ID");
        System.out.println("0. Salir");
        System.out.print("Elige una opción: ");
        return leerEntero();
    }

    /**
     * Pide un texto al usuario (por ejemplo, título o autor).
     */
    public String pedir(String etiqueta) {
        System.out.print(etiqueta + ": ");
        return sc.nextLine().trim();
    }

    /**
     * Pide un número entero al usuario, VALIDANDO que lo introduzca correctamente.
     */
    public int pedirEntero(String etiqueta) {
        System.out.print(etiqueta + ": ");
        return leerEntero();
    }

    /**
     * Saca un listado de todos los libros.
     */
    public void mostrarLista(List<Libro> libros) {
        System.out.println("\n=== LISTADO DE LIBROS ===");
        if (libros.isEmpty()) {
            System.out.println("(sin registros)");
        } else {
            libros.forEach(System.out::println);
        }
    }

    /**
     * Este metodo lo hacemos para sacar por pantalla los mensajes de información
     */
    public void info(String msg) {
        System.out.println(msg);
    }

    /**
     * Este otro metodo es para mostrar los mensajes de errorr.
     */
    public void error(String msg) {
        System.err.println("ERROR: " + msg);
    }

    /**
     * Lee un número entero desde teclado y repite mientras no sea válido.
     */
    private int leerEntero() {
        while (true) {
            String s = sc.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.print("Introduce un número entero válido: ");
            }
        }
    }
}