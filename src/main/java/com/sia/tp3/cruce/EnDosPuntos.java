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

        double[][] aux = new double[Genes.CANTIDAD_GENES][Genes.CANTIDAD_CARACTERISTICAS];
        for (int i = locus1; i < locus2; i++) {
            aux[i] = personaje1.getGenes()[i];
            personaje1.getGenes()[i] = personaje2.getGenes()[i];
            personaje2.getGenes()[i] = aux[i];
        }

        personaje1.recalcularDesempenio();
        personaje2.recalcularDesempenio();
    }
}
