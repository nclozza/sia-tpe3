package com.sia.tp3;

public class App 
{
    public static void main( String[] args )
    {
        Configuracion configuracion = new Configuracion();
        Multiplicador multiplicador = new Multiplicador(configuracion.getFuerza(), configuracion.getAgilidad(), configuracion.getPericia(), configuracion.getResistencia(), configuracion.getVida());
        Poblacion poblacion = new Poblacion(configuracion.getPersonaje(), multiplicador);

        poblacion.getPersonajes().get(0).imprimirGenes();
        poblacion.getPersonajes().get(1).imprimirGenes();
        System.out.println("ASD");

        Cruce cruce = new Cruce();
        cruce.enDosPuntos(poblacion.getPersonajes().get(0), poblacion.getPersonajes().get(1), 0, 2);

        poblacion.getPersonajes().get(0).imprimirGenes();
        poblacion.getPersonajes().get(1).imprimirGenes();

        System.out.println("ASD");

    }
}
