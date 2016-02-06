package com.simple.modelos.enemigos;

import android.content.Context;

import com.simple.R;
import com.simple.gestores.CargadorGraficos;
import com.simple.global.Estados;
import com.simple.graficos.Sprite;
import com.simple.modelos.controles.BarraEnemigo;
import com.simple.modelos.enemigos.disparos.DisparoEnemigo;
import com.simple.modelos.enemigos.disparos.DisparoEnemigoHelicoptero;
import com.simple.utilidades.Ar;

/**
 * Created by UO237040 on 29/09/2015.
 */
public class EnemigoHelicoptero extends AbstractEnemigo {

    public EnemigoHelicoptero(Context context, double x, double y) {

        super(context, x, y);
        altura = Ar.altura(50);
        ancho = Ar.ancho(50);
        estado = Estados.ACTIVO;

        Sprite basico = new Sprite(CargadorGraficos.cargarBitmap(context, R.drawable.animacion_enemigo_h), ancho,
                altura, 4, 4, true);
        sprites.put(BASICO, basico);

        Sprite explotar = new Sprite(CargadorGraficos.cargarBitmap(context, R.drawable.animacion_enemigo_h_explota), ancho,
                altura, 6, 6, false);
        sprites.put(EXPLOTAR, explotar);

        sprite = basico;

        aceleracionX = 8;
        aceleracionY = 0.5f;

        maxAceleracionX = 5.5f;
        minAceleracionX = 1.5f;

        cadenciaDisparo = 1500;
        milisegundosDisparo = 100;

        vida = 2;

        barraVida = new BarraEnemigo(context, x, y, vida, vida, altura, ancho);

    }

    public DisparoEnemigo disparar(long milisegundos) {
        if (milisegundos - milisegundosDisparo > cadenciaDisparo
                + Math.random()* cadenciaDisparo) {

            milisegundosDisparo = milisegundos;
            return new DisparoEnemigoHelicoptero(context, x, y);
        }
        return null;
    }

}
