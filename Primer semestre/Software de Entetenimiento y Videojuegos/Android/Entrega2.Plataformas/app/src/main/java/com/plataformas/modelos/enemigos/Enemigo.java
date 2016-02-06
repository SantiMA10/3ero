package com.plataformas.modelos.enemigos;

import android.content.Context;
import android.graphics.Canvas;

import com.plataformas.graficos.Sprite;
import com.plataformas.modelos.Modelo;
import com.plataformas.modelos.Nivel;

import java.util.HashMap;

/**
 * Created by UO237040 on 20/10/2015.
 */
public abstract class Enemigo extends Modelo{

    public static final int ACTIVO = 1;
    public static final int INACTIVO = 0;
    public static final int ELIMINAR = -1;
    public static final int DERECHA = 1;
    public static final int IZQUIERDA = -1;

    public static final String CAMINANDO_DERECHA = "Caminando_derecha";
    public static final String CAMINANDO_IZQUIERDA = "caminando_izquierda";
    public static final String MUERTE_DERECHA = "muerte_derecha";
    public static final String MUERTE_IZQUIERDA = "muerte_izquierda";

    protected Sprite sprite;
    protected HashMap<String,Sprite> sprites = new HashMap<String,Sprite> ();
    public double velocidadX;
    public double velocidadY;

    public int estado = ACTIVO;
    public int orientacion;

    public boolean volador = false;
    public boolean dispara = false;

    public Enemigo(Context context, double x, double y, int altura, int ancho) {
        super(context, x, y, altura, ancho);
        this.x = x;
        this.y = y - altura/2;
        velocidadX = 1.2;
        velocidadY = 0.0;

        cDerecha = 15;
        cIzquierda = 15;
        cArriba = 25;
        cAbajo = 25;

        inicializar();
    }

    public abstract void inicializar();

    public void girarX(){
        velocidadX = velocidadX*-1;
    }

    public void girarY(){
        velocidadY = velocidadY*-1;
    }

    public void dibujar(Canvas canvas){
        sprite.dibujarSprite(canvas, (int) x - Nivel.scrollEjeX, (int) y);
    }

    public void destruir (){
        estado = INACTIVO;
    }

    public boolean colisiona(Modelo modelo){
        if(estado == ACTIVO){
            return super.colisiona(modelo);
        }
        return false;
    }

    public void actualizar (long tiempo) {
        boolean finSprite = sprite.actualizar(tiempo);
    }
}
