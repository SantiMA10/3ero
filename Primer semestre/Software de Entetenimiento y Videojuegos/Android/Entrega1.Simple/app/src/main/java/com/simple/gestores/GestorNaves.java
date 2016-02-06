package com.simple.gestores;

import android.content.Context;
import android.util.Log;

import com.simple.modelos.naves.Nave;
import com.simple.modelos.naves.NaveBasica;
import com.simple.modelos.naves.NaveRapida;
import com.simple.util.CircularArrayList;
import com.simple.utilidades.Ar;

import java.util.HashMap;

/**
 * Created by santiagomartin on 8/10/15.
 */
public class GestorNaves {

    private static GestorNaves instancia = null;
    private String nave;

    public CircularArrayList<HashMap<String, Object>> listaNaves;

    private GestorNaves() {

        listaNaves = new CircularArrayList<>();
        listaNaves.add(NaveRapida.getInfo());
        listaNaves.add(NaveBasica.getInfo());

        up();
    }

    public static GestorNaves getInstancia() {
        synchronized (GestorNaves.class){

            if(instancia == null){
                instancia = new GestorNaves();
            }
            return instancia;

        }

    }

    public HashMap<String, Object> up(){
        HashMap<String, Object> n = listaNaves.getUp();
        this.nave = (String)n.get("descripcion");
        Log.v("GN", nave);

        return n;
    }

    public HashMap<String, Object> down(){
        HashMap<String, Object> n = listaNaves.getDown();
        this.nave = (String)n.get("descripcion");
        Log.v("GN", nave);

        return n;
    }

    public Nave getNave(Context context) {

        if(nave != null && nave.equals(NaveBasica.descripcion)){
            return new NaveBasica(context, Ar.x(160), Ar.x(300));
        }
        else if(nave != null && nave.equals(NaveRapida.descripcion)){
            return new NaveRapida(context,  Ar.x(160), Ar.x(300));
        }

        return new NaveRapida(context,  Ar.x(160), Ar.x(300));
    }


}
