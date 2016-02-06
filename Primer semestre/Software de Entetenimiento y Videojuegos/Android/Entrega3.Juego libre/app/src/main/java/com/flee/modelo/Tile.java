package com.flee.modelo;

import android.graphics.drawable.Drawable;

import com.flee.GameView;

public class Tile {

    public static final int PASABLE = 0;
    public static final int SOLIDO = 1;
    public static final int META = 2;

    public int tipoDeColision; // PASABLE o SOLIDO
    public static int ancho = GameView.pantallaAncho/7;
    public static int altura = ancho;

    public Drawable imagen;

    public Tile(Drawable imagen, int tipoDeColision){

        this.imagen = imagen ;
        this.tipoDeColision = tipoDeColision;

    }

}
