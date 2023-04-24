package conexion.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static conexion.sql.ConectorSQL.*;

public class CreateTables {

    ConectorSQL conector = new ConectorSQL();

    public static void createTables() {
        Connection conn = null;
        Statement stmt = null;

        try {

            // Paso 1: Registrar el driver JDBC
            Class.forName("com.mysql.jdbc.Driver");

            // Paso 2: Abrir la conexión
            System.out.println("Conectando a la base de datos...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Paso 3: Crear la sentencia SQL
            System.out.println("Creando tablas en la base de datos...");
            stmt = conn.createStatement();
            String sql = "CREATE TABLE equipo " +
                    "(id INTEGER not NULL AUTO_INCREMENT, " +
                    " nombre VARCHAR(255), " +
                    " descripcion VARCHAR(255), " +
                    " PRIMARY KEY (id))";
            stmt.executeUpdate(sql);
            System.out.println("Tabla equipo creada correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al crear la tabla: equipo " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
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
            System.out.println("Tabla partido creada correctamente...");

        } catch(SQLException e) {
            System.out.println("Error al crear la tabla: Partido " + e.getMessage());
            e.printStackTrace();
        } catch(Exception e) {
            // Manejar errores Class.forName
            e.printStackTrace();
        }

        finally {
            // Cerrar los recursos utilizados
            try {
                if(stmt != null)
                    conn.close();
            } catch(SQLException se) {
            } // no hacer nada

            try {
                if(conn != null)
                    conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            } // Cerrar la conexión
        }
        System.out.println("Fin del programa.");
    }

}
