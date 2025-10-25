package es.dam.accesodatos.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL  = "jdbc:mysql://localhost:3306/biblioteca?serverTimezone=UTC";
    private static final String USER = "jovaR";
    private static final String PASS = "123456";

    public static Connection get() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}