package com.simple;

import android.app.Activity;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.simple.gestores.GestorAudio;
import com.simple.utilidades.Ar;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        Ar.configurar(size.x, size.y);

        GameView gameView = new GameView(this);
        setContentView(gameView);
        gameView.requestFocus();
    }

    @Override
    protected void onPause() {

        if(GestorAudio.getInstancia() != null){

            GestorAudio.getInstancia().pararMusicaAmbiente();

        }
        GameView.pausa = true;
        super.onPause();
    }

    @Override
    protected void onResume() {

        if(GestorAudio.getInstancia() != null){

            GestorAudio.getInstancia().reproducirMusicaAmbiente();

        }

        super.onResume();

    }

}
