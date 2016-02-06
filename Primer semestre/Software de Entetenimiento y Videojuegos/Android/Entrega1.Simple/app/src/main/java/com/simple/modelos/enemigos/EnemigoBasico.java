package com.simple.modelos.enemigos;

import android.content.Context;

import com.simple.R;
import com.simple.gestores.CargadorGraficos;
import com.simple.global.Estados;
import com.simple.graficos.Sprite;
import com.simple.modelos.controles.BarraEnemigo;
import com.simple.modelos.enemigos.disparos.DisparoEnemigo;
import com.simple.modelos.enemigos.disparos.DisparoEnemigoBasico;
import com.simple.utilidades.Ar;

public class EnemigoBasico extends AbstractEnemigo {

    public EnemigoBasico(Context context, double x, double y) {
        super(context, x, y);
        altura = Ar.altura(50);
        ancho = Ar.ancho(50);
        estado = Estados.ACTIVO;

        Sprite basico = new Sprite(CargadorGraficos.cargarBitmap(context, R.drawable.animacion_enemigo), ancho,
                altura, 4, 4, true);
        sprites.put(BASICO, basico);

        Sprite explotar = new Sprite(CargadorGraficos.cargarBitmap(context, R.drawable.animacion_enemigo_explotar), ancho,
                altura, 6, 6, false);
        sprites.put(EXPLOTAR, explotar);

        sprite = basico;
        barraVida = new BarraEnemigo(context, x, y, vida, vida, altura, ancho);

        maxAceleracionX = 2.5f;
        minAceleracionX = 0.5f;

        aceleracionY = 1f;
    }

    public DisparoEnemigo disparar(long milisegundos) {
        if (milisegundos - milisegundosDisparo > cadenciaDisparo
                + Math.random()* cadenciaDisparo) {

            milisegundosDisparo = milisegundos;
            return new DisparoEnemigoBasico(context, x, y);
        }
        return null;
    }




}
