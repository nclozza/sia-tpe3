package com.sia.tp3.mutacion;

import com.sia.tp3.Personaje;

import java.util.ArrayList;
import java.util.Random;

public class Gen implements InterfazMutacion {

    private InterfazProbabilidad probabilidadDeMutacion;
    private int genAMutar;

    public Gen(final InterfazProbabilidad probabilidadDeMutacion, final int genAMutar) {
        this.probabilidadDeMutacion = probabilidadDeMutacion;
        this.genAMutar = genAMutar;
    }

    public int getGenAMutar() {
        return genAMutar;
    }

    @Override
    public void hacer(final ArrayList<Personaje> personajes, final int numeroDeGeneracion) {
        Random r = new Random();

        for (final Personaje personaje : personajes) {
            if (probabilidadDeMutacion.obtenerProbabilidad(numeroDeGeneracion) >= (1.0) * r.nextDouble()) {
                personaje.getGenes()[genAMutar] = personajes.get(r.nextInt(personajes.size())).getGenes()[genAMutar];
            }
        }
    }
}