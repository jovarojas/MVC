package es.dam.accesodatos.model.exceptions;

public class ReglaNegocioException extends RuntimeException {

    /**
     * Crea una nueva excepción con el mensaje indicado.
     *
     * @param message descripción de la regla incumplida
     */
    public ReglaNegocioException(String message) {
        super(message);
    }
}