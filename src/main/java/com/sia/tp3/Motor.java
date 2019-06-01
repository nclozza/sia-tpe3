package com.sia.tp3;

import com.sia.tp3.corte.InterfazCorte;
import com.sia.tp3.reemplazo.InterfazReemplazo;

import java.util.ArrayList;

public class Motor {

    private InterfazReemplazo reemplazo1;
    private InterfazReemplazo reemplazo2;
    private double modificadorB;
    private InterfazCorte corte;

    public Motor(final InterfazReemplazo reemplazo1, final InterfazReemplazo reemplazo2, final double modificadorB,
                 final InterfazCorte corte) {
        this.reemplazo1 = reemplazo1;
        this.reemplazo2 = reemplazo2;
        this.modificadorB = modificadorB;
        this.corte = corte;
    }

    public void correr(Poblacion poblacion) {

        while (!corte.evaluar(poblacion)) {

            poblacion.setPersonajes(nuevaGeneracion(poblacion));

//            System.out.println("GENERACION N: " + poblacion.getNumeroDeGeneracion());
//            for (int i = 0; i < poblacion.getPersonajes().size(); i++) {
//                System.out.println("Personaje nro: " + i);
//                System.out.println("Desempenio: " + poblacion.getPersonajes().get(i).getDesempenio());
//                poblacion.getPersonajes().get(i).imprimirGenes();
//            }
        }
    }

    private ArrayList<Personaje> nuevaGeneracion(Poblacion poblacion) {

        ArrayList<Personaje> nuevaGeneracionReemplazo1 = reemplazo1.hacer(poblacion.getPersonajes());
        ArrayList<Personaje> nuevaGeneracionReemplazo2 = reemplazo2.hacer(poblacion.getPersonajes());

        int cantidadNuevaGeneracionReemplazo1 = new Double(poblacion.getPersonajes().size() * modificadorB).intValue();
        int cantidadNuevaGeneracionReemplazo2 = poblacion.getPersonajes().size() - cantidadNuevaGeneracionReemplazo1;

        ArrayList<Personaje> ret = new ArrayList<>();

        for (int i = 0; i < cantidadNuevaGeneracionReemplazo1; i++) {
            ret.add(nuevaGeneracionReemplazo1.get(i));
        }

        for (int i = 0; i < cantidadNuevaGeneracionReemplazo2; i++) {
            ret.add(nuevaGeneracionReemplazo2.get(i));
        }

        // SOLO PARA DEBUGGEAR
        if (ret.size() != poblacion.getPersonajes().size()) {
            throw new RuntimeException("1 - ERROR EN MOTOR");
        }

        poblacion.aumentarGeneracion();
        return ret;
    }
}
