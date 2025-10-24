package es.dam.accesodatos.model.exceptions;

/**
 * Excepción personalizada para reglas de negocio.
 *
 * Se lanza desde la capa de servicio cuando algún dato
 * no cumple las condiciones establecidas (por ejemplo,
 * precio negativo, título vacío, ISBN nulo, etc.).
 *
 * Al usar una excepción específica, el controlador puede
 * capturarla y mostrar un mensaje amigable al usuario,
 * sin mezclarla con errores técnicos de SQL o del sistema.
 * NO ES OBLIGADO HACERLO ASÍ PERO ES DE BUENAS PRÁCTICAS. Intentadlo hacer!!
 */
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