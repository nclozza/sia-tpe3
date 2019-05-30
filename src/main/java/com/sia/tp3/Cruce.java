package com.sia.tp3;

import java.util.ArrayList;
import java.util.Random;

public class Cruce {

    public static ArrayList<Personaje> nuevaGeneracion(ArrayList<Personaje> personajes) {

        ArrayList<Personaje> nuevaGeneracion = new ArrayList<>();

        Random r = new Random();
        r.nextInt(personajes.size());

        int contadorNuevaGeneracion = 0;

        while (contadorNuevaGeneracion < personajes.size()) {
            nuevaGeneracion.add(personajes.get(r.nextInt(personajes.size())).copy());

            nuevaGeneracion.add(personajes.get(r.nextInt(personajes.size())).copy());

            // TODO: Hacer generico, no solamente para enUnPunto
            enUnPunto(nuevaGeneracion.get(contadorNuevaGeneracion), nuevaGeneracion.get(contadorNuevaGeneracion + 1),
                    1);

            contadorNuevaGeneracion += 2;
        }

        if ((personajes.size() % 2) != 0) {
            nuevaGeneracion.remove(personajes.size());
        }

        return nuevaGeneracion;
    }

    private static void enUnPunto(Personaje p1, Personaje p2, int punto) {
        enDosPuntos(p1, p2, punto, Genes.CANTIDAD_GENES);
    }

    private static void enDosPuntos(Personaje p1, Personaje p2, int primero, int ultimo) {
        for (int i = primero; i < ultimo; i++) {
            if (i != Genes.ALTURA) {
                cambiar(p1.getGenes()[i], p2.getGenes()[i], Genes.CANTIDAD_CARACTERISTICAS);

            } else {
                cambiar(p1.getGenes()[i], p2.getGenes()[i], 1);
            }
        }

        p1.setDesempenio(p1.calcularDesempenio());
        p2.setDesempenio(p2.calcularDesempenio());
    }

    private static void uniforme(Personaje p1, Personaje p2, double probabilidad) {

        Random r = new Random();

        for (int i = 0; i < Genes.CANTIDAD_GENES; i++) {
            if (probabilidad >= (1.0) * r.nextDouble()) {
                if (i != Genes.ALTURA) {
                    cambiar(p1.getGenes()[i], p2.getGenes()[i], Genes.CANTIDAD_CARACTERISTICAS);

                } else {
                    cambiar(p1.getGenes()[i], p2.getGenes()[i], 1);
                }
            }
        }

        p1.setDesempenio(p1.calcularDesempenio());
        p2.setDesempenio(p2.calcularDesempenio());
    }

    private static void cambiar(double[] gen1, double[] gen2, int largoGen) {

        double aux;
        for (int i = 0; i < largoGen; i++) {
            aux = gen1[i];
            gen1[i] = gen2[i];
            gen2[i] = aux;
        }
    }
}