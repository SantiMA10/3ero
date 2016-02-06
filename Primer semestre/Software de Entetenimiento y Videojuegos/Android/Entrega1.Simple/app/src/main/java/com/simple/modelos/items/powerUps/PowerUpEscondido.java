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
 * Created by santiagomartin on 6/10/15.
 */
public class PowerUpEscondido extends Modelo implements PowerUp {

    protected float aceleracionY = 2;

    public PowerUpEscondido(Context context, double x, double y) {

        super(context, x, y);
        altura = Ar.altura(25);
        ancho = Ar.ancho(25);
        imagen = CargadorGraficos.cargarDrawable(context, R.drawable.escudo_power_up);

    }

    @Override
    public void mover() {
        y += aceleracionY;
    }

    public void accion(GameView gameView){

        AbstractNave nave = (AbstractNave)gameView.getNaveBasica();
        if(nave.getPowerUp() != null)
            nave.getPowerUp().revertir(nave);
        nave.escondido = true;
        nave.sprite = nave.sprites.get(nave.ESCONDIDO);
        nave.setPowerUp(this);

    }

    @Override
    public void revertir(AbstractNave nave) {

        nave.escondido = false;
        nave.sprite = nave.sprites.get(nave.BASICO);
        nave.setPowerUp(null);

    }

    @Override
    public boolean perdidoAlImpactar() {
        return true;
    }
}
