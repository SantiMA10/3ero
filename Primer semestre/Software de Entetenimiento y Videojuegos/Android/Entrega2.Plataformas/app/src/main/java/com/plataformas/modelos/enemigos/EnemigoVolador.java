package com.plataformas.modelos.enemigos;

import android.content.Context;

import com.plataformas.R;
import com.plataformas.gestores.CargadorGraficos;
import com.plataformas.graficos.Sprite;

/**
 * Created by UO237040 on 20/10/2015.
 */
public class EnemigoVolador extends Enemigo{

    public EnemigoVolador(Context context, double xInicial, double yInicial) {
        super(context, xInicial, yInicial, 50, 50);
        velocidadX = 0.5;
        velocidadY = 0.5;

        cDerecha = 15;
        cIzquierda = 15;
        cArriba = 15;
        cAbajo = 15;

        volador = true;
    }

    @Override
    public void inicializar() {

        Sprite caminando = new Sprite(
                CargadorGraficos.cargarDrawable(context, R.drawable.enemigo_volador),
                ancho, altura,
                1, 2, true);
        sprites.put(CAMINANDO_DERECHA, caminando);

        Sprite muriendo = new Sprite(
                CargadorGraficos.cargarDrawable(context, R.drawable.animacion_nave_explota),
                ancho, altura,
                1, 3, false);
        sprites.put(MUERTE_DERECHA, muriendo);

        sprite = caminando;
    }

    @Override
    public void actualizar (long tiempo) {
        boolean finSprite = sprite.actualizar(tiempo);

        if (estado == INACTIVO) {
            sprite = sprites.get(MUERTE_DERECHA);
        } else {
            sprite = sprites.get(CAMINANDO_DERECHA);
        }
        if (estado == INACTIVO && finSprite == true) {
            estado = ELIMINAR;
        }
    }
}
