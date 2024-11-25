package com.example.atv13.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "biblioteca.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Criação da tabela Livro
        String createTableLivro = "CREATE TABLE Livro (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "titulo TEXT NOT NULL," +
                "autor TEXT NOT NULL," +
                "numeroDePaginas INTEGER NOT NULL);";

        // Criação da tabela Revista
        String createTableRevista = "CREATE TABLE Revista (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "titulo TEXT NOT NULL," +
                "autor TEXT NOT NULL," +
                "edicao INTEGER NOT NULL);";

        db.execSQL(createTableLivro);
        db.execSQL(createTableRevista);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Livro");
        db.execSQL("DROP TABLE IF EXISTS Revista");
        onCreate(db);
    }
}
