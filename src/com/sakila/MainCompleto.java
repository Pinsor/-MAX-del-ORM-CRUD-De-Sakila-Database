package com.sakila;

import com.sakila.conexion.ConexionDB;
import com.sakila.gestor.ActorManager;
import com.sakila.modelo.Actor;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class MainCompleto {

    public static void main(String[] args) {
        ConexionDB conexionDB = new ConexionDB();
        Connection conn = conexionDB.conectar();
        ActorManager manager = new ActorManager(conn);

        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n---- MENÚ CRUD ACTOR ----");
            System.out.println("1. Insertar actor");
            System.out.println("2. Buscar actor por ID");
            System.out.println("3. Listar todos los actores");
            System.out.println("4. Actualizar actor");
            System.out.println("5. Eliminar actor");
            System.out.println("6. Salir");
            System.out.print("Selecciona una opción: ");

            if (!scanner.hasNextInt()) {
                System.out.println("❌ Entrada inválida. Por favor ingresa un número.");
                scanner.nextLine(); // limpiar entrada
                continue;
            }

            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Apellido: ");
                    String apellido = scanner.nextLine();
                    manager.post(new Actor(0, nombre, apellido));
                    break;
                case 2:
                    System.out.print("ID del actor: ");
                    if (!scanner.hasNextInt()) {
                        System.out.println("❌ Entrada inválida. Debes ingresar un número.");
                        scanner.nextLine();
                        break;
                    }
                    int idBuscar = scanner.nextInt();
                    Actor actorEncontrado = manager.get(idBuscar);
                    if (actorEncontrado != null) {
                        System.out.println("Encontrado: " + actorEncontrado.getFirstName() + " " + actorEncontrado.getLastName());
                    } else {
                        System.out.println("❌ No se encontró el actor.");
                    }
                    break;
                case 3:
                    List<Actor> actores = manager.getAll();
                    for (Actor a : actores) {
                        System.out.println(a.getId() + ": " + a.getFirstName() + " " + a.getLastName());
                    }
                    break;
                case 4:
                    System.out.print("ID del actor a actualizar: ");
                    if (!scanner.hasNextInt()) {
                        System.out.println("❌ Entrada inválida.");
                        scanner.nextLine();
                        break;
                    }
                    int idActualizar = scanner.nextInt();
                    scanner.nextLine();
                    Actor actorActualizar = manager.get(idActualizar);
                    if (actorActualizar != null) {
                        System.out.print("Nuevo nombre: ");
                        actorActualizar.setFirstName(scanner.nextLine());
                        System.out.print("Nuevo apellido: ");
                        actorActualizar.setLastName(scanner.nextLine());
                        manager.put(actorActualizar);
                    } else {
                        System.out.println("❌ Actor no encontrado.");
                    }
                    break;
                case 5:
                    System.out.print("ID del actor a eliminar: ");
                    if (!scanner.hasNextInt()) {
                        System.out.println("❌ Entrada inválida.");
                        scanner.nextLine();
                        break;
                    }
                    int idEliminar = scanner.nextInt();
                    manager.delete(idEliminar);
                    break;
                case 6:
                    System.out.println("👋 Saliendo del programa...");
                    break;
                default:
                    System.out.println("❗ Opción no válida.");
            }

        } while (opcion != 6);

        conexionDB.cerrarConexion();
        scanner.close();
    }
}
