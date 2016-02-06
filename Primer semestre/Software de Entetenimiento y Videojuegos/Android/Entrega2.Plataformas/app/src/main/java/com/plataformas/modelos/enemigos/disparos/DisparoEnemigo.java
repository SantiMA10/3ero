package com.plataformas.modelos.enemigos.disparos;

import android.content.Context;
import android.graphics.Canvas;

import com.plataformas.R;
import com.plataformas.gestores.CargadorGraficos;
import com.plataformas.graficos.Sprite;
import com.plataformas.modelos.Jugador;
import com.plataformas.modelos.Modelo;
import com.plataformas.modelos.Nivel;

/**
 * Created by UO237040 on 13/10/2015.
 */
public class DisparoEnemigo extends Modelo {

    private Sprite sprite;
    public double velocidadX = 10;

    public DisparoEnemigo(Context context, double xInicial, double yInicial, int orientacion) {
        super(context, xInicial, yInicial, 70, 70);

        // Si disparan para la izquierda invertir velocidad
        if (orientacion == Jugador.IZQUIERDA)
            velocidadX = velocidadX*-1;

        cDerecha = 10;
        cIzquierda = 10;
        cArriba = 10;
        cAbajo = 10;

        inicializar();
    }
    public void inicializar (){
        sprite= new Sprite(
        CargadorGraficos.cargarDrawable(context,
                R.drawable.animacion_disparo1),
                ancho, altura,
                5, 4, true);
    }
    public void actualizar (long tiempo) {
        sprite.actualizar(tiempo);
    }
    public void dibujar(Canvas canvas){
        sprite.dibujarSprite(canvas, (int) x - Nivel.scrollEjeX, (int) y);
    }
}