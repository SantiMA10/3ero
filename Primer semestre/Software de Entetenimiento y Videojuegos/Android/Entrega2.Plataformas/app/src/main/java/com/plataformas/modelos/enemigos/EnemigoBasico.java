package com.plataformas.modelos.enemigos;

import android.content.Context;
import android.graphics.Canvas;

import com.plataformas.R;
import com.plataformas.gestores.CargadorGraficos;
import com.plataformas.graficos.Sprite;
import com.plataformas.modelos.Modelo;
import com.plataformas.modelos.Nivel;

import java.util.HashMap;

/**
 * Created by UO237040 on 13/10/2015.
 */
public class EnemigoBasico extends Enemigo {

    public EnemigoBasico(Context context, double xInicial, double yInicial) {
        super(context, xInicial, yInicial, 50, 50);
        velocidadX = 1.2;
        velocidadY = 0.0;

    }
    public void inicializar (){

        Sprite caminandoDerecha = new Sprite(
                CargadorGraficos.cargarDrawable(context, R.drawable.enemyrunright),
                ancho, altura,
                4, 4, true);
        sprites.put(CAMINANDO_DERECHA, caminandoDerecha);

        Sprite caminandoIzquierda = new Sprite(
                CargadorGraficos.cargarDrawable(context, R.drawable.enemyrun),
                ancho, altura,
                4, 4, true);
        sprites.put(CAMINANDO_IZQUIERDA, caminandoIzquierda);

        Sprite muerteDerecha = new Sprite(
                CargadorGraficos.cargarDrawable(context,
                        R.drawable.enemydieright),
                ancho, altura,
                4, 8, false);
        sprites.put(MUERTE_DERECHA, muerteDerecha);

        Sprite muerteIzquierda = new Sprite(
                CargadorGraficos.cargarDrawable(context, R.drawable.enemydie),
                ancho, altura,
                4, 8, false);
        sprites.put(MUERTE_IZQUIERDA, muerteIzquierda);

        sprite = caminandoDerecha;
    }
    public void actualizar (long tiempo) {
        boolean finSprite = sprite.actualizar(tiempo);

        if (estado == INACTIVO){
            if (orientacion == DERECHA)
                sprite = sprites.get(MUERTE_DERECHA);
            else
                sprite = sprites.get(MUERTE_IZQUIERDA);
        } else {
            if (velocidadX > 0) {
                orientacion = DERECHA;
                sprite = sprites.get(CAMINANDO_DERECHA);
            }
            if (velocidadX < 0) {
                orientacion = IZQUIERDA;
                sprite = sprites.get(CAMINANDO_IZQUIERDA);
            }
        }
        if ( estado == INACTIVO && finSprite == true){
            estado = ELIMINAR;
        }

    }

}