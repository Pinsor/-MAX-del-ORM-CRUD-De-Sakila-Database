package com.sakila.controladores;

import com.sakila.modelo.Film;
import com.sakila.gestor.FilmManager;
import com.sakila.util.Exporter;

import java.util.List;
import java.util.Scanner;

public class FilmController {

    private final FilmManager manager;
    private final Scanner scanner = new Scanner(System.in);

    public FilmController(FilmManager manager) {
        this.manager = manager;
    }

    public void mostrarMenu() {
        int opcion = 0;
        do {
            System.out.println("\n-- GESTIÓN DE PELÍCULAS --");
            System.out.println("1. Insertar película");
            System.out.println("2. Buscar por ID");
            System.out.println("3. Listar todas");
            System.out.println("4. Actualizar");
            System.out.println("5. Eliminar");
            System.out.println("6. Exportar a CSV");
            System.out.println("7. Exportar a JSON");
            System.out.println("8. Salir");
            System.out.print("Opción: ");
            if (!scanner.hasNextInt()) {
                System.out.println("❌ Entrada inválida.");
                scanner.nextLine();
                continue;
            }
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> insertar();
                case 2 -> buscar();
                case 3 -> listar();
                case 4 -> actualizar();
                case 5 -> eliminar();
                case 6 -> Exporter.exportFilmsToCSV(manager.getAll(), "peliculas.csv");
                case 7 -> Exporter.exportFilmsToJSON(manager.getAll(), "peliculas.json");
                case 8 -> System.out.println("👋 Saliendo del módulo de películas...");
                default -> System.out.println("❌ Opción no válida.");
            }
        } while (opcion != 8);
    }

    private void insertar() {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();
        manager.post(new Film(0, titulo, descripcion));
    }

    private void buscar() {
        System.out.print("ID: ");
        if (!scanner.hasNextInt()) {
            System.out.println("❌ Entrada inválida.");
            scanner.nextLine();
            return;
        }
        int id = scanner.nextInt();
        scanner.nextLine();
        Film f = manager.get(id);
        if (f != null) {
            System.out.println(f.getId() + ": " + f.getTitle() + " - " + f.getDescription());
        } else {
            System.out.println("❌ Película no encontrada.");
        }
    }

    private void listar() {
        List<Film> lista = manager.getAll();
        for (Film f : lista) {
            System.out.println(f.getId() + ": " + f.getTitle() + " - " + f.getDescription());
        }
    }

    private void actualizar() {
        System.out.print("ID a actualizar: ");
        if (!scanner.hasNextInt()) {
            System.out.println("❌ Entrada inválida.");
            scanner.nextLine();
            return;
        }
        int id = scanner.nextInt();
        scanner.nextLine();
        Film f = manager.get(id);
        if (f != null) {
            System.out.print("Nuevo título: ");
            f.setTitle(scanner.nextLine());
            System.out.print("Nueva descripción: ");
            f.setDescription(scanner.nextLine());
            manager.put(f);
        } else {
            System.out.println("❌ Película no encontrada.");
        }
    }

    private void eliminar() {
        System.out.print("ID a eliminar: ");
        if (!scanner.hasNextInt()) {
            System.out.println("❌ Entrada inválida.");
            scanner.nextLine();
            return;
        }
        int id = scanner.nextInt();
        scanner.nextLine();
        manager.delete(id);
    }
}
