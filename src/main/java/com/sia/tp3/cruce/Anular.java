package com.sia.tp3.cruce;

import com.sia.tp3.Genes;
import com.sia.tp3.Personaje;

public class Anular implements InterfazCruce {

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
        double[][] aux = new double[Genes.CANTIDAD_GENES][Genes.CANTIDAD_CARACTERISTICAS];
        for (int i = locus1; 0 < contador; i++) {
            aux[i % Genes.CANTIDAD_GENES] = personaje1.getGenes()[i % Genes.CANTIDAD_GENES];
            personaje1.getGenes()[i % Genes.CANTIDAD_GENES] = personaje2.getGenes()[i % Genes.CANTIDAD_GENES];
            personaje2.getGenes()[i % Genes.CANTIDAD_GENES] = aux[i % Genes.CANTIDAD_GENES];
            contador--;
        }

        personaje1.recalcularDesempenio();
        personaje2.recalcularDesempenio();
    }
}
