package com.simple.modelos.enemigos;

import android.content.Context;
import android.graphics.Canvas;

import com.simple.global.Estados;
import com.simple.graficos.Sprite;
import com.simple.modelos.ModeloMovimiento;
import com.simple.modelos.controles.Barra;
import com.simple.modelos.controles.BarraEnemigo;
import com.simple.modelos.enemigos.disparos.DisparoEnemigo;
import com.simple.utilidades.Ar;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by UO237040 on 29/09/2015.
 */
public abstract class AbstractEnemigo extends ModeloMovimiento implements Enemigo {

    public static final String BASICO = "Basico";
    public static final String EXPLOTAR = "Explotar";

    protected int estado;
    protected Sprite sprite;
    protected HashMap<String,Sprite> sprites = new HashMap<String,Sprite>();

    protected float aceleracionX = 4;
    protected float aceleracionY = 1f;

    protected float maxAceleracionX;
    protected float minAceleracionX;

    protected int vida = 1;

    protected int cadenciaDisparo = 3000;
    protected long milisegundosDisparo = 0;

    protected BarraEnemigo barraVida;

    public AbstractEnemigo(Context context, double x, double y) {

        super(context, x, y);

    }

    @Override
    public void dibujarEnPantalla(Canvas canvas) {
        if(estado != Estados.INACTIVO) {
            sprite.dibujarSprite(canvas, (int) x, (int) y);
        }
        barraVida.dibujarEnPantalla(canvas);
    }

    public abstract DisparoEnemigo disparar(long milisegundos);

    public void destruir(int damage){

        vida -= damage;
        barraVida.setValorActual(vida);
        if(vida <= 0){
            estado = Estados.EXPLOTANDO;
            sprite = sprites.get(EXPLOTAR);
        }

    }

    public void mover(){

        boolean finalizarSprite = sprite.actualizar(System.currentTimeMillis());

        if(finalizarSprite && sprite == sprites.get(EXPLOTAR)){
            estado = Estados.INACTIVO;
        }

        if (estado == Estados.ACTIVO) {
            if (x + ancho / 2 >= mCanvasAncho ){
                aceleracionX = (float) (minAceleracionX + Math.random()*maxAceleracionX * -1);
            }
            if ( x - ancho / 2 <= 0){
                aceleracionX = (float) (minAceleracionX + Math.random()*maxAceleracionX);
            }
            x += Ar.x(aceleracionX);
            y += Ar.y(aceleracionY);

            barraVida.setX(x);
            barraVida.setY(y);
        }

    }

    public int getEstado() {
        return estado;
    }
}
