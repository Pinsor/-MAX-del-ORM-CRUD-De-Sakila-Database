package com.sakila;

import com.sakila.conexion.ConexionDB;
import com.sakila.controladores.ActorController;
import com.sakila.controladores.FilmController;
import com.sakila.gestor.ActorManager;
import com.sakila.gestor.FilmManager;

import java.sql.Connection;
import java.util.Scanner;

public class App {

    // 📌 Scanner compartido para toda la clase
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ConexionDB conexionDB = new ConexionDB();
        Connection conn = conexionDB.conectar();

        if (conn == null) {
            System.out.println("❌ No se pudo conectar a la base de datos.");
            return;
        }

        int opcion = 0;

        do {
            System.out.println("\n==== SISTEMA DE GESTIÓN SAKILA ====");
            System.out.println("1. Gestión de Actores");
            System.out.println("2. Gestión de Películas");
            System.out.println("3. Ver Reportes");
            System.out.println("4. Salir");
            System.out.print("Selecciona una opción: ");

            if (!scanner.hasNextInt()) {
                System.out.println("❌ Entrada inválida. Solo números.");
                scanner.nextLine();
                continue;
            }

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> new ActorController(new ActorManager(conn)).mostrarMenu();
                case 2 -> new FilmController(new FilmManager(conn)).mostrarMenu();
                case 3 -> mostrarMenuReportes(conn);
                case 4 -> System.out.println("👋 Saliendo del sistema...");
                default -> System.out.println("❌ Opción no válida.");
            }

        } while (opcion != 4);

        conexionDB.cerrarConexion();
        scanner.close(); // Cerrar una sola vez aquí
    }

    // ✅ Submenú de reportes usando el mismo Scanner
    public static void mostrarMenuReportes(Connection conn) {
        Reportes reportes = new Reportes(conn);
        int opcion = 0;

        do {
            System.out.println("\n📊 === MENÚ DE REPORTES ===");
            System.out.println("1. Total de películas");
            System.out.println("2. Actores por película");
            System.out.println("3. Rentas por ciudad");
            System.out.println("4. Volver al menú principal");
            System.out.print("Selecciona una opción: ");

            if (!scanner.hasNextInt()) {
                System.out.println("❌ Entrada inválida.");
                scanner.nextLine();
                continue;
            }

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> reportes.totalPeliculas();
                case 2 -> reportes.actoresPorPeliculas();
                case 3 -> reportes.rentasPorCiudad();
                case 4 -> System.out.println("🔙 Volviendo al menú principal...");
                default -> System.out.println("❌ Opción no válida.");
            }

        } while (opcion != 4);
    }
}
