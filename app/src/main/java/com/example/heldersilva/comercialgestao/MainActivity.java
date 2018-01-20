package com.example.heldersilva.comercialgestao;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    protected EditText oNome,aMorada,oNumero;
    protected Button botaoInserir,botaoListaClientes;
    protected AdaptadorBaseDados db;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;

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

        botaoListaClientes=(Button)findViewById(R.id.botaoListaClientes);
        botaoListaClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                osNomes = db.obterTodosNomes();
                executarOutraActivity(ListarActivity.class,(ArrayList)osNomes);
            }
        });

        auth = FirebaseAuth.getInstance();


        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {

                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_logout, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_exit) {
            auth.signOut();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return true;

        }


        return super.onOptionsItemSelected(item);
    }
}
