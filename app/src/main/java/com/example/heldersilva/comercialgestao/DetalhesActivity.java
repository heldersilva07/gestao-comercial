package com.example.heldersilva.comercialgestao;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class DetalhesActivity extends AppCompatActivity {
    protected TextView lbnome,lbmorada,lbnumero;
    private static final String TAG = DetalhesActivity.class.getSimpleName();


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_map, menu);

        return true;
    }

    protected void openLocationInMap() {

        String addressString = "Av. Eng. Jose Afonso Maria de Figueiredo 121, 4470-285 Maia ";
        Uri geoLocation = Uri.parse("geo:0,0?q=" +addressString);

        //Uri geoLocation = Uri.parse("geo:0,0?q= Av. Eng. Jose Afonso Maria de Figueiredo 121, 4470-285 Maia &z=30");

        Intent intent = new Intent(Intent.ACTION_VIEW,geoLocation);
        intent.setData(geoLocation);
        intent.setPackage("com.google.android.apps.maps");

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d(TAG, "Couldn't call " + geoLocation.toString()
                    + ", no receiving apps installed!");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_map) {
            openLocationInMap();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
