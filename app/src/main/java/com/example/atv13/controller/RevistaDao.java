package com.example.atv13.controller;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.example.atv13.persistence.DatabaseHelper;
import com.example.atv13.model.Revista;
import java.util.ArrayList;
import java.util.List;

public class RevistaDao {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public RevistaDao(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(Revista revista) throws SQLException {
        open();
        ContentValues values = new ContentValues();
        values.put("titulo", revista.getTitulo());
        values.put("autor", revista.getAutor());
        values.put("edicao", revista.getEdicao());
        db.insert("Revista", null, values);
        close();
    }

    public int update(Revista revista) throws SQLException {
        open();
        ContentValues values = new ContentValues();
        values.put("titulo", revista.getTitulo());
        values.put("autor", revista.getAutor());
        values.put("edicao", revista.getEdicao());
        int rows = db.update("Revista", values, "id = ?", new String[]{String.valueOf(revista.getId())});
        close();
        return rows;
    }

    public void delete(Revista revista) throws SQLException {
        open();
        db.delete("Revista", "id = ?", new String[]{String.valueOf(revista.getId())});
        close();
    }

    @SuppressLint("Range")
    public Revista findOne(int id) throws SQLException {
        open();
        Cursor cursor = db.query("Revista", null, "id = ?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") Revista revista = new Revista(
                    cursor.getString(cursor.getColumnIndex("titulo")),
                    cursor.getString(cursor.getColumnIndex("autor")),
                    cursor.getInt(cursor.getColumnIndex("edicao"))
            );
            revista.setId(cursor.getInt(cursor.getColumnIndex("id")));
            cursor.close();
            close();
            return revista;
        }
        close();
        return null;
    }

    @SuppressLint("Range")
    public List<Revista> findAll() throws SQLException {
        open();
        List<Revista> revistas = new ArrayList<>();
        Cursor cursor = db.query("Revista", null, null, null, null, null, "titulo ASC");
        while (cursor.moveToNext()) {
            @SuppressLint("Range") Revista revista = new Revista(
                    cursor.getString(cursor.getColumnIndex("titulo")),
                    cursor.getString(cursor.getColumnIndex("autor")),
                    cursor.getInt(cursor.getColumnIndex("edicao"))
            );
            revista.setId(cursor.getInt(cursor.getColumnIndex("id")));
            revistas.add(revista);
        }
        cursor.close();
        close();
        return revistas;
    }
}
