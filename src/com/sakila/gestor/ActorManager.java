package com.sakila.gestor;

import com.sakila.data.DataContext;
import com.sakila.interfaces.iDatapost;
import com.sakila.modelo.Actor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class ActorManager extends DataContext<Actor> implements iDatapost<Actor> {

    public ActorManager(Connection conn) {
        super(conn);
    }

    @Override
    protected void crear(Actor actor) {
        String sql = "INSERT INTO actor (first_name, last_name) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, actor.getFirstName());
            stmt.setString(2, actor.getLastName());
            stmt.executeUpdate();
            System.out.println("✅ Actor insertado correctamente.");
        } catch (SQLException e) {
            System.out.println("❌ Error al insertar actor: " + e.getMessage());
        }
    }

    @Override
    protected Actor leer(int id) {
        String sql = "SELECT * FROM actor WHERE actor_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Actor(
                    rs.getInt("actor_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name")
                );
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al obtener actor: " + e.getMessage());
        }
        return null;
    }

    @Override
    protected List<Actor> leerTodo() {
        List<Actor> lista = new ArrayList<>();
        String sql = "SELECT * FROM actor";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Actor actor = new Actor(
                    rs.getInt("actor_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name")
                );
                lista.add(actor);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al listar actores: " + e.getMessage());
        }
        return lista;
    }

    @Override
    protected void actualizar(Actor actor) {
        String sql = "UPDATE actor SET first_name = ?, last_name = ? WHERE actor_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, actor.getFirstName());
            stmt.setString(2, actor.getLastName());
            stmt.setInt(3, actor.getId());
            stmt.executeUpdate();
            System.out.println("✅ Actor actualizado correctamente.");
        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar actor: " + e.getMessage());
        }
    }

    @Override
    protected void eliminar(int id) {
        String sql = "DELETE FROM actor WHERE actor_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("🗑️ Actor eliminado correctamente.");
        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar actor: " + e.getMessage());
        }
    }
}
