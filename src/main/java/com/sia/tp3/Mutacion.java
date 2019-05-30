package com.sia.tp3;

import java.util.ArrayList;
import java.util.Random;

public class Mutacion {


    // TODO: CAMBIAR
    // PREGUNTAR:
    // ¿Cómo hago para mutar? ¿Le sumo un valor fijo?
    // ¿Qué gen muta? ¿Se define en config.json o es random entre todos los genes?
    private static double probabilidadDeMutacion = 0.1;
    private static int genAMutar = 2;
    private static int caracteristicaAMutar = 1;
    private static double mutacion = 0.3;

    public static void mutarGeneracion(ArrayList<Personaje> personajes) {
        gen(personajes);
    }

    private static void gen(ArrayList<Personaje> personajes) {
        Random r = new Random();

        for (final Personaje personaje : personajes) {
            if (probabilidadDeMutacion >= (1.0) * r.nextDouble()) {
                personaje.getGenes()[genAMutar][caracteristicaAMutar] += mutacion;
            }
        }
    }

    private static void multiGen(ArrayList<Personaje> personajes) {

    }
}
