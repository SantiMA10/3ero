package com.simple.modelos.items.powerUps;

import android.content.Context;

import com.simple.GameView;
import com.simple.R;
import com.simple.gestores.CargadorGraficos;
import com.simple.modelos.Modelo;
import com.simple.modelos.items.Item;
import com.simple.modelos.naves.AbstractNave;
import com.simple.modelos.naves.Nave;
import com.simple.utilidades.Ar;

/**
 * Created by UO237040 on 29/09/2015.
 */
public class PowerUpVida extends Modelo implements PowerUp {

    protected float aceleracionY = 2;

    public PowerUpVida(Context context, double x, double y) {
        super(context, x, y);
        altura = Ar.altura(25);
        ancho = Ar.ancho(25);
        imagen = CargadorGraficos.cargarDrawable(context, R.drawable.vida_power_up);
    }

    @Override
    public void mover() {
        y += aceleracionY;
    }

    public void accion(GameView gameView){

        gameView.getBarraVidas().setValorActual(gameView.getBarraVidas().getValorActual() + 1);

    }

    @Override
    public void revertir(AbstractNave nave) {

    }

    @Override
    public boolean perdidoAlImpactar() {
        return false;
    }

}
