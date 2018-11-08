package com.example.nachodelaviuda.proyecto_nacho;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class NuevoDetallesPelicula extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_detalle);

        TextView titulo = (TextView) findViewById(R.id.tbTituloDescripcion);
        TextView detalles = (TextView) findViewById(R.id.tvdescripcion);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if(b!=null) {
            titulo.setText(b.getString("TIT"));
            detalles.setText(b.getString("DET"));
        }
    }
}
