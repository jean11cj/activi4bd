package com.sistemavuelo.infrastructure.output.postgres;

import com.sistemavuelo.application.PasajeroRepository;
import com.sistemavuelo.domain.Pasajero;

import java.sql.*;
import java.util.*;

public class JdbcPasajeroRepository implements PasajeroRepository {
    private Connection conn;

    public JdbcPasajeroRepository(Connection conn) {
        this.conn = conn;
    }

    public void guardar(Pasajero pasajero) {
        try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO pasajero (nombre) VALUES (?)")) {
            stmt.setString(1, pasajero.getNombre());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Pasajero buscarPorId(int id) {
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM pasajero WHERE id_pasajero = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Pasajero(rs.getInt("id_pasajero"), rs.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Pasajero> listarTodos() {
        List<Pasajero> lista = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM pasajero");
            while (rs.next()) {
                lista.add(new Pasajero(rs.getInt("id_pasajero"), rs.getString("nombre")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void actualizar(Pasajero pasajero) {
        try (PreparedStatement stmt = conn.prepareStatement("UPDATE pasajero SET nombre = ? WHERE id_pasajero = ?")) {
            stmt.setString(1, pasajero.getNombre());
            stmt.setInt(2, pasajero.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(int id) {
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM pasajero WHERE id_pasajero = ?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}