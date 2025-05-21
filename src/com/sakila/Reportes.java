package com.sakila;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Reportes {

    private final Connection conn;

    public Reportes(Connection conn) {
        this.conn = conn;
    }

    public void totalPeliculas() {
        String sql = "SELECT COUNT(*) AS total FROM film";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                System.out.println("🎞️ Total de películas: " + rs.getInt("total"));
            }
        } catch (Exception e) {
            System.out.println("❌ Error al obtener total de películas: " + e.getMessage());
        }
    }

    public void actoresPorPeliculas() {
        String sql = """
            SELECT f.title, COUNT(*) AS total_actores
            FROM film_actor fa
            JOIN film f ON f.film_id = fa.film_id
            GROUP BY f.title
            ORDER BY total_actores DESC
            LIMIT 10
            """;
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n🎬 Películas con más actores:");
            while (rs.next()) {
                System.out.printf("- %-40s %d actores%n", rs.getString("title"), rs.getInt("total_actores"));
            }
        } catch (Exception e) {
            System.out.println("❌ Error en reporte: " + e.getMessage());
        }
    }

    public void rentasPorCiudad() {
        String sql = """
            SELECT city.city, COUNT(*) AS total_rentas
            FROM rental
            JOIN inventory ON rental.inventory_id = inventory.inventory_id
            JOIN store ON inventory.store_id = store.store_id
            JOIN address ON store.address_id = address.address_id
            JOIN city ON address.city_id = city.city_id
            GROUP BY city.city
            ORDER BY total_rentas DESC
            LIMIT 10
            """;
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n🏙️ Rentas por ciudad:");
            while (rs.next()) {
                System.out.printf("- %-20s %d rentas%n", rs.getString("city"), rs.getInt("total_rentas"));
            }
        } catch (Exception e) {
            System.out.println("❌ Error en reporte: " + e.getMessage());
        }
    }
}
