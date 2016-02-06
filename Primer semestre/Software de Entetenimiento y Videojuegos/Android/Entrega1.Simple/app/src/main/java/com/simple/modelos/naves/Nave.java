package com.simple.modelos.naves;

import android.content.Context;

import com.simple.modelos.ModeloMovimientoInterface;
import com.simple.modelos.items.powerUps.PowerUp;
import com.simple.modelos.naves.disparos.DisparoJugador;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by santiagomartin on 8/10/15.
 */
public interface Nave extends ModeloMovimientoInterface {

    public void frenar();
    public void acelerar(float factorX, float factorY);
    public void disparando();
    public void setModoDisparo(String modoDisparo);
    public DisparoJugador disparar(long milis);
    public void impactado();
    public boolean isEscondido();
    public int getVida();
    public void setPowerUp(PowerUp powerUp);
    public HashMap<String, Object> getDefaults();
    public PowerUp getPowerUp();

}
