package com.sia.tp3;

import com.sia.tp3.Mutacion.Gen;
import com.sia.tp3.Mutacion.InterfazMutacion;
import com.sia.tp3.Mutacion.MultiGen;
import com.sia.tp3.cruce.*;
import com.sia.tp3.reemplazo.InterfazReemplazo;
import com.sia.tp3.reemplazo.Reemplazo1;
import com.sia.tp3.reemplazo.Reemplazo2;
import com.sia.tp3.seleccion.Elite;
import com.sia.tp3.seleccion.InterfazSeleccion;

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


        for (int i = 0; i < poblacion.getPersonajes().size(); i++) {
            System.out.println("Personaje nro: " + i);
            System.out.println("Desempenio: " + poblacion.getPersonajes().get(i).getDesempenio());
            poblacion.getPersonajes().get(i).imprimirGenes();
        }

        int aux = 0;
        while (aux++ < configuracion.getGeneraciones()) {
            poblacion.setPersonajes(motor.correr(poblacion));
        }


        for (int i = 0; i < poblacion.getPersonajes().size(); i++) {
            System.out.println("Personaje nro: " + i);
            System.out.println("Desempenio: " + poblacion.getPersonajes().get(i).getDesempenio());
            poblacion.getPersonajes().get(i).imprimirGenes();
        }
    }


    public static InterfazCruce obtenerCruce(Configuracion configuracion) {

        InterfazCruce cruce = new EnUnPunto(Math.toIntExact(configuracion.getLocus1()));

        switch (configuracion.getMetodoCruce()) {
            case "en un punto":
                break;

            case "en dos puntos":
                cruce = new EnDosPuntos(Math.toIntExact(configuracion.getLocus1()),
                        Math.toIntExact(configuracion.getLocus2()));
                break;

            case "uniforme":
                cruce = new Uniforme(configuracion.getProbabilidadCruceUniforme());
                break;

            case "anular":
                cruce = new Anular(Math.toIntExact(configuracion.getLocus1()),
                        Math.toIntExact(configuracion.getSegmento()));
                break;
        }
        return cruce;
    }

    public static InterfazMutacion obtenerMutacion(Configuracion configuracion) {

        InterfazMutacion mutacion = new Gen(configuracion.getProbabilidadDeMutacion(), configuracion.getGenAMutar());

        switch (configuracion.getMetodoMutacion()) {
            case "gen":
                mutacion = new Gen(configuracion.getProbabilidadDeMutacion(), configuracion.getGenAMutar());
                break;

            case "multigen":
                mutacion = new MultiGen(configuracion.getProbabilidadDeMutacion());
                break;
        }
        return mutacion;
    }

    public static InterfazReemplazo obtenerReemplazo(Configuracion configuracion) {

        InterfazReemplazo reemplazo = new Reemplazo1();

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
