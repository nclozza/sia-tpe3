package com.sia.tp3;

import java.util.Random;

public class Cruce {

    public void enUnPunto(Personaje p1, Personaje p2, int punto) {
        cambiar(p1, p2, punto,  Genes.CANTIDAD_GENES);
    }

    public void enDosPuntos(Personaje p1, Personaje p2, int primero, int ultimo) {
        cambiar(p1, p2, primero,  ultimo);
    }

    private void cambiar(Personaje p1, Personaje p2, int primero, int ultimo) {

        double aux;

        for (int i = primero; i < ultimo; i++) {
            if (i != Genes.ALTURA) {
                for (int j = 0; j < Genes.CANTIDAD_CARACTERISTICAS; j++) {
                    aux = p1.getGenes()[i][j];
                    p1.getGenes()[i][j] = p2.getGenes()[i][j];
                    p2.getGenes()[i][j] = aux;
                }

            } else {
                aux = p1.getGenes()[i][0];
                p1.getGenes()[i][0] = p2.getGenes()[i][0];
                p2.getGenes()[i][0] = aux;
            }
        }
    }

    public void uniforme(Personaje p1, Personaje p2, double probabilidad) {

        Random r = new Random();

        for (int i = 0; i < Genes.CANTIDAD_GENES; i++) {
            if (probabilidad >= (1.0) * r.nextDouble()) {
                cambiar(p1, p2, i, i + 1);
            }
        }
    }
}