package com.sia.tp3.Mutacion;

import com.sia.tp3.Genes;
import com.sia.tp3.Personaje;

import java.util.ArrayList;
import java.util.Random;

public class MultiGen implements InterfazMutacion {

    private double probabilidadDeMutacion;

    public MultiGen(final double probabilidadDeMutacion) {
        this.probabilidadDeMutacion = probabilidadDeMutacion;
    }

    public double getProbabilidadDeMutacion() {
        return probabilidadDeMutacion;
    }

    @Override
    public void hacer(final ArrayList<Personaje> personajes) {
        Random r = new Random();

        for (final Personaje personaje : personajes) {
            for (int i = 0; i < Genes.CANTIDAD_GENES; i++) {
                if (probabilidadDeMutacion >= (1.0) * r.nextDouble()) {
                    personaje.getGenes()[i] = personajes.get(r.nextInt(personajes.size())).getGenes()[i];
                }
            }
        }
    }
}
