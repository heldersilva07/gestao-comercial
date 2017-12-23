package com.example.heldersilva.comercialgestao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorBaseDados {
    private AjudaUsoBaseDados dbHelper;
    private SQLiteDatabase database;

    public AdaptadorBaseDados(Context context) {
        dbHelper = new AjudaUsoBaseDados(context.getApplicationContext());
    }
    public AdaptadorBaseDados open() {
        database = dbHelper.getWritableDatabase();
        return this;
    }
    public void close() {

        dbHelper.close();
    }
    public boolean existe(String umNome) {
        Cursor cursor = database.rawQuery(
                "select nome from app where nome=?", new String[] { umNome });
        boolean b = cursor.getCount() >= 1;
        cursor.close();
        return b;
    }
    private Cursor obterTodosRegistos() {
        String[] colunas = new String[4];
        colunas[0] = "_id";
        colunas[1] = "nome";
        colunas[2] = "morada";
        colunas[3] = "numero";
        return database.query("clientes", colunas, null, null, null, null, null);
    }
    public long insertNomeMoradaNumero(String oNome, String aMorada,String oNumero) {
        ContentValues values = new ContentValues() ;
        values.put("nome", oNome);
        values.put("morada", aMorada);
        values.put("numero", oNumero);
        return database.insert("clientes", null, values);
    }

    public List<String> obterTodosNomes() {
        ArrayList<String> resultados = new ArrayList<String>();
        Cursor cursor = obterTodosRegistos();
        if (cursor.moveToFirst()) {
            do {
                resultados.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return resultados;
    }

    private Cursor RegistoWhere(String nome) {
        String[] colunas = new String[3];
        colunas[0] = "nome";
        colunas[1] = "morada";
        colunas[2] = "numero";
        String selection = "nome=?";
        String[] selectionArgs = {nome.toString()};
        return database.query("clientes", colunas, selection, selectionArgs, null, null, "_id");
    }
    protected String Obternome(String nome) {
        String resultados = new String();
        Cursor cursor = RegistoWhere(nome);
        if (cursor.moveToFirst()) {
            do {
                resultados = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return resultados;
    }

    protected String Obtermorada(String nome) {
        String resultados = new String();
        Cursor cursor = RegistoWhere(nome);
        if (cursor.moveToFirst()) {
            do {
                resultados = cursor.getString(1);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return resultados;
    }
    protected String Obtertelefone(String nome) {
        String resultados = new String();
        Cursor cursor = RegistoWhere(nome);
        if (cursor.moveToFirst()) {
            do {
                resultados = cursor.getString(2);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return resultados;
    }
    protected void Eliminar(long id) {

        database.delete("clientes", "_id = ?", new String[] { String.valueOf(id) });
        database.close();
    }
}