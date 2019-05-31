package com.sia.tp3;

import com.sia.tp3.cruce.*;
import com.sia.tp3.mutacion.Gen;
import com.sia.tp3.mutacion.InterfazMutacion;
import com.sia.tp3.reemplazo.InterfazReemplazo;
import com.sia.tp3.reemplazo.Reemplazo1;
import com.sia.tp3.reemplazo.Reemplazo2;
import com.sia.tp3.seleccion.Elite;
import com.sia.tp3.seleccion.InterfazSeleccion;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        Configuracion configuracion = new Configuracion();
        Multiplicador multiplicador = new Multiplicador(configuracion.getFuerza(), configuracion.getAgilidad(),
                configuracion.getPericia(), configuracion.getResistencia(), configuracion.getVida());
        Poblacion poblacion = new Poblacion(configuracion.getPersonaje(), multiplicador);

        InterfazCruce cruce = obtenerCruce(configuracion);
        InterfazMutacion mutacion = obtenerMutacion(configuracion);
        InterfazReemplazo reemplazo = obtenerReemplazo(configuracion);
        InterfazSeleccion seleccion = obtenerSeleccion(configuracion);

        Motor motor = new Motor(cruce, mutacion, reemplazo, seleccion);


//        for (int i = 0; i < poblacion.getPersonajes().size(); i++) {
//            System.out.println("Personaje nro: " + i);
//            System.out.println("Desempenio: " + poblacion.getPersonajes().get(i).getDesempenio());
//            poblacion.getPersonajes().get(i).imprimirGenes();
//        }
        ArrayList<Personaje> originales = new ArrayList<>(poblacion.getPersonajes());

        int aux = 0;
        while (aux++ < configuracion.getGeneraciones()) {
            poblacion.setPersonajes(motor.correr(poblacion));
        }

//        for (int i = 0; i < originales.size(); i++) {
//            System.out.println("Personaje nro: " + i);
//            System.out.println("Desempenio: " + originales.get(i).getDesempenio());
//            originales.get(i).imprimirGenes();
//        }
//
//
//        for (int i = 0; i < poblacion.getPersonajes().size(); i++) {
//            System.out.println("Personaje nro: " + i);
//            System.out.println("Desempenio: " + poblacion.getPersonajes().get(i).getDesempenio());
//            poblacion.getPersonajes().get(i).imprimirGenes();
//        }
    }

    public static InterfazCruce obtenerCruce(Configuracion configuracion) {

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

    public static InterfazMutacion obtenerMutacion(Configuracion configuracion) {

        InterfazMutacion mutacion = new Gen(configuracion.getProbabilidadDeMutacion());

        switch (configuracion.getMetodoReemplazo()) {
            case "gen":
                break;
        }
        return mutacion;
    }

    public static InterfazReemplazo obtenerReemplazo(Configuracion configuracion) {

        InterfazReemplazo reemplazo = new Reemplazo1(configuracion.getCantidadDeReemplazo());

        switch (configuracion.getMetodoReemplazo()) {
            case "reemplazo 1":
                break;

            case "reemplazo 2":
                reemplazo = new Reemplazo2(configuracion.getCantidadDeReemplazo());
                break;

            case "reemplazo 3":
                break;
        }
        return reemplazo;
    }

    public static InterfazSeleccion obtenerSeleccion(Configuracion configuracion) {

        InterfazSeleccion seleccion = new Elite();

        switch (configuracion.getMetodoReemplazo()) {
            case "elite":
                break;
        }

        return seleccion;
    }
}
