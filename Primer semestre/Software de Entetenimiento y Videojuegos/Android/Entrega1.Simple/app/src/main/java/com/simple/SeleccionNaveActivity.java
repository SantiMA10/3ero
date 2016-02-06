package com.simple;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.simple.gestores.GestorNaves;

import java.util.HashMap;
import java.util.Objects;

public class SeleccionNaveActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_seleccion_nave);

        HashMap<String, Object> nave = GestorNaves.getInstancia().down();

        ImageView iconoNave = ((ImageView) findViewById(R.id.imageNave));
        iconoNave.setImageResource(0);
        iconoNave.setImageResource((int) nave.get("icono"));

        TextView descripcion = ((TextView) findViewById(R.id.descripcionNave));
        descripcion.setText((String) nave.get("descripcion"));

        ImageButton botonSeleccionar = ((ImageButton) findViewById(R.id.botonSeleccionar));
        botonSeleccionar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent actividadJuego = new Intent(SeleccionNaveActivity.this, SeleccionNivelActivity.class);
                startActivity(actividadJuego);

            }

        });

        ImageButton botonFlechaIzquierda = ((ImageButton) findViewById(R.id.botonFlechaIzquierda));
        botonFlechaIzquierda.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                HashMap<String, Object> nave = GestorNaves.getInstancia().down();

                ImageView iconoNave = ((ImageView) findViewById(R.id.imageNave));
                iconoNave.setImageResource(0);
                iconoNave.setImageResource((int)nave.get("icono"));

                TextView descripcion = ((TextView) findViewById(R.id.descripcionNave));
                descripcion.setText((String)nave.get("descripcion"));

            }

        });

        ImageButton botonFlechaDerecha = ((ImageButton) findViewById(R.id.botonFlechaDerecha));
        botonFlechaDerecha.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                HashMap<String, Object> nave = GestorNaves.getInstancia().up();

                ImageView iconoNave = ((ImageView) findViewById(R.id.imageNave));
                iconoNave.setImageResource(0);
                iconoNave.setImageResource((int) nave.get("icono"));

                TextView descripcion = ((TextView) findViewById(R.id.descripcionNave));
                descripcion.setText((String)nave.get("descripcion"));

            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_seleccion_nave, menu);
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
