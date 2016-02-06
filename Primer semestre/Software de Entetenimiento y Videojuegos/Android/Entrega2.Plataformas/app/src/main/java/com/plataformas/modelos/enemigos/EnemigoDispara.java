package com.plataformas.modelos.enemigos;

import android.content.Context;
import android.util.Log;

import com.plataformas.R;
import com.plataformas.gestores.CargadorGraficos;
import com.plataformas.graficos.Sprite;

/**
 * Created by santiagomartin on 19/11/15.
 */
public class EnemigoDispara extends Enemigo {

    public long tiempoEntreDisparos = 450;
    public long ultimoDisparo;

    public EnemigoDispara(Context context, double x, double y) {
        super(context, x, y, 50, 50);
        dispara = true;
    }

    @Override
    public void inicializar() {

        Sprite caminandoDerecha = new Sprite(
                CargadorGraficos.cargarDrawable(context, R.drawable.enemy_dispara_runright),
                ancho, altura,
                4, 4, true);
        sprites.put(CAMINANDO_DERECHA, caminandoDerecha);

        Sprite caminandoIzquierda = new Sprite(
                CargadorGraficos.cargarDrawable(context, R.drawable.enemy_dispara_run),
                ancho, altura,
                4, 4, true);
        sprites.put(CAMINANDO_IZQUIERDA, caminandoIzquierda);

        Sprite muerteDerecha = new Sprite(
                CargadorGraficos.cargarDrawable(context,
                        R.drawable.enemy_dispara_dieright),
                ancho, altura,
                4, 6, false);
        sprites.put(MUERTE_DERECHA, muerteDerecha);

        Sprite muerteIzquierda = new Sprite(
                CargadorGraficos.cargarDrawable(context, R.drawable.enemy_dispara_die),
                ancho, altura,
                4, 6, false);
        sprites.put(MUERTE_IZQUIERDA, muerteIzquierda);

        sprite = caminandoDerecha;

    }

    public void actualizar (long tiempo) {
        boolean finSprite = sprite.actualizar(tiempo);

        if(!dispara){
            ultimoDisparo += tiempo;
        }
        if(ultimoDisparo > tiempoEntreDisparos){
            dispara = true;
            ultimoDisparo = 0;
        }

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
