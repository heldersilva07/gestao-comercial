package com.example.heldersilva.comercialgestao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetalhesActivity extends AppCompatActivity {
    protected TextView lbnome,lbmorada,lbnumero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);
        Intent oIntent = getIntent();
        lbnome = (TextView) findViewById(R.id.lblnome);
        lbmorada =(TextView) findViewById(R.id.lblmorada);
        lbnumero =(TextView)findViewById(R.id.lblnumero);

        lbnome.setText(oIntent.getStringExtra("nome"));
        lbmorada.setText(oIntent.getStringExtra("morada"));
        lbnumero.setText(oIntent.getStringExtra("telefone"));

    }
}
