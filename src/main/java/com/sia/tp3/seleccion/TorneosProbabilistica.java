package com.sia.tp3.seleccion;

import com.sia.tp3.Personaje;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TorneosProbabilistica implements InterfazSeleccion{

    private final static Double PROBABILIDAD = 0.75;

    @Override
    public ArrayList<Personaje> hacer(ArrayList<Personaje> personajes, int cantidad) {

        //TODO: Verificar si estos parametros van en el .config
        int ciclos = 3;
        cantidad = 6;

        //TODO: PREGUNTAR SI SE USAN TODOS LOS PERSONAJES O SE AGARRA UNA CIERTA CANTIDAD COMO EN LA DETERMINISTICA
        ArrayList<Personaje> personajesSeleccionados = seleccionarPersonajes(personajes, cantidad);
        ArrayList<Personaje> ret = seleccionarGanadores(personajesSeleccionados, ciclos);

        return ret;
    }


    private ArrayList<Personaje> seleccionarPersonajes(ArrayList<Personaje>personajes, int cantidad){

        Integer aleatorio;
        ArrayList<Integer> numerosAleatoriosUsados = new ArrayList<>();
        ArrayList<Personaje> personajesSeleccionados = new ArrayList<>();

        while(cantidad != 0){

            do{
                aleatorio = ThreadLocalRandom.current().nextInt(0, personajes.size());
                if(!numerosAleatoriosUsados.contains(aleatorio)){
                    numerosAleatoriosUsados.add(aleatorio);
                    break;
                }

            } while (true);

            personajesSeleccionados.add(personajes.get(aleatorio));
            cantidad --;
        }

        return personajesSeleccionados;
    }

    private ArrayList<Personaje> seleccionarGanadores(ArrayList<Personaje> personajesSeleccionados, int ciclos){

        Integer aleatorio;
        Personaje p1, p2;
        ArrayList<Personaje> ret = new ArrayList<>();
        Random random = new Random();

        while(ciclos != 0){

            Double r = (1.0) * random.nextDouble();
            aleatorio = ThreadLocalRandom.current().nextInt(0, personajesSeleccionados.size());
            p1 = personajesSeleccionados.get(aleatorio);

            do{
                if(aleatorio != ThreadLocalRandom.current().nextInt(0, personajesSeleccionados.size())){
                    p2 = personajesSeleccionados.get(ThreadLocalRandom.current().nextInt(0, personajesSeleccionados.size()));
                    break;
                }
            } while (true);

            if (r < PROBABILIDAD){

                if (p1.getDesempenio() >  p2.getDesempenio()){
                    ret.add(p1);
                } else
                    ret.add(p2);
            } else {

                if (p1.getDesempenio() <  p2.getDesempenio()){
                    ret.add(p1);
                } else
                    ret.add(p2);
            }

            ciclos--;
        }

        return ret;
    }

}
