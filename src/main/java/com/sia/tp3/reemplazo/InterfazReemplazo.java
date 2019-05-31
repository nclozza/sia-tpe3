package com.sia.tp3.reemplazo;

import com.sia.tp3.Personaje;

import java.util.ArrayList;

public interface InterfazReemplazo {

    public ArrayList<Personaje> hacer(final ArrayList<Personaje> padres, final ArrayList<Personaje> nuevaGeneracion);

    public ArrayList<Personaje> seleccionarIndividuosParaReproduccion(final ArrayList<Personaje> personajes);

    public int getCantidad();
}
