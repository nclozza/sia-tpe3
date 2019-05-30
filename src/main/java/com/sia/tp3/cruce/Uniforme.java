package com.sia.tp3.cruce;

import com.sia.tp3.Genes;
import com.sia.tp3.Personaje;

import java.util.Random;

public class Uniforme implements InterfazCruce {

    private double probabilidad;

    public Uniforme(final double probabilidad) {
        this.probabilidad = probabilidad;
    }

    public double getProbabilidad() {
        return probabilidad;
    }

    @Override
    public void hacer(final Personaje personaje1, final Personaje personaje2) {
        Random r = new Random();

        for (int i = 0; i < Genes.CANTIDAD_GENES; i++) {
            if (probabilidad >= (1.0) * r.nextDouble()) {
                if (i != Genes.ALTURA) {
                    cambiar(personaje1.getGenes()[i], personaje2.getGenes()[i], Genes.CANTIDAD_CARACTERISTICAS);

                } else {
                    cambiar(personaje1.getGenes()[i], personaje2.getGenes()[i], 1);
                }
            }
        }

        personaje1.recalcularDesempenio();
        personaje2.recalcularDesempenio();
    }

    private static void cambiar(final double[] gen1, final double[] gen2, final int largoGen) {

        double aux;
        for (int i = 0; i < largoGen; i++) {
            aux = gen1[i];
            gen1[i] = gen2[i];
            gen2[i] = aux;
        }
    }
}
