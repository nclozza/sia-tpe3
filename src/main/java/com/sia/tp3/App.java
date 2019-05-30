package com.sia.tp3;

public class App {
    public static void main(String[] args) {
        Configuracion configuracion = new Configuracion();
        Multiplicador multiplicador = new Multiplicador(configuracion.getFuerza(), configuracion.getAgilidad(),
                configuracion.getPericia(), configuracion.getResistencia(), configuracion.getVida());
        Poblacion poblacion = new Poblacion(configuracion.getPersonaje(), multiplicador);

        System.out.println("TERMINE DE CARGAR");

//        for (int i = 0; i < poblacion.getPersonajes().size(); i++) {
//            System.out.println("Personaje nro: " + i);
//            System.out.println("Desempenio: " + poblacion.getPersonajes().get(i).getDesempenio());
//            poblacion.getPersonajes().get(i).imprimirGenes();
//        }

        int aux = 0;
        Reemplazo reemplazo = new Reemplazo(1, poblacion.getPersonajes().size());

        while (aux++ < configuracion.getGeneraciones()) {
            poblacion.setPersonajes(reemplazo.obtenerNuevaGeneracion(poblacion.getPersonajes()));
        }

//        for (int i = 0; i < poblacion.getPersonajes().size(); i++) {
//            System.out.println("Personaje nro: " + i);
//            System.out.println("Desempenio: " + poblacion.getPersonajes().get(i).getDesempenio());
//            poblacion.getPersonajes().get(i).imprimirGenes();
//        }


    }
}
