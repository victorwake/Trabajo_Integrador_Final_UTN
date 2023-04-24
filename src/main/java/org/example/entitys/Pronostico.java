package org.example.entitys;

import org.example.enums.Resultados;

public class Pronostico {
    private Partido partido;
    private Equipo equipo;
    private Resultados resultado;

    public Pronostico(Partido partido, Equipo equipo, Resultados resultado) {
        super();
        this.partido = partido;
        this.equipo = equipo;
        this.resultado = resultado;
    }

    public Partido getPartido() {
        return this.partido;
    }

    public Equipo getEquipo() {
        return this.equipo;
    }

    public Resultados getResultado() {
        return this.resultado;
    }

    public int puntos() {
        Resultados resultadoReal = this.partido.resultado(this.equipo);
        if (this.resultado.equals(resultadoReal)) {
            return 1;
        } else {
            return 0;
        }

    }
}
