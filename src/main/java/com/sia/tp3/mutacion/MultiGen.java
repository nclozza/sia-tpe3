package com.sia.tp3.mutacion;

import com.sia.tp3.Genes;
import com.sia.tp3.Personaje;

import java.util.ArrayList;
import java.util.Random;

public class MultiGen implements InterfazMutacion {

    private InterfazProbabilidad probabilidadDeMutacion;

    public MultiGen(final InterfazProbabilidad probabilidadDeMutacion) {
        this.probabilidadDeMutacion = probabilidadDeMutacion;
    }

    @Override
    public void hacer(final ArrayList<Personaje> personajes, final int numeroDeGeneracion) {
        Random r = new Random();

        for (final Personaje personaje : personajes) {
            for (int i = 0; i < Genes.CANTIDAD_GENES; i++) {
                if (probabilidadDeMutacion.obtenerProbabilidad(numeroDeGeneracion) >= (1.0) * r.nextDouble()) {
                    personaje.getGenes()[i] = personajes.get(r.nextInt(personajes.size())).getGenes()[i];
                }
            }
        }
    }
}
