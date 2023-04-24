package org.example;

import org.example.conexion.CreateTables;
import org.example.services.InsertData;

public class Main {

    public static void main(String[] args) {


        CreateTables createTables = new CreateTables();

        //Se ejecuta una vez para crear las tablas y después se comenta(Crea las tablas Partidos y Equipos)
        //createTables.createTables();

        InsertData insertData = new InsertData();
        //Se ejecuta una vez para insertar datos y después se comenta(Inserta datos en las tablas Partidos y Equipos)
        insertData.ejecutar();









    }
}
