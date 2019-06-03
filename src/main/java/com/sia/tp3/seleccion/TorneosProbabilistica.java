package com.sia.tp3.seleccion;

import com.sia.tp3.Personaje;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TorneosProbabilistica implements InterfazSeleccion {

    private final static double PROBABILIDAD = 0.75;
    private int cantidadDePersonajes;
    private boolean usaBoltzmann;

    public TorneosProbabilistica(final int cantidadDePersonajes, final boolean usaBoltzmann) {
        this.cantidadDePersonajes = cantidadDePersonajes;
        this.usaBoltzmann = usaBoltzmann;
    }

    public int getCantidadDePersonajes() {
        return cantidadDePersonajes;
    }

    @Override
    public ArrayList<Personaje> hacer(final ArrayList<Personaje> personajes, final int cantidad,
                                      final int numeroDeGeneracion) {

        ArrayList<Personaje> aux = personajes;

        if (usaBoltzmann) {
            aux = new ArrayList<>();
            for (Personaje personaje : personajes) {
                aux.add(personaje.copy());
            }

            Boltzmann boltzmann = new Boltzmann();
            boltzmann.hacer(aux, numeroDeGeneracion);
        }

        ArrayList<Personaje> personajesSeleccionados = seleccionarPersonajes(aux, cantidadDePersonajes);

        return seleccionarGanadores(personajesSeleccionados, cantidad);
    }


    private ArrayList<Personaje> seleccionarPersonajes(final ArrayList<Personaje> personajes, final int cantidad) {

        int aleatorio;
        ArrayList<Integer> numerosAleatoriosUsados = new ArrayList<>();
        ArrayList<Personaje> personajesSeleccionados = new ArrayList<>();
        int contador = cantidad;

        while (contador != 0) {

            do {
                aleatorio = ThreadLocalRandom.current().nextInt(0, personajes.size());
                if (!numerosAleatoriosUsados.contains(aleatorio)) {
                    numerosAleatoriosUsados.add(aleatorio);
                    break;
                }

            } while (true);

            personajesSeleccionados.add(personajes.get(aleatorio));
            contador--;
        }

        return personajesSeleccionados;
    }

    private ArrayList<Personaje> seleccionarGanadores(final ArrayList<Personaje> personajesSeleccionados,
                                                      final int ciclos) {

        int aleatorio;
        Personaje p1, p2;
        ArrayList<Personaje> ret = new ArrayList<>();
        Random random = new Random();
        int contador = ciclos;

        while (contador != 0) {

            double r = (1.0) * random.nextDouble();
            aleatorio = ThreadLocalRandom.current().nextInt(0, personajesSeleccionados.size());
            p1 = personajesSeleccionados.get(aleatorio);

            do {
                if (aleatorio != ThreadLocalRandom.current().nextInt(0, personajesSeleccionados.size())) {
                    p2 = personajesSeleccionados.get(ThreadLocalRandom.current().nextInt(0,
                            personajesSeleccionados.size()));
                    break;
                }
            } while (true);

            if (r < PROBABILIDAD) {

                if (p1.getDesempenio() > p2.getDesempenio()) {
                    ret.add(p1);
                } else
                    ret.add(p2);
            } else {

                if (p1.getDesempenio() < p2.getDesempenio()) {
                    ret.add(p1);
                } else
                    ret.add(p2);
            }

            contador--;
        }

        return ret;
    }

}
