//package org.example.services;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//import static org.example.conexion.ConectorSQL.*;
//
//public class PartidosService {
//    Connection conn = null;
//    PreparedStatement stmt = null;
//
//        try {
//        // Paso 1: Registrar el driver JDBC
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//        } catch (ClassNotFoundException ex) {
//            throw new RuntimeException(ex);
//        }
//
//        // Paso 2: Abrir la conexión
//        System.out.println("Conectando a la base de datos...");
//        try {
//            conn = DriverManager.getConnection(DB_URL, USER, PASS);
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        }
//
//        // Insertar un equipo en la tabla equipo
//        String equipoInsert = "INSERT INTO equipo (nombre, descripcion) VALUES (?, ?)";
//        try {
//            stmt = conn.prepareStatement(equipoInsert);
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        }
//        try {
//            stmt.setString(1, "Barcelona");
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        }
//        try {
//            stmt.setString(2, "Equipo de fútbol de Barcelona");
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        }
//        try {
//            stmt.executeUpdate();
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        }
//        System.out.println("Equipo insertado correctamente.");
//
//        // Insertar un partido en la tabla partido
//        String partidoInsert = "INSERT INTO partido (equipo1_id, equipo2_id, golesEq1, golesEq2) VALUES (?, ?, ?, ?)";
//        try {
//            stmt = conn.prepareStatement(partidoInsert);
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        }
//        try {
//            stmt.setInt(1, 1); // Id del equipo 1
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        }
//        try {
//            stmt.setInt(2, 2); // Id del equipo 2
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        }
//        try {
//            stmt.setInt(3, 2); // Goles equipo 1
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        }
//        try {
//            stmt.setInt(4, 1); // Goles equipo 2
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        }
//        try {
//            stmt.executeUpdate();
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        }
//        System.out.println("Partido insertado correctamente.");
//
//    } catch (
//    SQLException e) {
//        System.out.println("Error al insertar datos: " + e.getMessage());
//    } catch (ClassNotFoundException e) {
//        throw new RuntimeException(e);
//    } finally {
//        // Cerrar los recursos utilizados
//        try {
//            if (stmt != null)
//                stmt.close();
//        } catch (SQLException se) {
//        } // no hacer nada
//
//        try {
//            if (conn != null)
//                conn.close();
//        } catch (SQLException se) {
//            se.printStackTrace();
//        } // Cerrar la conexión
//    }
//        System.out.println("Fin del programa.");
//}
//
