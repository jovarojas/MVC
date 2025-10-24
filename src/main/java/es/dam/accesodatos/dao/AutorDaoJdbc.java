package es.dam.accesodatos.dao;

import es.dam.accesodatos.model.Autor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class AutorDaoJdbc implements Dao<Autor, Integer>{
    @Override
    public Integer create(Autor a) {
        if (a == null) throw new IllegalArgumentException("Autor no puede ser null");
        final String sql = "INSERT INTO autores (nombre, fechaNac, nacionalidad) VALUES (?,?,?)";
        try (Connection con = Conexion.get();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, a.getNombre());
            ps.setDate( 2, new java.sql.Date(a.getFechaNacimiento().getTime()));
            ps.setString(3, a.getNacionalidad());


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
                    "Error creando autor | SQLState=%s | ErrorCode=%d | Message=%s",
                    e.getSQLState(), e.getErrorCode(), e.getMessage()
            );
            throw new RuntimeException(msg, e);
        }
    }

    @Override
    public Optional<Autor> findById(Integer id) {
        String sql = "SELECT id, nombre, fechaNac, nacionalidad FROM autores WHERE id = ?";
        try (Connection con = Conexion.get();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(map(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error buscando autor por id", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Autor> findAll() {
        List<Autor> res = new ArrayList<>();
        String sql = "SELECT id, nombre, fechaNac, nacionalidad FROM autores ORDER BY id";
        try (Connection con = Conexion.get();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) res.add(map(rs));
        } catch (SQLException e) {
            throw new RuntimeException("Error listando autores", e);
        }
        return res;
    }

    @Override
    public boolean update(Autor a) {
        String sql = "UPDATE autores SET nombre=?, fechaNac=?, nacionalidad=? WHERE id=?";
        try (Connection con = Conexion.get();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, a.getNombre());
            ps.setDate(2, new java.sql.Date(a.getFechaNacimiento().getTime()));
            ps.setString(3, a.getNacionalidad());
            ps.setInt(4, a.getId());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando autor", e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM autores WHERE id=?";
        try (Connection con = Conexion.get();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Error borrando autor", e);
        }
    }
//--------------------------AUXILIARES--------------------------
    private Autor map(ResultSet rs) throws SQLException {
        return new Autor(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getDate("fechaNac"),
                rs.getString("nacionalidad")
        );
    }
}
