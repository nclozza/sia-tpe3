package com.sia.tp3.corte;

import com.sia.tp3.Personaje;
import com.sia.tp3.Poblacion;

public class Estructura implements InterfazCorte {

    private double porcentaje;

    public Estructura(final double porcentaje) {
        this.porcentaje = porcentaje;
    }

    @Override
    public boolean evaluar(final Poblacion poblacion) {

        int contador;

        contador = 0;
        for (Personaje personaje : poblacion.getPersonajes()) {
            String hashPersonaje = personaje.toHash();
            if (poblacion.getHashSetPersonajes().contains(hashPersonaje)) {
                contador++;
            }
        }

        return (1.0 * contador) / poblacion.getPersonajes().size() >= porcentaje;
    }
}
