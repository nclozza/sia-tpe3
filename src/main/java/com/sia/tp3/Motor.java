package com.sia.tp3;

import com.sia.tp3.corte.InterfazCorte;
import com.sia.tp3.reemplazo.InterfazReemplazo;

import java.util.ArrayList;

public class Motor {

    private InterfazReemplazo reemplazo;
    private InterfazCorte corte;

    public Motor(final InterfazReemplazo reemplazo, final InterfazCorte corte) {
        this.reemplazo = reemplazo;
        this.corte = corte;
    }

    public void correr(Poblacion poblacion) {

        while (!corte.evaluar(poblacion)) {

            poblacion.setPersonajes(nuevaGeneracion(poblacion));
            poblacion.aumentarGeneracion();
            poblacion.aniadirTodosLosPersonajesAHashSet();
            poblacion.agregarMejorDesempenio();
        }
    }

    private ArrayList<Personaje> nuevaGeneracion(Poblacion poblacion) {

        ArrayList<Personaje> nuevaGeneracion = reemplazo.hacer(poblacion.getPersonajes(),
                poblacion.getNumeroDeGeneracion());

        // SOLO PARA DEBUGGEAR
        if (nuevaGeneracion.size() != poblacion.getPersonajes().size()) {
            throw new RuntimeException("1 - ERROR EN MOTOR");
        }

        return nuevaGeneracion;
    }
}
