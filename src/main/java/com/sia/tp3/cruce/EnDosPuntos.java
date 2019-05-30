package com.sia.tp3.cruce;

import com.sia.tp3.Genes;
import com.sia.tp3.Personaje;

public class EnDosPuntos implements InterfazCruce {

    private int primero;
    private int ultimo;

    public EnDosPuntos(final int primero, final int ultimo) {
        this.primero = primero;
        this.ultimo = ultimo;
    }

    public int getPrimero() {
        return primero;
    }

    public int getUltimo() {
        return ultimo;
    }

    @Override
    public void hacer(final Personaje personaje1, final Personaje personaje2) {
        for (int i = primero; i < ultimo; i++) {
            if (i != Genes.ALTURA) {
                cambiar(personaje1.getGenes()[i], personaje2.getGenes()[i], Genes.CANTIDAD_CARACTERISTICAS);

            } else {
                cambiar(personaje1.getGenes()[i], personaje2.getGenes()[i], 1);
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
