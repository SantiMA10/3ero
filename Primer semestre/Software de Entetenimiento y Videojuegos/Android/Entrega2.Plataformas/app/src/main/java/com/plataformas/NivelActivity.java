package com.plataformas;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.plataformas.gestores.GestorAudio;
import com.plataformas.gestores.GestorNiveles;

public class NivelActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_nivel);

        Button botonNivel1 = ((Button) findViewById(R.id.botonNivel1));
        botonNivel1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                GestorNiveles.getInstancia().setNivelActual(0);
                Intent actividadJuego = new Intent(NivelActivity.this, MainActivity.class);
                startActivity(actividadJuego);

            }

        });

        Button botonNivel2 = ((Button) findViewById(R.id.botonNivel2));
        botonNivel2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                GestorNiveles.getInstancia().setNivelActual(1);
                Intent actividadJuego = new Intent(NivelActivity.this, MainActivity.class);
                startActivity(actividadJuego);

            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nivel, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {

        if(GestorAudio.getInstancia() != null){

            GestorAudio.getInstancia().pararMusicaAmbiente();

        }
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
