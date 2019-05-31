package com.sia.tp3.reemplazo;

import com.sia.tp3.Personaje;

import java.util.ArrayList;

public interface InterfazReemplazo {

    public ArrayList<Personaje> hacer(final ArrayList<Personaje> padres, final ArrayList<Personaje> nuevaGeneracion);

    public int getCantidad();
}
