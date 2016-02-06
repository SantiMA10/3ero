package com.simple.modelos.items;

import android.content.Context;

import com.simple.GameView;
import com.simple.R;
import com.simple.gestores.CargadorGraficos;
import com.simple.modelos.ModeloMovimiento;
import com.simple.utilidades.Ar;

import java.util.Random;

/**
 * Created by santiagomartin on 10/10/15.
 */
public class ItemPausa extends ModeloMovimiento implements Item {

    protected float aceleracionY = 2;

    public ItemPausa(Context context, double x, double y) {

        super(context, x, y);
        altura = Ar.altura(25);
        ancho = Ar.ancho(25);
        imagen = CargadorGraficos.cargarDrawable(context, R.drawable.boton_pausa);

    }

    @Override
    public void accion(GameView gameView) {
        gameView.tiempoEntreEnemigos = 5000;
    }

    @Override
    public void mover() {
        y += aceleracionY;
    }
}
