package com.flee;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.flee.gestores.GestorAudio;

public class GameActivity extends Activity {

    GameView gameView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_game);

        gameView = new GameView(this);
        setContentView(gameView);
        gameView.numeroNivel = 0;
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
