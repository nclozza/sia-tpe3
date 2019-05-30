package com.sia.tp3.cruce;

import com.sia.tp3.Genes;
import com.sia.tp3.Personaje;

public class Anular implements InterfazCruce{

    private int locus1;
    private int segmento;

    public Anular(int locus1, int segmento) {
        this.locus1 = locus1;
        this.segmento = segmento;
    }

    public int getLocus1() {
        return locus1;
    }

    public int getSegmento() {
        return segmento;
    }

    @Override
    public void hacer(Personaje personaje1, Personaje personaje2) {

        int contador = segmento;

        for (int i = locus1; 0 < contador; i++) {
            if (i%Genes.CANTIDAD_GENES != Genes.ALTURA) {
                cambiar(personaje1.getGenes()[i%Genes.CANTIDAD_GENES], personaje2.getGenes()[i%Genes.CANTIDAD_GENES], Genes.CANTIDAD_CARACTERISTICAS);

            } else {
                cambiar(personaje1.getGenes()[i%Genes.CANTIDAD_GENES], personaje2.getGenes()[i%Genes.CANTIDAD_GENES], 1);
            }
            contador --;
        }
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
