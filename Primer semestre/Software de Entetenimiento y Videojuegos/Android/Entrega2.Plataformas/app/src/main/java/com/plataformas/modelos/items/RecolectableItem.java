package com.plataformas.modelos.items;

import android.content.Context;
import android.graphics.Canvas;

import com.plataformas.GameView;
import com.plataformas.R;
import com.plataformas.gestores.CargadorGraficos;
import com.plataformas.graficos.Sprite;
import com.plataformas.modelos.Nivel;

/**
 * Created by UO237040 on 06/10/2015.
 */
public class RecolectableItem extends Item {

    public RecolectableItem(Context context, double x, double y) {
        super(context, x , y,32, 32);

        this.x = x;
        this.y = y - altura/2;
        sprite = new Sprite(
                CargadorGraficos.cargarDrawable(context, R.drawable.gem),
                ancho, altura,
                4, 8, true);
    }

    @Override
    public void accion(Nivel nivel) {
        if(!usado){
            nivel.marcador.addPuntos(10);
            usado = true;
        }
    }
}
