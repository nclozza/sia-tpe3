package com.sia.tp3;

import com.sia.tp3.Mutacion.Gen;
import com.sia.tp3.Mutacion.InterfazMutacion;
import com.sia.tp3.cruce.EnDosPuntos;
import com.sia.tp3.cruce.EnUnPunto;
import com.sia.tp3.cruce.InterfazCruce;
import com.sia.tp3.cruce.Uniforme;
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

        InterfazCruce cruce = new EnUnPunto(2);
        switch (configuracion.getMetodoCruce()) {
            case "en un punto":
                cruce = new EnUnPunto(2);
                break;

            case "en dos puntos":
                cruce = new EnDosPuntos(2, 4);
                break;

            case "uniforme":
                cruce = new Uniforme(0.01);
                break;
        }

        InterfazMutacion mutacion = new Gen(configuracion.getProbabilidadDeMutacion());
        switch (configuracion.getMetodoReemplazo()) {
            case "gen":
                mutacion = new Gen(configuracion.getProbabilidadDeMutacion());
                break;
        }

        InterfazReemplazo reemplazo = new Reemplazo1();
        switch (configuracion.getMetodoReemplazo()) {
            case "reemplazo 1":
                reemplazo = new Reemplazo1();
                break;

            case "reemplazo 2":
                reemplazo = new Reemplazo2(configuracion.getCantidadDeReemplazo());
                break;

            case "reemplazo 3":
                break;
        }

        InterfazSeleccion seleccion = new Elite();
        switch (configuracion.getMetodoReemplazo()) {
            case "elite":
                seleccion = new Elite();
                break;
        }

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
}
