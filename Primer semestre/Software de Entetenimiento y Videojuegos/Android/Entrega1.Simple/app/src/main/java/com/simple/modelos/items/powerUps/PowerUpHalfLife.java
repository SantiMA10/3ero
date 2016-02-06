package com.simple.modelos.items.powerUps;

import android.content.Context;

import com.simple.GameView;
import com.simple.R;
import com.simple.gestores.CargadorGraficos;
import com.simple.modelos.Modelo;
import com.simple.modelos.items.Item;
import com.simple.modelos.naves.AbstractNave;
import com.simple.modelos.naves.Nave;
import com.simple.modelos.naves.NaveBasica;
import com.simple.utilidades.Ar;

/**
 * Created by santiagomartin on 6/10/15.
 */
public class PowerUpHalfLife extends Modelo implements PowerUp {

    protected float aceleracionY = 2;

    public PowerUpHalfLife(Context context, double x, double y) {

        super(context, x, y);
        altura = Ar.altura(25);
        ancho = Ar.ancho(25);
        imagen = CargadorGraficos.cargarDrawable(context, R.drawable.halflife_power_up);

    }

    @Override
    public void mover() {
        y += aceleracionY;
    }

    public void accion(GameView gameView){

        AbstractNave nave = (AbstractNave)gameView.getNaveBasica();

        nave.setModoDisparo(NaveBasica.DISPARO_HALFFILE);
        nave.cadenciaDisparo = 3;
        gameView.getBarraVidas().setValorActual(gameView.getBarraVidas().getValorMaximo());
        gameView.getMarcador().setPuntos(gameView.getMarcador().getPuntos() + 3);

    }

    @Override
    public void revertir(AbstractNave nave) {

    }

    @Override
    public boolean perdidoAlImpactar() {
        return false;
    }

}
