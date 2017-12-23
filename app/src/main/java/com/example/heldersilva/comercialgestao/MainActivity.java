package com.example.heldersilva.comercialgestao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    protected EditText oNome,aMorada,oNumero;
    protected Button botaoInserir,botaoProximo;
    protected AdaptadorBaseDados db;

    List<String> osNomes;

    @Override
    protected void onStart() {
        super.onStart();
        db = new AdaptadorBaseDados(this).open();
    }

    private void executarOutraActivity(Class<?> subActividade, ArrayList<String> oValor) {
        Intent x = new Intent(this, subActividade);
        x.putExtra("osNomes", oValor); startActivity(x);
    }

    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "MainActivity onPause()", Toast.LENGTH_SHORT).show();
    }
    @Override protected void onStop() {
        super.onStop();
        Toast.makeText(this, "MainActivity onStop()", Toast.LENGTH_SHORT).show();
        db.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        oNome =(EditText) findViewById(R.id.oNome);
        aMorada=(EditText) findViewById(R.id.aMorada);
        oNumero=(EditText) findViewById(R.id.oNumero);
        botaoInserir=(Button)findViewById(R.id.botaoInserir);
        botaoInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.insertNomeMoradaNumero(oNome.getText().toString(),aMorada.getText().toString(),oNumero.getText().toString());
                oNome.setText("");
                aMorada.setText("");
                oNumero.setText("");
            }
        });

        botaoProximo=(Button)findViewById(R.id.botaoProximo);
        botaoProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                osNomes = db.obterTodosNomes();
                executarOutraActivity(ListarActivity.class,(ArrayList)osNomes);
            }
        });

    }
}
