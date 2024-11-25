package com.example.atv13.controller;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.atv13.persistence.DatabaseHelper;
import com.example.atv13.model.Livro;

import java.util.ArrayList;
import java.util.List;

public class LivroDao implements ICRUDDao<Livro> {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public LivroDao(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    @Override
    public void insert(Livro livro) throws Exception {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("titulo", livro.getTitulo());
        values.put("autor", livro.getAutor());
        values.put("numeroDePaginas", livro.getNumeroDePaginas());
        db.insert("Livro", null, values);
        db.close();
    }

    @Override
    public int update(Livro livro) throws Exception {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("titulo", livro.getTitulo());
        values.put("autor", livro.getAutor());
        values.put("numeroDePaginas", livro.getNumeroDePaginas());
        int rows = db.update("Livro", values, "id = ?", new String[]{String.valueOf(livro.getId())});
        db.close();
        return rows;
    }

    @Override
    public void delete(Livro livro) throws Exception {
        db = dbHelper.getWritableDatabase();
        db.delete("Livro", "id = ?", new String[]{String.valueOf(livro.getId())});
        db.close();
    }

    @SuppressLint("Range")
    @Override
    public Livro findOne(int id) throws Exception {
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("Livro", null, "id = ?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor.moveToFirst()) {
            @SuppressLint("Range") Livro livro = new Livro(
                    cursor.getString(cursor.getColumnIndex("titulo")),
                    cursor.getString(cursor.getColumnIndex("autor")),
                    cursor.getInt(cursor.getColumnIndex("numeroDePaginas"))
            );
            livro.setId(cursor.getInt(cursor.getColumnIndex("id")));
            cursor.close();
            db.close();
            return livro;
        }
        db.close();
        return null;
    }

    @SuppressLint("Range")
    @Override
    public List<Livro> findAll() throws Exception {
        db = dbHelper.getReadableDatabase();
        List<Livro> livros = new ArrayList<>();
        Cursor cursor = db.query("Livro", null, null, null, null, null, "titulo ASC");
        while (cursor.moveToNext()) {
            @SuppressLint("Range") Livro livro = new Livro(
                    cursor.getString(cursor.getColumnIndex("titulo")),
                    cursor.getString(cursor.getColumnIndex("autor")),
                    cursor.getInt(cursor.getColumnIndex("numeroDePaginas"))
            );
            livro.setId(cursor.getInt(cursor.getColumnIndex("id")));
            livros.add(livro);
        }
        cursor.close();
        db.close();
        return livros;
    }
}
