package com.sia.tp3.cruce;

import com.sia.tp3.Genes;
import com.sia.tp3.Personaje;

public class EnDosPuntos implements InterfazCruce {

    private int locus1;
    private int locus2;

    public EnDosPuntos(final int locus1, final int locus2) {
        this.locus1 = locus1;
        this.locus2 = locus2;
    }

    public int getLocus1() {
        return locus1;
    }

    public int getLocus2() {
        return locus2;
    }

    @Override
    public void hacer(final Personaje personaje1, final Personaje personaje2) {
        System.out.println();
        for (int i = locus1; i < locus2; i++) {
            if (i != Genes.ALTURA) {
                cambiar(personaje1.getGenes()[i], personaje2.getGenes()[i], Genes.CANTIDAD_CARACTERISTICAS);

            } else {
                cambiar(personaje1.getGenes()[i], personaje2.getGenes()[i], 1);
            }

            System.out.println();
        }
        System.out.println();
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
