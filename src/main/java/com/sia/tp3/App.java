package com.sia.tp3;

import com.sia.tp3.cruce.*;
import com.sia.tp3.mutacion.Gen;
import com.sia.tp3.mutacion.InterfazMutacion;
import com.sia.tp3.mutacion.MultiGen;
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

        int cantidadDeSeleccion1 = new Double(poblacion.getPersonajes().size() * configuracion.getA()).intValue();
        int cantidadDeSeleccion2 = poblacion.getPersonajes().size() - cantidadDeSeleccion1;
        int cantidadPoblacionReemplazo1 = new Double(poblacion.getPersonajes().size() * configuracion.getB()).intValue();
        int cantidadPoblacionReemplazo2 = poblacion.getPersonajes().size() - cantidadPoblacionReemplazo1;

        InterfazCruce cruce = obtenerCruce(configuracion);
        InterfazMutacion mutacion = obtenerMutacion(configuracion);
        InterfazReemplazo reemplazo1 = obtenerReemplazo(configuracion.getMetodoReemplazo1(),
                configuracion.getCantidadDeReemplazo1());
        InterfazReemplazo reemplazo2 = obtenerReemplazo(configuracion.getMetodoReemplazo2(),
                configuracion.getCantidadDeReemplazo2());
        InterfazSeleccion seleccion1 = obtenerSeleccion(configuracion.getMetodoSeleccion1());
        InterfazSeleccion seleccion2 = obtenerSeleccion(configuracion.getMetodoSeleccion2());

        Motor motor = new Motor(cruce, mutacion, reemplazo1, reemplazo2, seleccion1, seleccion2, cantidadDeSeleccion1
                , cantidadDeSeleccion2, cantidadPoblacionReemplazo1, cantidadPoblacionReemplazo2);

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

    public static InterfazReemplazo obtenerReemplazo(String metodoReemplazo, int cantidadDeReemplazo) {

        InterfazReemplazo reemplazo = new Reemplazo1(cantidadDeReemplazo);

        switch (metodoReemplazo) {
            case "reemplazo 1":
                break;

            case "reemplazo 2":
                reemplazo = new Reemplazo2(cantidadDeReemplazo);
                break;

            case "reemplazo 3":
                break;
        }
        return reemplazo;
    }

    public static InterfazSeleccion obtenerSeleccion(String metodoSeleccion) {

        InterfazSeleccion seleccion = new Elite();

        switch (metodoSeleccion) {
            case "elite":
                break;
        }

        return seleccion;
    }
}
