package org.example;

import org.example.conexion.CreateTables;
import org.example.entitys.Equipo;
import org.example.entitys.Partido;
import org.example.entitys.Pronostico;
import org.example.enums.Resultados;
import org.example.services.InsertData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


       // CreateTables createTables = new CreateTables();

        //Se ejecuta una vez para crear las tablas y después se comenta(Crea las tablas Partidos y Equipos)
        //createTables.createTables();

        //InsertData insertData = new InsertData();
        //Se ejecuta una vez para insertar datos y después se comenta(Inserta datos en las tablas Partidos y Equipos)
        //insertData.ejecutar();

        Scanner scanner = new Scanner(System.in);

// Lista de equipos
        List<Equipo> equipos = new ArrayList<>();
        equipos.add(new Equipo("Equipo A"));
        equipos.add(new Equipo("Equipo B"));
        equipos.add(new Equipo("Equipo C"));
        equipos.add(new Equipo("Equipo D"));

// Lista de partidos
        List<Partido> partidos = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            Equipo equipo1 = equipos.get(random.nextInt(equipos.size()));
            Equipo equipo2 = equipos.get(random.nextInt(equipos.size()));
            while (equipo1 == equipo2) {
                equipo2 = equipos.get(random.nextInt(equipos.size()));
            }
            int golesEq1 = random.nextInt(7);
            int golesEq2 = random.nextInt(7);
            partidos.add(new Partido(equipo1, equipo2, golesEq1, golesEq2));
        }

// Permite a dos usuarios ingresar su apuesta por cada partido y compiten entre ellos
        for (int i = 0; i < partidos.size(); i++) {
            Partido partido = partidos.get(i);
            System.out.println("Partido " + (i + 1) + ": " + partido.getEquipo1().getNombre() + " vs. " + partido.getEquipo2().getNombre());
            System.out.println("Usuario 1, ingrese su apuesta (1 para " + partido.getEquipo1().getNombre() + ", 2 para " + partido.getEquipo2().getNombre() + ", 3 para empate):");
            int apuesta1 = scanner.nextInt();
            System.out.println("Usuario 2, ingrese su apuesta (1 para " + partido.getEquipo1().getNombre() + ", 2 para " + partido.getEquipo2().getNombre() + ", 3 para empate):");
            int apuesta2 = scanner.nextInt();
            Pronostico pronostico1 = new Pronostico(partido, partido.getEquipo1(), apuesta1 == 1 ? Resultados.GANADOR : apuesta1 == 2 ? Resultados.PERDEDOR : Resultados.EMPATE);
            Pronostico pronostico2 = new Pronostico(partido, partido.getEquipo2(), apuesta2 == 1 ? Resultados.GANADOR : apuesta2 == 2 ? Resultados.PERDEDOR : Resultados.EMPATE);
            int puntos1 = pronostico1.puntos();
            int puntos2 = pronostico2.puntos();
            if (puntos1 > puntos2) {
                System.out.println("¡Usuario 1 acertó su apuesta y ganó el punto!");
            } else if (puntos2 > puntos1) {
                System.out.println("¡Usuario 2 acertó su apuesta y ganó el punto!");
            } else {
                System.out.println("Ambos usuarios fallaron su apuesta o acertaron igual, no se otorga punto.");
            }
        }










    }
}
