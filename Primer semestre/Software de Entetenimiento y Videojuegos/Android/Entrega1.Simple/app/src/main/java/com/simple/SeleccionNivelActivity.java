package com.simple;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.simple.gestores.GestorNiveles;

public class SeleccionNivelActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_seleccion_nivel);

        ImageButton botonNivel1 = ((ImageButton) findViewById(R.id.imageButtonNivel1));
        botonNivel1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                GestorNiveles.getInstancia().setNivelActual(1);
                Intent actividadJuego = new Intent(SeleccionNivelActivity.this, MainActivity.class);
                startActivity(actividadJuego);

            }

        });

        ImageButton botonNivel2 = ((ImageButton) findViewById(R.id.imageButtonNivel2));
        botonNivel2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                GestorNiveles.getInstancia().setNivelActual(2);
                Intent actividadJuego = new Intent(SeleccionNivelActivity.this, MainActivity.class);
                startActivity(actividadJuego);

            }

        });

        ImageButton botonNivel3 = ((ImageButton) findViewById(R.id.imageButtonNivel3));
        botonNivel3.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                GestorNiveles.getInstancia().setNivelActual(3);
                Intent actividadJuego = new Intent(SeleccionNivelActivity.this, MainActivity.class);
                startActivity(actividadJuego);

            }

        });

        ImageButton botonNivelInf = ((ImageButton) findViewById(R.id.imageButtonNivelInf));
        botonNivelInf.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                GestorNiveles.getInstancia().setNivelActual(Integer.MAX_VALUE);
                Intent actividadJuego = new Intent(SeleccionNivelActivity.this, MainActivity.class);
                startActivity(actividadJuego);

            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_seleccion_nivel, menu);
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
}
