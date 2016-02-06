package com.plataformas;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

import com.plataformas.gestores.GestorAudio;
import com.plataformas.gestores.GestorNiveles;

public class MainActivity extends Activity {
    GameView gameView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        gameView = new GameView(this);
        setContentView(gameView);
        gameView.numeroNivel = GestorNiveles.getInstancia().getNivelActual();
        gameView.requestFocus();
    }

    @Override
    public void onBackPressed() {
        finish();
        System.gc();

        synchronized(gameView.gameloop)
        {
            gameView.context = null;
            gameView.gameloop.setRunning(false);
            gameView = null;
        }
    }

    @Override
    protected void onPause() {

        if(GestorAudio.getInstancia() != null){

            GestorAudio.getInstancia().pararMusicaAmbiente();

        }
        gameView.gameloop.setRunning(false);
        super.onPause();
    }

    @Override
    protected void onResume() {

        if(GestorAudio.getInstancia() != null){

            GestorAudio.getInstancia().reproducirMusicaAmbiente();

        }
        gameView.gameloop.setRunning(true);
        super.onResume();

    }
}