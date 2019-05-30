package com.sia.tp3.reemplazo;

import com.sia.tp3.Personaje;

import java.util.ArrayList;

public class Reemplazo1 implements InterfazReemplazo {

    @Override
    public ArrayList<Personaje> hacer(final ArrayList<Personaje> padres, final ArrayList<Personaje> nuevaGeneracion) {
        return nuevaGeneracion;
    }

    @Override
    public ArrayList<Personaje> seleccionarIndividuosParaReproduccion(final ArrayList<Personaje> personajes) {
        return new ArrayList<>(personajes);
    }
}
