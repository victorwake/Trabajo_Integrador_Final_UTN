package org.example;

import conexion.sql.CreateTables;

public class Main {

    public static void main(String[] args) {


        CreateTables createTables = new CreateTables();

        //Se ejecuta una vez para crear las tablas y después se comenta(Crea las tablas Partidos y Equipos)
        createTables.createTables();








    }
}
