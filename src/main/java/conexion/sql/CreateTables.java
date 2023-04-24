package conexion.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.DatabaseMetaData;


import static conexion.sql.ConectorSQL.*;

public class CreateTables {

    public static void createTables() {
        Connection conn = null;
        Statement stmt = null;

        try {

            // Paso 1: Registrar el driver JDBC
            Class.forName("com.mysql.jdbc.Driver");

            // Paso 2: Abrir la conexión
            System.out.println("Conectando a la base de datos...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Verificar si la tabla equipo ya existe
            if (!tableExists(conn, "equipo")) {
                // Paso 3: Crear la sentencia SQL para la tabla equipo
                System.out.println("Creando tabla equipo en la base de datos...");
                stmt = conn.createStatement();
                String sql = "CREATE TABLE equipo " +
                        "(id INTEGER not NULL AUTO_INCREMENT, " +
                        " nombre VARCHAR(255), " +
                        " descripcion VARCHAR(255), " +
                        " PRIMARY KEY (id))";
                stmt.executeUpdate(sql);
                System.out.println("Tabla equipo creada correctamente.");
            } else {
                System.out.println("La tabla equipo ya existe.");
            }

            // Verificar si la tabla partido ya existe
            if (!tableExists(conn, "partido")) {
                // Paso 4: Crear la sentencia SQL para la tabla partido
                System.out.println("Creando tabla partido en la base de datos...");
                String sql = "CREATE TABLE partido " +
                        "(id INTEGER not NULL, " +
                        " equipo1_id INTEGER, " +
                        " equipo2_id INTEGER, " +
                        " golesEq1 int, " +
                        " golesEq2 int, " +
                        " PRIMARY KEY (id), " +
                        " FOREIGN KEY (equipo1_id) REFERENCES equipo(id), " +
                        " FOREIGN KEY (equipo2_id) REFERENCES equipo(id))";
                stmt.executeUpdate(sql);
                System.out.println("Tabla partido creada correctamente.");
            } else {
                System.out.println("La tabla partido ya existe.");
            }

        } catch (SQLException e) {
            System.out.println("Error al crear la tabla: " + e.getMessage());
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
        }
        System.out.println("Fin del programa.");
    }

    /**
     * Verifica si una tabla existe en la base de datos
     * @param conn la conexión a la base de datos
     * @param tableName el nombre de la tabla
     * @return true si la tabla existe, false en caso contrario
     * @throws SQLException si ocurre un error al obtener la información de las tablas
     */
    private static boolean tableExists(Connection conn, String tableName) throws SQLException {
        DatabaseMetaData meta = conn.getMetaData();
        String[] types = {"TABLE"};
        try (var rs = meta.getTables(null, null, tableName, types)) {
            return rs.next();
        }
    }
}
