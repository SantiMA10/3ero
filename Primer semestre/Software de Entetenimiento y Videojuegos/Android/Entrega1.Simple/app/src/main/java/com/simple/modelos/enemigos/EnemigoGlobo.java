package com.simple.modelos.enemigos;

import android.content.Context;

import com.simple.R;
import com.simple.gestores.CargadorGraficos;
import com.simple.global.Estados;
import com.simple.graficos.Sprite;
import com.simple.modelos.controles.BarraEnemigo;
import com.simple.modelos.enemigos.disparos.DisparoEnemigo;
import com.simple.modelos.enemigos.disparos.DisparoEnemigoGlobo;
import com.simple.utilidades.Ar;

/**
 * Created by UO237040 on 29/09/2015.
 */
public class EnemigoGlobo extends AbstractEnemigo {

    public EnemigoGlobo(Context context, double x, double y) {
        super(context, x, y);
        altura = Ar.altura(75);
        ancho = Ar.ancho(50);
        estado = Estados.ACTIVO;

        Sprite basico = new Sprite(CargadorGraficos.cargarBitmap(context, R.drawable.animacion_jefe), ancho,
                altura, 4, 4, true);
        sprites.put(BASICO, basico);

        Sprite explotar = new Sprite(CargadorGraficos.cargarBitmap(context, R.drawable.animacion_jefe_explota), ancho,
                altura, 6, 6, false);
        sprites.put(EXPLOTAR, explotar);

        sprite = basico;

        aceleracionX = 2;
        aceleracionY = 0.25f;

        maxAceleracionX = 1.5f;
        minAceleracionX = 0.25f;

        cadenciaDisparo = 1000;
        milisegundosDisparo = 400;
        vida = 3;
        barraVida = new BarraEnemigo(context, x, y, vida, vida, altura, ancho);

    }

    @Override
    public DisparoEnemigo disparar(long milisegundos) {
        if (milisegundos - milisegundosDisparo > cadenciaDisparo
                + Math.random()* cadenciaDisparo) {

            milisegundosDisparo = milisegundos;
            return new DisparoEnemigoGlobo(context, x, y);
        }
        return null;
    }
}
