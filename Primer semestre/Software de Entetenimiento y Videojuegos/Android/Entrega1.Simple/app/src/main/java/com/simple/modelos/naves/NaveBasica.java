package com.simple.modelos.naves;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.simple.R;
import com.simple.graficos.Sprite;
import com.simple.utilidades.Ar;

import java.util.HashMap;

public class NaveBasica extends AbstractNave {

    public static final int Nave = R.drawable.nave;
    public static final String descripcion  = "Es la nave mas basica y aun asi los power ups le hacen efecto durante mas tiempo.\n";

    public NaveBasica(Context context, double x, double y) {
        super(context, x, y);

        altura = Ar.altura(63);
        ancho = Ar.ancho(50);

        Sprite basico = new Sprite(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.animacion_nave),
                ancho, altura,
                4, 4, true);
        sprites.put(BASICO, basico);

        Sprite impactado = new Sprite(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.animacion_nave_explota),
                ancho, altura,
                4, 12, false);
        sprites.put(IMPACTADO, impactado);

        Sprite escudo = new Sprite(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.animacion_nave_escudo),
                ancho, altura,
                4, 4, true);
        sprites.put(ESCONDIDO, escudo);

        sprite = basico;

        maxAceleracionX = 4;
        maxAceleracionY = 4;
        tiempoPowerUp = 6000;
        cadenciaDisparo = 250;
        defaultCadenciaDisparo = 250;

        vida = 6;
        disparo = R.drawable.disparo_nave;

    }

    public static HashMap<String, Object> getInfo(){

        HashMap<String, Object> naveBasica = new HashMap<String, Object>();
        naveBasica.put("icono", NaveBasica.Nave);
        naveBasica.put("descripcion", NaveBasica.descripcion);

        return naveBasica;

    }

}
