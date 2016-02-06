package com.simple.modelos.items;

import android.content.Context;
import android.util.Log;

import com.simple.GameView;
import com.simple.R;
import com.simple.gestores.CargadorGraficos;
import com.simple.modelos.Modelo;
import com.simple.utilidades.Ar;

/**
 * Created by santiagomartin on 10/10/15.
 */
public abstract class ItemAbstractMoneda extends Modelo implements Item {

    protected float aceleracionY = 2;
    public static int monedas = 0;
    protected int valor = 5;

    public ItemAbstractMoneda(Context context, double x, double y) {

        super(context, x, y);

    }

    @Override
    public void mover() {
        y += aceleracionY;
    }

    public void accion(GameView gameView){

        gameView.getMarcador().setPuntos(gameView.getMarcador().getPuntos() + valor);
        monedas += valor/5;
        if(monedas % 15 == 0){
            gameView.getBarraVidas().setValorActual(gameView.getBarraVidas().getValorActual() + valor/5);
        }

    }

}
