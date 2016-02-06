package com.simple.modelos.naves;

import android.content.Context;
import android.graphics.Canvas;

import com.simple.graficos.Sprite;
import com.simple.modelos.ModeloMovimiento;
import com.simple.modelos.items.powerUps.PowerUp;
import com.simple.modelos.items.powerUps.PowerUpHalfLife;
import com.simple.modelos.naves.disparos.DisparoJugador;
import com.simple.modelos.naves.disparos.DisparoNave;
import com.simple.modelos.naves.disparos.DisparoNaveHalfLife;
import com.simple.utilidades.Ar;

import java.util.HashMap;

/**
 * Created by santiagomartin on 8/10/15.
 */
public abstract class AbstractNave extends ModeloMovimiento implements Nave {


    private boolean disparando = false;



    public static final String DISPARO_NORMAL = "Normal";
    public static final String DISPARO_HALFFILE = "halflife";

    private String modo_disparo = DISPARO_NORMAL;

    public static final String BASICO = "Basico";
    public static final String IMPACTADO = "Explotar";
    public static final String ESCONDIDO = "Escondido";

    public Sprite sprite;
    public HashMap<String,Sprite> sprites = new HashMap<String,Sprite> ();
    public boolean escondido = false;
    protected PowerUp powerUp;
    private long inicioTiempoPowerUp;
    public int disparo;

    protected long tiempoPowerUp;
    public int vida;
    private double aceleracionX;
    private double aceleracionY;
    protected double maxAceleracionX;
    protected double maxAceleracionY;
    public int defaultCadenciaDisparo;
    public int cadenciaDisparo;
    private long milisegundosDisparo;

    public AbstractNave(Context context, double x, double y) {
        super(context, x, y);
    }

    @Override
    public void dibujarEnPantalla(Canvas canvas){
        sprite.dibujarSprite(canvas, (int) x, (int) y);
    }

    public void impactado(){

        if(powerUp != null && powerUp.perdidoAlImpactar())
            powerUp.revertir(this);

        sprite = sprites.get(IMPACTADO);
        sprite.setFrameActual(0);

    }

    public boolean isEscondido(){
        return escondido;
    }

    public void setEscondido(boolean escondido){

        this.escondido = escondido;
        if(escondido){
            sprite = sprites.get(ESCONDIDO);
            inicioTiempoPowerUp = System.currentTimeMillis();
        }
        else{
            sprite = sprites.get(BASICO);
        }

    }
    public void mover(){

        boolean finalizaSprite = sprite.actualizar(System.currentTimeMillis());

        if(powerUp != null && (System.currentTimeMillis() - inicioTiempoPowerUp) > tiempoPowerUp){
            powerUp.revertir(this);
        }

        if (sprite == sprites.get(IMPACTADO) && finalizaSprite) {
            sprite = sprites.get(BASICO);
        }

        x = x + aceleracionX;
        y = y + aceleracionY;
        if(y < (mCanvasAltura/2) + (altura/2)){
            y = (mCanvasAltura/2) + (altura/2);
        }
        if(y > mCanvasAltura - altura/2){
            y = mCanvasAltura - altura/2;
        }
        if(x < ancho/2) {
            x = ancho / 2;
        }
        if(x > mCanvasAncho - ancho/2){
            x = mCanvasAncho - ancho/2;
        }
    }

    public void acelerar(float factorX, float factorY){
        if(factorX < 0){
            aceleracionX = Ar.x(maxAceleracionX);
        }
        if(factorX >0){
            aceleracionX = Ar.x(-maxAceleracionX);
        }
        if(factorY < 0){
            aceleracionY = Ar.y(maxAceleracionY);
        }
        if(factorY >0){
            aceleracionY = Ar.y(-maxAceleracionY);
        }
    }

    public void frenar(){

        aceleracionX = 0;
        aceleracionY = 0;

    }

    public void disparando(){

        disparando = true;

    }

    public void setModoDisparo(String modo){
        this.modo_disparo = modo;
    }

    public DisparoJugador disparar(long milisegundos){
        if(disparando && milisegundos - milisegundosDisparo > cadenciaDisparo + Math.random()* cadenciaDisparo){

            disparando = false;
            milisegundosDisparo = milisegundos;

            if(modo_disparo == DISPARO_NORMAL){
                return new DisparoNave(context,x,y, disparo);
            }
            else if(modo_disparo == DISPARO_HALFFILE){
                return new DisparoNaveHalfLife(context, x, y);
            }
        }
        return null;
    }

    public int getVida(){
        return vida;
    }

    public void setPowerUp(PowerUp powerUp){

        this.powerUp = powerUp;
        this.inicioTiempoPowerUp = System.currentTimeMillis();

    }

    public HashMap<String, Object> getDefaults(){
        HashMap<String, Object> defaults = new HashMap<>();

        defaults.put("cadenciaDisparo", defaultCadenciaDisparo);

        return defaults;
    }

    public PowerUp getPowerUp(){
        return powerUp;
    }

}
