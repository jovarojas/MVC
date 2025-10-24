package es.dam.accesodatos.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
/*Aquí tenéis que poner los parámetros de vuestra conexión.
Quedamos que nadie pondría contraseña al instalar Wamps, por tanto el pwd es también vació aqui
 */
    private static final String URL  = "jdbc:mysql://localhost:3306/biblioteca?serverTimezone=UTC";
    private static final String USER = "jovaR";
    private static final String PASS = "123456";

    public static Connection get() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}