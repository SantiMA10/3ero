package com.flee.modelo.item.powerUp;

import android.content.Context;

import com.flee.modelo.Nivel;
import com.flee.modelo.item.Item;

public abstract class PowerUp extends Item {

    public long tiempoPowerUp;
    public long tiempoAccion;
    protected Nivel nivel;

    public boolean aplicado = false;

    public PowerUp(Context context, double x, double y) {
        super(context, x, y);
        tiempoAccion = 0;
    }

    @Override
    public void aplicar(Nivel nivel){
        tiempoAccion = System.currentTimeMillis();
        this.nivel = nivel;
        if(this.nivel.jugador.powerUp != null){
            this.nivel.jugador.powerUp.deshacer();
        }
        this.nivel.jugador.powerUp = this;
        aplicado = true;
        aplicarAbstract();
    }

    protected abstract void aplicarAbstract();
    public abstract void deshacer();

}
