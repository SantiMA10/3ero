package com.flee.modelo.item;

import android.content.Context;

import com.flee.gestores.CargadorGraficos;
import com.flee.modelo.Nivel;
import com.flee.R;


public class Moneda extends Item {

    public Moneda(Context context, double x, double y) {
        super(context, x, y);

        this.imagen = CargadorGraficos.cargarDrawable(context, R.drawable.coin_gold);
    }

    @Override
    public void aplicar(Nivel nivel) {
        nivel.marcador.puntos += 10;
    }

}
