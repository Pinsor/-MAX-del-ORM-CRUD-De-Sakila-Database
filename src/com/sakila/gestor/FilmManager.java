package com.sakila.gestor;

import com.sakila.data.DataContext;
import com.sakila.interfaces.iDatapost;
import com.sakila.modelo.Film;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class FilmManager extends DataContext<Film> implements iDatapost<Film> {

    public FilmManager(Connection conn) {
        super(conn);
    }

    @Override
    protected void crear(Film film) {
        String sql = "INSERT INTO film (title, description, language_id) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, film.getTitle());
            stmt.setString(2, film.getDescription());
            stmt.setInt(3, 1); // lenguaje predeterminado (inglés)
            stmt.executeUpdate();
            System.out.println("✅ Película insertada correctamente.");
        } catch (SQLException e) {
            System.out.println("❌ Error al insertar película: " + e.getMessage());
        }
    }

    @Override
    protected Film leer(int id) {
        String sql = "SELECT * FROM film WHERE film_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Film(
                    rs.getInt("film_id"),
                    rs.getString("title"),
                    rs.getString("description")
                );
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al obtener película: " + e.getMessage());
        }
        return null;
    }

    @Override
    protected List<Film> leerTodo() {
        List<Film> lista = new ArrayList<>();
        String sql = "SELECT * FROM film LIMIT 50"; // evitar cargar miles
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Film film = new Film(
                    rs.getInt("film_id"),
                    rs.getString("title"),
                    rs.getString("description")
                );
                lista.add(film);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al listar películas: " + e.getMessage());
        }
        return lista;
    }

    @Override
    protected void actualizar(Film film) {
        String sql = "UPDATE film SET title = ?, description = ? WHERE film_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, film.getTitle());
            stmt.setString(2, film.getDescription());
            stmt.setInt(3, film.getId());
            stmt.executeUpdate();
            System.out.println("✅ Película actualizada correctamente.");
        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar película: " + e.getMessage());
        }
    }

    @Override
    protected void eliminar(int id) {
        String sql = "DELETE FROM film WHERE film_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("🗑️ Película eliminada correctamente.");
        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar película: " + e.getMessage());
        }
    }
}
