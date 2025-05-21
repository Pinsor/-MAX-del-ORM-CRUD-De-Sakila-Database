package com.sakila.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private final String url = "jdbc:mysql://localhost:3306/sakila";
    private final String usuario = "Stone";
    private final String contraseña = "Msql#2025_Segura!";
    private Connection conn;

    public Connection conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("✅ Conexión establecida.");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("❌ Error en la conexión: " + e.getMessage());
        }
        return conn;
    }

    public void cerrarConexion() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("🔒 Conexión cerrada.");
            }
        } catch (SQLException e) {
            System.out.println("⚠️ Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
