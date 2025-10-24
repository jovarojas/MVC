package es.dam.accesodatos.dao;
import es.dam.accesodatos.model.Libro;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class LibroDaoJdbc implements Dao<Libro, Integer> {

    @Override
    public Integer create(Libro l) {
        if (l == null) throw new IllegalArgumentException("Libro no puede ser null");
        final String sql = "INSERT INTO libros (titulo, autor, isbn, precio) VALUES (?,?,?,?)";
        try (Connection con = Conexion.get();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, l.getTitulo());
            ps.setString(2, l.getAutor());
            ps.setString(3, l.getIsbn());
            ps.setInt(4, l.getPrecio());

            int rows = ps.executeUpdate();
            if (rows != 1) {
                throw new RuntimeException("El INSERT no afectó exactamente a 1 fila (rows=" + rows + ")");
            }

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getInt(1);
                } else {
                    // Si no hay claves generadas, fuerza el mensaje para detectar el motivo
                    throw new RuntimeException("No se devolvió clave generada. ¿id es AUTO_INCREMENT?");
                }
            }

        } catch (SQLException e) {
            String msg = String.format(
                    "Error creando libro | SQLState=%s | ErrorCode=%d | Message=%s",
                    e.getSQLState(), e.getErrorCode(), e.getMessage()
            );
            throw new RuntimeException(msg, e);
        }
    }
    @Override
    public Optional<Libro> findById(Integer id) {
        String sql = "SELECT id, titulo, autor, isbn, precio FROM libros WHERE id = ?";
        try (Connection con = Conexion.get();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(map(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error buscando libro por id", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Libro> findAll() {
        List<Libro> res = new ArrayList<>();
        String sql = "SELECT id, titulo, autor, isbn, precio FROM libros ORDER BY id";
        try (Connection con = Conexion.get();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) res.add(map(rs));
        } catch (SQLException e) {
            throw new RuntimeException("Error listando libros", e);
        }
        return res;
    }

    @Override
    public boolean update(Libro l) {
        String sql = "UPDATE libros SET titulo=?, autor=?, isbn=?, precio=? WHERE id=?";
        try (Connection con = Conexion.get();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, l.getTitulo());
            ps.setString(2, l.getAutor());
            ps.setString(3, l.getIsbn());
            ps.setInt(4, l.getPrecio());
            ps.setInt(5, l.getId());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando libro", e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM libros WHERE id=?";
        try (Connection con = Conexion.get();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Error borrando libro", e);
        }
    }

    // ---------- auxiliares ---------- LAS PODEIS OBVIAR o INCLUIR DENTRO DE OTROS MÉTODOS MÁS GENERALES
    private Libro map(ResultSet rs) throws SQLException {
        return new Libro(
                rs.getInt("id"),
                rs.getString("titulo"),
                rs.getString("autor"),
                rs.getString("isbn"),
                rs.getInt("precio")
        );
    }

    //(Opcional) ejemplo de metodo específico:
    public Optional<Libro> findByIsbn(String isbn) {
        String sql = "SELECT id, titulo, autor, isbn, precio FROM libros WHERE isbn = ?";
        try (Connection con = Conexion.get();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, isbn);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(map(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error buscando por ISBN", e);
        }
        return Optional.empty();
    }
}
