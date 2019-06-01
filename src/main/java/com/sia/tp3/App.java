package com.sia.tp3;

import com.sia.tp3.corte.InterfazCorte;
import com.sia.tp3.corte.MaximaCantidadDeGeneraciones;
import com.sia.tp3.cruce.*;
import com.sia.tp3.mutacion.Gen;
import com.sia.tp3.mutacion.InterfazMutacion;
import com.sia.tp3.mutacion.MultiGen;
import com.sia.tp3.reemplazo.InterfazReemplazo;
import com.sia.tp3.reemplazo.Reemplazo1;
import com.sia.tp3.reemplazo.Reemplazo2;
import com.sia.tp3.reemplazo.Reemplazo3;
import com.sia.tp3.seleccion.Elite;
import com.sia.tp3.seleccion.InterfazSeleccion;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        Configuracion configuracion = new Configuracion();
        Multiplicador multiplicador = new Multiplicador(configuracion.getFuerza(), configuracion.getAgilidad(),
                configuracion.getPericia(), configuracion.getResistencia(), configuracion.getVida());
        Poblacion poblacion = new Poblacion(configuracion.getPersonaje(), multiplicador, 0);

        double modificadorA = configuracion.getA();
        double modificadorB = configuracion.getB();

        InterfazCruce cruce = obtenerCruce(configuracion);
        InterfazMutacion mutacion = obtenerMutacion(configuracion);
        InterfazSeleccion seleccion1 = obtenerSeleccion(configuracion.getMetodoSeleccion1());
        InterfazSeleccion seleccion2 = obtenerSeleccion(configuracion.getMetodoSeleccion2());
        InterfazCorte corte = obtenerCorte(configuracion);

        InterfazReemplazo reemplazo1 = obtenerReemplazo(configuracion.getMetodoReemplazo1(), modificadorA,
                configuracion.getCantidadDeReemplazo1(), poblacion.getPersonajes().size(), seleccion1, seleccion2,
                cruce, mutacion);

        InterfazReemplazo reemplazo2 = obtenerReemplazo(configuracion.getMetodoReemplazo2(), modificadorA,
                configuracion.getCantidadDeReemplazo2(), poblacion.getPersonajes().size(), seleccion1, seleccion2,
                cruce, mutacion);


        Motor motor = new Motor(reemplazo1, reemplazo2, modificadorB, corte);

//        System.out.println("GENERACION N: " + poblacion.getNumeroDeGeneracion());
//        for (int i = 0; i < poblacion.getPersonajes().size(); i++) {
//            System.out.println("Personaje nro: " + i);
//            System.out.println("Desempenio: " + poblacion.getPersonajes().get(i).getDesempenio());
//            poblacion.getPersonajes().get(i).imprimirGenes();
//        }

        motor.correr(poblacion);

        double desempenio = 0;
        for (Personaje personaje : poblacion.getPersonajes()) {
            if (personaje.getDesempenio() > desempenio) {
                desempenio = personaje.getDesempenio();
            }
        }

        System.out.println("MEJOR DESEMPENIO: " + desempenio);



//        ArrayList<Personaje> original = new ArrayList<>();
//
//        for (Personaje personaje : poblacion.getPersonajes()) {
//            original.add(personaje.copy());
//        }
//
//        for (int i = 0; i < 5; i++) {
//
//            ArrayList<Personaje> aux = new ArrayList<>();
//            for (Personaje personaje : original) {
//                aux.add(personaje.copy());
//            }
//
//            poblacion.setPersonajes(aux);
//            poblacion.resetNumeroDeGeneracion();
//            motor.correr(poblacion);
//
//            double desempenio = 0;
//            for (Personaje personaje : poblacion.getPersonajes()) {
//                if (personaje.getDesempenio() > desempenio) {
//                    desempenio = personaje.getDesempenio();
//                }
//            }
//
//            System.out.println("MEJOR DESEMPENIO: " + desempenio);
//        }

    }

    private static InterfazCorte obtenerCorte(Configuracion configuracion) {

        InterfazCorte corte = new MaximaCantidadDeGeneraciones(configuracion.getGeneraciones());

        switch (configuracion.getMetodoCorte()) {
            case "maxima cantidad":
                break;

            case "estructura":
                break;

            case "contenido":
                break;

            case "entorno a un optimo":
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

    private static InterfazMutacion obtenerMutacion(Configuracion configuracion) {

        InterfazMutacion mutacion = new Gen(configuracion.getProbabilidadDeMutacion(), configuracion.getGenAMutar());

        switch (configuracion.getMetodoReemplazo1()) {
            case "gen":
                break;

            case "multigen":
                mutacion = new MultiGen(configuracion.getProbabilidadDeMutacion());
                break;
        }

        return mutacion;
    }

    private static InterfazReemplazo obtenerReemplazo(String metodoReemplazo, final double modificadorA, final int k,
                                                      final int poblacionTotal, final InterfazSeleccion seleccion1,
                                                      final InterfazSeleccion seleccion2, final InterfazCruce cruce,
                                                      final InterfazMutacion mutacion) {

        InterfazReemplazo reemplazo = new Reemplazo1(seleccion1, seleccion2, cruce, modificadorA, poblacionTotal,
                mutacion);

        switch (metodoReemplazo) {
            case "reemplazo 1":
                break;

            case "reemplazo 2":
                reemplazo = new Reemplazo2(seleccion1, seleccion2, cruce, modificadorA, k, mutacion);
                break;

            case "reemplazo 3":
                reemplazo = new Reemplazo3(seleccion1, seleccion2, cruce, modificadorA, k, mutacion);
                break;
        }

        return reemplazo;
    }

    private static InterfazSeleccion obtenerSeleccion(String metodoSeleccion) {

        InterfazSeleccion seleccion = new Elite();

        switch (metodoSeleccion) {
            case "elite":
                break;
        }

        return seleccion;
    }
}
