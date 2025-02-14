package com.example.notas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class BaseDatos extends SQLiteOpenHelper {
    private static final String NOMBRE_BD = "notas.db";
    private static final int VERSION_BD = 1;
    private static final String TABLA_NOTAS = "notas";
    private static final String COLUMNA_ID = "id";
    private static final String COLUMNA_NOTA = "texto";

    public BaseDatos(Context contexto) {
        super(contexto, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        String crearTabla = "CREATE TABLE " + TABLA_NOTAS
                + " (" + COLUMNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMNA_NOTA + " TEXT NOT NULL);";
        bd.execSQL(crearTabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int versionAntigua, int versionNueva) {
        bd.execSQL("DROP TABLE IF EXISTS " + TABLA_NOTAS);
        onCreate(bd);
    }

    public void insertarNota(String nota) {
        SQLiteDatabase bd = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(COLUMNA_NOTA, nota);
        bd.insert(TABLA_NOTAS, null, valores);
        bd.close();
    }

    public List<String> obtenerTodasLasNotas() {
        List<String> notas = new ArrayList<>();
        SQLiteDatabase bd = this.getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM " + TABLA_NOTAS, null);
        if (cursor.moveToFirst()) {
            do {
                notas.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMNA_NOTA)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        bd.close();
        return notas;
    }
}
