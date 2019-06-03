package com.sia.tp3.corte;

import com.sia.tp3.Personaje;
import com.sia.tp3.Poblacion;

import java.util.HashSet;

public class Estructura implements InterfazCorte {

    private int cantidadGeneracionesAVerificar;
    private double porcentaje;

    public Estructura(final int cantidadGeneracionesAVerificar, final double porcentaje) {
        this.cantidadGeneracionesAVerificar = cantidadGeneracionesAVerificar;
        this.porcentaje = porcentaje;
    }

    @Override
    public boolean evaluar(final Poblacion poblacion) {
        if (poblacion.getNumeroDeGeneracion() < cantidadGeneracionesAVerificar) {
            return false;
        }

        int contador;

        HashSet<String> aux;
        for (int i = 0; i < cantidadGeneracionesAVerificar; i++) {
            aux = poblacion.getArrayHashsPersonajes().get(poblacion.getNumeroDeGeneracion() - 1 - i);

            contador = 0;
            for (Personaje personaje : poblacion.getPersonajes()) {
                String hashPersonaje = personaje.toHash();
                if (aux.contains(hashPersonaje)) {
                    contador++;
                }
            }

            if (contador / poblacion.getPersonajes().size() < porcentaje) {
                return false;
            }
        }

        return true;
    }
}
