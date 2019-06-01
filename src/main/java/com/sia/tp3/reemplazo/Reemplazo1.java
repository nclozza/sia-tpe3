package com.sia.tp3.reemplazo;

import com.sia.tp3.Personaje;
import com.sia.tp3.cruce.InterfazCruce;
import com.sia.tp3.mutacion.InterfazMutacion;
import com.sia.tp3.seleccion.InterfazSeleccion;

import java.util.ArrayList;

public class Reemplazo1 extends Reemplazo implements InterfazReemplazo {

    private double modificadorA;
    private double modificadorB;
    private int k;
    private InterfazMutacion mutacion;

    public Reemplazo1(final InterfazSeleccion seleccion1, final InterfazSeleccion seleccion2,
                      final InterfazSeleccion seleccion3, final InterfazSeleccion seleccion4,
                      final InterfazCruce cruce, final double modificadorA, final double modificadorB, final int k,
                      final InterfazMutacion mutacion) {
        super(seleccion1, seleccion2, seleccion3, seleccion4, cruce);
        this.modificadorA = modificadorA;
        this.modificadorB = modificadorB;
        this.k = k;
        this.mutacion = mutacion;
    }

    @Override
    public ArrayList<Personaje> hacer(final ArrayList<Personaje> personajes) {

        int cantidadSeleccion1 = new Double(k * modificadorA).intValue();
        int cantidadSeleccion2 = k - cantidadSeleccion1;

        ArrayList<Personaje> individuosParaCruzar = seleccionarPadres(personajes, cantidadSeleccion1,
                cantidadSeleccion2);

        ArrayList<Personaje> individuosCruzados = cruzarIndividuos(individuosParaCruzar);
        mutacion.hacer(individuosCruzados);

        recalcularTodosLosDesempenios(individuosCruzados);

        // SOLO PARA DEBUGGEAR
        if (individuosCruzados.size() != k) {
            throw new RuntimeException("1 - ERROR EN REEMPLAZO 1");
        }

        cantidadSeleccion1 = new Double(k * modificadorB).intValue();
        cantidadSeleccion2 = k - cantidadSeleccion1;

        ArrayList<Personaje> ret = new ArrayList<>(seleccionarNuevaGeneracion(individuosCruzados, cantidadSeleccion1,
                cantidadSeleccion2));

        // SOLO PARA DEBUGGEAR
        if (ret.size() != personajes.size()) {
            throw new RuntimeException("2 - ERROR EN REEMPLAZO 1");
        }

        return ret;
    }
}
