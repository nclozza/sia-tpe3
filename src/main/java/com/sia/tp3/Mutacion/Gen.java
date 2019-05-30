package com.sia.tp3.Mutacion;

import com.sia.tp3.Genes;
import com.sia.tp3.Personaje;

import java.util.ArrayList;
import java.util.Random;

public class Gen implements InterfazMutacion {

    private double probabilidadDeMutacion;

    public Gen(final double probabilidadDeMutacion) {
        this.probabilidadDeMutacion = probabilidadDeMutacion;
    }

    public double getProbabilidadDeMutacion() {
        return probabilidadDeMutacion;
    }

    @Override
    public void hacer(final ArrayList<Personaje> personajes) {
        Random r = new Random();

        int genAMutar;
        for (final Personaje personaje : personajes) {
            if (probabilidadDeMutacion >= (1.0) * r.nextDouble()) {
                genAMutar = r.nextInt(Genes.CANTIDAD_GENES);
                personaje.getGenes()[genAMutar] = personajes.get(r.nextInt(personajes.size())).getGenes()[genAMutar];
            }
        }
    }
}
