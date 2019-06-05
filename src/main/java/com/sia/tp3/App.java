package com.sia.tp3;

import com.sia.tp3.corte.*;
import com.sia.tp3.cruce.*;
import com.sia.tp3.mutacion.*;
import com.sia.tp3.reemplazo.InterfazReemplazo;
import com.sia.tp3.reemplazo.Reemplazo1;
import com.sia.tp3.reemplazo.Reemplazo2;
import com.sia.tp3.reemplazo.Reemplazo3;
import com.sia.tp3.seleccion.*;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        Configuracion configuracion = new Configuracion();
        Multiplicador multiplicador = new Multiplicador(configuracion.getFuerza(), configuracion.getAgilidad(),
                configuracion.getPericia(), configuracion.getResistencia(), configuracion.getVida());
        Poblacion poblacion = new Poblacion(configuracion.getPersonaje(), multiplicador, configuracion.getPath(),
                configuracion.getPoblacion());

        double modificadorA = configuracion.getA();
        double modificadorB = configuracion.getB();

        InterfazCruce cruce = obtenerCruce(configuracion);
        InterfazMutacion mutacion = obtenerMutacion(configuracion, poblacion);
        InterfazSeleccion seleccion1 = obtenerSeleccion(configuracion.getMetodoSeleccion1(),
                configuracion.getPorcentajeDePersonajesTorneos(), configuracion.getSeleccion1UsaBoltzmann());
        InterfazSeleccion seleccion2 = obtenerSeleccion(configuracion.getMetodoSeleccion2(),
                configuracion.getPorcentajeDePersonajesTorneos(), configuracion.getSeleccion2UsaBoltzmann());
        InterfazSeleccion seleccion3 = obtenerSeleccion(configuracion.getMetodoSeleccion3(),
                configuracion.getPorcentajeDePersonajesTorneos(), configuracion.getSeleccion3UsaBoltzmann());
        InterfazSeleccion seleccion4 = obtenerSeleccion(configuracion.getMetodoSeleccion4(),
                configuracion.getPorcentajeDePersonajesTorneos(), configuracion.getSeleccion4UsaBoltzmann());
        InterfazCorte corte = obtenerCorte(configuracion);

        InterfazReemplazo reemplazo = obtenerReemplazo(configuracion.getMetodoReemplazo(), modificadorA, modificadorB,
                configuracion.getCantidadDeReemplazo(), poblacion.getPersonajes().size(), seleccion1, seleccion2,
                seleccion3, seleccion4, cruce, mutacion);

        Motor motor = new Motor(reemplazo, corte);

        // Con la misma data cargada corremos el motor varias veces
        ArrayList<Personaje> original = new ArrayList<>();
        for (Personaje personaje : poblacion.getPersonajes()) {
            original.add(personaje.copy());
        }
        for (int i = 0; i < 1; i++) {

            ArrayList<Personaje> aux = new ArrayList<>();
            for (Personaje personaje : original) {
                aux.add(personaje.copy());
            }

            poblacion.setPersonajes(aux);
            poblacion.resetNumeroDeGeneracion();
            long startTime = System.currentTimeMillis();
            motor.correr(poblacion, configuracion.getGenerarGraficos());
            long endTime = System.currentTimeMillis();
            long totalTime = (endTime - startTime) / 1000;

            System.out.println("MEJOR DESEMPENIO: " + poblacion.getMejorDesempenio());
            System.out.println("PEOR DESEMPENIO: " + poblacion.getPeorDesempenio());
            System.out.println("DESEMPENIO PROMEDIO: " + poblacion.getDesempenioPromedio());
            System.out.println("NÚMERO DE GENERACIÓN: " + poblacion.getNumeroDeGeneracion());
            System.out.println("TIEMPO: " + totalTime  + "s");
            System.out.println();
        }
    }

    private static InterfazCorte obtenerCorte(Configuracion configuracion) {

        InterfazCorte corte = new MaximaCantidadDeGeneraciones(configuracion.getGeneraciones());

        switch (configuracion.getMetodoCorte()) {
            case "maxima cantidad":
                break;

            case "estructura":
                corte = new Estructura(configuracion.getPorcentaje());
                break;

            case "contenido":
                corte = new Contenido(configuracion.getGeneracionesAVerificar());
                break;

            case "optimo":
                corte = new Optimo(configuracion.getOptimo());
                break;
        }

        return corte;
    }


    private static InterfazCruce obtenerCruce(Configuracion configuracion) {

        InterfazCruce cruce = new EnUnPunto(configuracion.getLocus1());

        switch (configuracion.getMetodoCruce()) {
            case "un punto":
                break;
            case "dos puntos":
                cruce = new EnDosPuntos(Math.toIntExact(configuracion.getLocus1()), configuracion.getLocus2());
                break;

            case "uniforme":
                cruce = new Uniforme(configuracion.getProbabilidadCruceUniforme());
                break;
            case "anular":
                cruce = new Anular(configuracion.getLocus1(), configuracion.getSegmento());
                break;
        }

        return cruce;
    }

    private static InterfazMutacion obtenerMutacion(Configuracion configuracion, Poblacion poblacion) {

        InterfazProbabilidad probabilidad = new MutacionUniforme(configuracion.getProbabilidadDeMutacion());

        if (!configuracion.getMutacionUniforme()) {
            probabilidad = new MutacionNoUniforme(configuracion.getBaseExponencialMutacionNoUniforme());
        }

        InterfazMutacion mutacion = new Gen(probabilidad, configuracion.getGenAMutar(), poblacion);

        switch (configuracion.getMetodoReemplazo()) {
            case "gen":
                break;

            case "multigen":
                mutacion = new MultiGen(probabilidad, poblacion);
                break;
        }

        return mutacion;
    }

    private static InterfazReemplazo obtenerReemplazo(String metodoReemplazo, final double modificadorA,
                                                      final double modificadorB, final int k,
                                                      final int poblacionTotal, final InterfazSeleccion seleccion1,
                                                      final InterfazSeleccion seleccion2,
                                                      final InterfazSeleccion seleccion3,
                                                      final InterfazSeleccion seleccion4, final InterfazCruce cruce,
                                                      final InterfazMutacion mutacion) {

        InterfazReemplazo reemplazo = new Reemplazo1(seleccion1, seleccion2, seleccion3, seleccion4, cruce,
                modificadorA, modificadorB, poblacionTotal, mutacion);

        switch (metodoReemplazo) {
            case "reemplazo 1":
                break;

            case "reemplazo 2":
                reemplazo = new Reemplazo2(seleccion1, seleccion2, seleccion3, seleccion4, cruce, modificadorA,
                        modificadorB, k, mutacion);
                break;

            case "reemplazo 3":
                reemplazo = new Reemplazo3(seleccion1, seleccion2, seleccion3, seleccion4, cruce, modificadorA,
                        modificadorB, k, mutacion);
                break;
        }

        return reemplazo;
    }

    private static InterfazSeleccion obtenerSeleccion(String metodoSeleccion, Double porcentajeDePersonajesTorneos,
                                                      Boolean usaBoltzmann) {

        InterfazSeleccion seleccion = new Elite(usaBoltzmann);

        switch (metodoSeleccion) {
            case "elite":
                break;

            case "ruleta":
                seleccion = new Ruleta(usaBoltzmann);
                break;

            case "universal":
                seleccion = new Universal(usaBoltzmann);
                break;

            case "torneos deterministica":
                seleccion = new TorneosDeterministica(porcentajeDePersonajesTorneos, usaBoltzmann);
                break;

            case "torneos probabilistica":
                seleccion = new TorneosProbabilistica(porcentajeDePersonajesTorneos, usaBoltzmann);
                break;

            case "ranking":
                seleccion = new Ranking(usaBoltzmann);
                break;
        }

        return seleccion;
    }
}
