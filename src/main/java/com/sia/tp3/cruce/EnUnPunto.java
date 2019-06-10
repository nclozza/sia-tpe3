package com.sia.tp3.cruce;import com.sia.tp3.Genes;import com.sia.tp3.Personaje;import com.sia.tp3.mutacion.Gen;import java.util.concurrent.ThreadLocalRandom;public class EnUnPunto implements InterfazCruce {    public EnUnPunto() {    }    @Override    public void hacer(final Personaje personaje1, final Personaje personaje2) {        int locus1 = generarLocus1();        double[][] aux = new double[Genes.CANTIDAD_GENES][Genes.CANTIDAD_CARACTERISTICAS];        for (int i = locus1; i < Genes.CANTIDAD_GENES; i++) {            aux[i] = personaje1.getGenes()[i];            personaje1.getGenes()[i] = personaje2.getGenes()[i];            personaje2.getGenes()[i] = aux[i];        }        personaje1.recalcularDesempenio();        personaje2.recalcularDesempenio();    }    private int generarLocus1(){        return ThreadLocalRandom.current().nextInt(0, Genes.CANTIDAD_GENES);    }}