package com.example.heldersilva.comercialgestao;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ListarActivity extends ListActivity {
    protected AdaptadorBaseDados a;
    protected List<String> lista;
    protected String nome, morada, telefone;

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Start", Toast.LENGTH_SHORT).show();
        a = new AdaptadorBaseDados(this).open();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        Intent oItent = getIntent();
        lista = oItent.getStringArrayListExtra("osNomes");
        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);
        setListAdapter(adaptador);
    }
    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Stop", Toast.LENGTH_SHORT).show();
        a.close();
    }

    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Pause", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onListItemClick(ListView parent, View v, int position, long id) {
        nome = lista.get(position).toString();
        morada = a.Obtermorada(lista.get(position));
        telefone = a.Obtertelefone(lista.get(position));
        executarOutraActivity(DetalhesActivity.class, lista.get(position), morada, telefone);
    }
    private void executarOutraActivity(Class<?> subActividade,String nome, String morada, String telefone) {
        Intent x = new Intent(this, subActividade);
        x.putExtra("nome", nome);
        x.putExtra("morada", morada);
        x.putExtra("telefone", telefone);
        startActivity(x);
    }
}
