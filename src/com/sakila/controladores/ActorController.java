package com.sakila.controladores;

import com.sakila.modelo.Actor;
import com.sakila.gestor.ActorManager;
import com.sakila.util.Exporter;

import java.util.List;
import java.util.Scanner;

public class ActorController {

    private final ActorManager manager;
    private final Scanner scanner = new Scanner(System.in);

    public ActorController(ActorManager manager) {
        this.manager = manager;
    }

    public void mostrarMenu() {
        int opcion = 0;
        do {
            System.out.println("\n-- GESTIÓN DE ACTORES --");
            System.out.println("1. Insertar actor");
            System.out.println("2. Buscar actor por ID");
            System.out.println("3. Listar todos los actores");
            System.out.println("4. Actualizar actor");
            System.out.println("5. Eliminar actor");
            System.out.println("6. Exportar actores a CSV");
            System.out.println("7. Exportar actores a JSON");
            System.out.println("8. Salir");
            System.out.print("Selecciona una opción: ");

            if (!scanner.hasNextInt()) {
                System.out.println("❌ Entrada inválida.");
                scanner.nextLine();
                continue;
            }

            opcion = scanner.nextInt();
            scanner.nextLine(); // Limper buffer

            switch (opcion) {
                case 1 -> insertar();
                case 2 -> buscar();
                case 3 -> listar();
                case 4 -> actualizar();
                case 5 -> eliminar();
                case 6 -> Exporter.exportActorsToCSV(manager.getAll(), "actores.csv");
                case 7 -> Exporter.exportActorsToJSON(manager.getAll(), "actores.json");
                case 8 -> System.out.println("👋 Saliendo del módulo de actores...");
                default -> System.out.println("❌ Opción no válida.");
            }
        } while (opcion != 8);
    }

    private void insertar() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        manager.post(new Actor(0, nombre, apellido));
    }

    private void buscar() {
        System.out.print("ID del actor: ");
        if (!scanner.hasNextInt()) {
            System.out.println("❌ Entrada inválida.");
            scanner.nextLine();
            return;
        }
        int id = scanner.nextInt();
        scanner.nextLine();
        Actor actor = manager.get(id);
        if (actor != null) {
            System.out.println(actor.getId() + ": " + actor.getFirstName() + " " + actor.getLastName());
        } else {
            System.out.println("❌ Actor no encontrado.");
        }
    }

    private void listar() {
        List<Actor> lista = manager.getAll();
        for (Actor a : lista) {
            System.out.println(a.getId() + ": " + a.getFirstName() + " " + a.getLastName());
        }
    }

    private void actualizar() {
        System.out.print("ID del actor a actualizar: ");
        if (!scanner.hasNextInt()) {
            System.out.println("❌ Entrada inválida.");
            scanner.nextLine();
            return;
        }
        int id = scanner.nextInt();
        scanner.nextLine();
        Actor a = manager.get(id);
        if (a != null) {
            System.out.print("Nuevo nombre: ");
            a.setFirstName(scanner.nextLine());
            System.out.print("Nuevo apellido: ");
            a.setLastName(scanner.nextLine());
            manager.put(a);
        } else {
            System.out.println("❌ Actor no encontrado.");
        }
    }

    private void eliminar() {
        System.out.print("ID del actor a eliminar: ");
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
