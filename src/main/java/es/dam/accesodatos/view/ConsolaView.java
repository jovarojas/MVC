package es.dam.accesodatos.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        System.out.println("6. Crear nuevo autor");
        System.out.println("7. Listar todos los autores");
        System.out.println("8. Actualizar un autor");
        System.out.println("9. Borrar un autor");
        System.out.println("10. Ver autor por ID");
        System.out.println("11. Obtener información completa de un libro");
        System.out.println("0. Salir");
        System.out.print("Elige una opción: ");
        return leerEntero();
    }

    /**
     * Pide un texto al usuario.
     */
    public String pedir(String etiqueta) {
        System.out.print(etiqueta + ": ");
        return sc.nextLine().trim();
    }

    /**
     * Pide una fecha al usuario en formato dd/mm/yyyy.
     */
    public Date pedirFecha(String etiqueta) {
        System.out.print(etiqueta + ": ");
        String fecha = sc.nextLine().trim();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date fechaNacimiento = formato.parse(fecha);
            return fechaNacimiento;
        } catch (ParseException e) {
            throw new RuntimeException("Formato incorrecto, usa: dd/MM/yyyy");
        }
    }

    /**
     * Pide un número entero al usuario, VALIDANDO que lo introduzca correctamente.
     */
    public int pedirEntero(String etiqueta) {
        System.out.print(etiqueta + ": ");
        return leerEntero();
    }

    public static <T> void mostrarLista(List<T> elementos, String titulo) {
        System.out.println("\n=== " + titulo.toUpperCase() + " ===");
        if (elementos.isEmpty()) {
            System.out.println("(sin registros)");
        } else {
            elementos.forEach(System.out::println);
        }
    }

    public void info(String msg) {
        System.out.println(msg);
    }


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