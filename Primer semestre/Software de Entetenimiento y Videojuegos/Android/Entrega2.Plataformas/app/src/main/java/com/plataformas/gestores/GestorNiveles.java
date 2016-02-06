package com.plataformas.gestores;

/**
 * Created by santiagomartin on 27/9/15.
 */
public class GestorNiveles {

    private static GestorNiveles instancia = null;
    private int nivelActual = 0;

    private GestorNiveles() {}

    public static GestorNiveles getInstancia() {
        synchronized (GestorNiveles.class){

            if(instancia == null){
                instancia = new GestorNiveles();
            }
            return instancia;

        }

    }

    public int getNivelActual() {
        return nivelActual;
    }

    public void setNivelActual(int nivel) {
        this.nivelActual = nivel;
    }

}
