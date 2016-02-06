package com.simple.modelos.naves;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.simple.R;
import com.simple.graficos.Sprite;
import com.simple.utilidades.Ar;

import java.util.HashMap;

/**
 * Created by santiagomartin on 8/10/15.
 */
public class NaveRapida extends AbstractNave {

    public static final int Nave = R.drawable.nave_rapida;
    public static final String descripcion  = "Es la nave rapida en movimiento y disparo, pero los power ups le duran poco tiempo.\n";

    public NaveRapida(Context context, double x, double y) {
        super(context, x, y);

        altura = Ar.altura(63);
        ancho = Ar.ancho(50);

        Sprite basico = new Sprite(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.animacion_nave_rapida),
                ancho, altura,
                4, 4, true);
        sprites.put(BASICO, basico);

        Sprite impactado = new Sprite(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.nave_rapida_parpadea),
                ancho, altura,
                4, 12, false);
        sprites.put(IMPACTADO, impactado);

        Sprite escudo = new Sprite(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.animacion_nave_rapida_escudo),
                ancho, altura,
                4, 4, true);
        sprites.put(ESCONDIDO, escudo);

        sprite = basico;

        maxAceleracionX = 6;
        maxAceleracionY = 6;
        tiempoPowerUp = 2000;
        cadenciaDisparo = 75;
        defaultCadenciaDisparo = 75;

        vida = 4;

        disparo = R.drawable.disparo_nave_rapida;

    }

    public static HashMap<String, Object> getInfo(){

        HashMap<String, Object> naveRapida = new HashMap<String, Object>();
        naveRapida.put("icono", NaveRapida.Nave);
        naveRapida.put("descripcion", NaveRapida.descripcion);

        return naveRapida;

    }

}
