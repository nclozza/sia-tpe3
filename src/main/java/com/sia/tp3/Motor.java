package com.sia.tp3;

import com.sia.tp3.corte.InterfazCorte;
import com.sia.tp3.grafico.Graficador;
import com.sia.tp3.grafico.Punto2D;
import com.sia.tp3.reemplazo.InterfazReemplazo;

import java.util.ArrayList;
import java.util.LinkedList;

public class Motor {

    private InterfazReemplazo reemplazo;
    private InterfazCorte corte;

    public Motor(final InterfazReemplazo reemplazo, final InterfazCorte corte) {
        this.reemplazo = reemplazo;
        this.corte = corte;
    }

    public void correr(final Poblacion poblacion, final boolean mostrarGraficos) {

        LinkedList<Punto2D> mejoresDesempenios = new LinkedList<>();
        LinkedList<Punto2D> peoresDesempenios = new LinkedList<>();
        LinkedList<Punto2D> promedioDesempenios = new LinkedList<>();

        while (!corte.evaluar(poblacion)) {

            ArrayList<Personaje> nuevaGeneracion = nuevaGeneracion(poblacion);
            poblacion.setPersonajes(nuevaGeneracion);
            poblacion.aumentarGeneracion();
            poblacion.aniadirTodosLosPersonajesAlHashSet();
            poblacion.agregarMejorDesempenio();

            mejoresDesempenios.add(new Punto2D(poblacion.getNumeroDeGeneracion(), poblacion.getMejorDesempenio()));
            peoresDesempenios.add(new Punto2D(poblacion.getNumeroDeGeneracion(), poblacion.getPeorDesempenio()));
            promedioDesempenios.add(new Punto2D(poblacion.getNumeroDeGeneracion(), poblacion.getDesempenioPromedio()));
        }

        if (mostrarGraficos) {
            Graficador.mostrarGraficoMejoresDesempenios(mejoresDesempenios, peoresDesempenios, promedioDesempenios);
        }
    }

    private ArrayList<Personaje> nuevaGeneracion(Poblacion poblacion) {

        ArrayList<Personaje> nuevaGeneracion = reemplazo.hacer(poblacion.getPersonajes(),
                poblacion.getNumeroDeGeneracion());

        return nuevaGeneracion;
    }
}
