package com.sakila.util;

import com.google.gson.Gson;
import com.sakila.modelo.Actor;
import com.sakila.modelo.Film;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Exporter {

    public static void exportActorsToCSV(List<Actor> actores, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("ID,Nombre,Apellido\n");
            for (Actor a : actores) {
                writer.write(a.getId() + "," + a.getFirstName() + "," + a.getLastName() + "\n");
            }
            System.out.println("✅ Actores exportados a CSV: " + filePath);
        } catch (IOException e) {
            System.out.println("❌ Error al exportar actores CSV: " + e.getMessage());
        }
    }

    public static void exportActorsToJSON(List<Actor> actores, String filePath) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(actores, writer);
            System.out.println("✅ Actores exportados a JSON: " + filePath);
        } catch (IOException e) {
            System.out.println("❌ Error al exportar actores JSON: " + e.getMessage());
        }
    }

    public static void exportFilmsToCSV(List<Film> films, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("ID,Título,Descripción\n");
            for (Film f : films) {
                writer.write(f.getId() + "," + f.getTitle() + "," + f.getDescription() + "\n");
            }
            System.out.println("✅ Películas exportadas a CSV: " + filePath);
        } catch (IOException e) {
            System.out.println("❌ Error al exportar películas CSV: " + e.getMessage());
        }
    }

    public static void exportFilmsToJSON(List<Film> films, String filePath) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(films, writer);
            System.out.println("✅ Películas exportadas a JSON: " + filePath);
        } catch (IOException e) {
            System.out.println("❌ Error al exportar películas JSON: " + e.getMessage());
        }
    }
}
