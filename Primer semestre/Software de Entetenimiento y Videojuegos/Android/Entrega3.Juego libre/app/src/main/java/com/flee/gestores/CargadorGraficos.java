package com.flee.gestores;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.flee.modelo.Tile;

import java.util.HashMap;

public class CargadorGraficos {

    private static HashMap<Integer, Drawable> drawables = new HashMap<>();
    private static HashMap<Integer, Bitmap> bitmaps = new HashMap<>();

    public static Drawable cargarDrawable(Context context, int id){
        if (drawables.containsKey(id)){
            return drawables.get(id);
        }

        Drawable nuevoDrawable = context.getResources().getDrawable(id);
        drawables.put(id, nuevoDrawable);
        return nuevoDrawable;
    }

    public static Drawable cargarDrawable(Context context, int id, boolean tile){

        if (drawables.containsKey(id)){
            return drawables.get(id);
        }

        Drawable nuevoDrawable = context.getResources().getDrawable(id);
        Bitmap b = ((BitmapDrawable)nuevoDrawable).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, Tile.ancho, Tile.altura, false);
        drawables.put(id, new BitmapDrawable(context.getResources(), bitmapResized));

        return nuevoDrawable;
    }

    public static Bitmap cargarBitmap(Context context,int id)
    {
        if (bitmaps.containsKey(id)){
            return bitmaps.get(id);
        }

        Bitmap nuevoBitmap = ((BitmapDrawable)context.getResources().getDrawable(id)).getBitmap();
        bitmaps.put(id,nuevoBitmap);
        return nuevoBitmap;
    }

}