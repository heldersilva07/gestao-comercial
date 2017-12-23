package com.example.heldersilva.comercialgestao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AjudaUsoBaseDados extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "base-dados.db";
    private static final int VERSION = 1;

    public AjudaUsoBaseDados(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String s = "CREATE TABLE clientes(_id integer primary key autoincrement,nome varchar(40), morada varchar(40) ,numero integer)";
        db.execSQL(s);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS clientes");
        onCreate(db);
    }
}