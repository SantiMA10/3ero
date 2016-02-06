package com.simple.modelos.items.powerUps;

import android.content.Context;

import com.simple.GameView;
import com.simple.R;
import com.simple.gestores.CargadorGraficos;
import com.simple.modelos.ModeloMovimiento;
import com.simple.modelos.naves.AbstractNave;
import com.simple.modelos.naves.Nave;
import com.simple.utilidades.Ar;

/**
 * Created by santiagomartin on 10/10/15.
 */
public class PowerUpDisparoRafaga extends ModeloMovimiento implements PowerUp{

    protected float aceleracionY = 2;

    public PowerUpDisparoRafaga(Context context, double x, double y) {

        super(context, x, y);
        altura = Ar.altura(25);
        ancho = Ar.ancho(25);
        imagen = CargadorGraficos.cargarDrawable(context, R.drawable.rafaga_power_up);

    }

    @Override
    public void revertir(AbstractNave nave) {
        nave.cadenciaDisparo = ((int) nave.getDefaults().get("cadenciaDisparo"));
        nave.setPowerUp(null);
    }

    @Override
    public boolean perdidoAlImpactar() {
        return false;
    }

    @Override
    public void accion(GameView gameView) {

        AbstractNave nave = (AbstractNave)gameView.getNaveBasica();
        if(nave.getPowerUp() != null)
            nave.getPowerUp().revertir(nave);
        nave.setPowerUp(this);
        nave.cadenciaDisparo = 0;

    }

    public void mover() {
        y += aceleracionY;
    }

}
