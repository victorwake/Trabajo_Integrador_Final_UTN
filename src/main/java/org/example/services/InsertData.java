package org.example.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import static org.example.conexion.ConectorSQL.*;

public class InsertData {

    public void ejecutar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        Scanner scanner = new Scanner(System.in);

        try {
            // Paso 1: Registrar el driver JDBC
            Class.forName("com.mysql.jdbc.Driver");

            // Paso 2: Abrir la conexión
            System.out.println("Conectando a la base de datos...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Obtener cantidad de equipos a ingresar
            System.out.print("Ingrese la cantidad de equipos a ingresar (o 'exit' para salir): ");
            String input = scanner.nextLine();

            while (!input.equalsIgnoreCase("exit")) {
                int cantidadEquipos = Integer.parseInt(input);

                // Insertar cada equipo en la tabla equipo
                for (int i = 0; i < cantidadEquipos; i++) {
                    System.out.println("Ingrese el nombre y descripción del equipo " + (i+1) + ":");
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Descripción: ");
                    String descripcion = scanner.nextLine();

                    String equipoInsert = "INSERT INTO equipo (nombre, descripcion) VALUES (?, ?)";
                    stmt = conn.prepareStatement(equipoInsert);
                    stmt.setString(1, nombre);
                    stmt.setString(2, descripcion);
                    stmt.executeUpdate();
                    System.out.println("Equipo '" + nombre + "' insertado correctamente.");
                }

                // Solicitar cantidad de equipos a ingresar nuevamente
                System.out.print("Ingrese la cantidad de equipos a ingresar (o 'exit' para salir): ");
                input = scanner.nextLine();
            }

        } catch (SQLException e) {
            System.out.println("Error al insertar datos: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            // Cerrar los recursos utilizados
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se) {
            } // no hacer nada

            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } // Cerrar la conexión

            scanner.close();
        }
        System.out.println("Fin del programa.");
    }
}