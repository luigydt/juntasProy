package com.example.proyandroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class OpenHelper extends SQLiteOpenHelper {

    String sqlCreateJuntas = "CREATE TABLE Juntas (id INTEGER PRIMARY KEY NOT NULL, nPersonas INTEGER,idSorteo INTEGER,cantidad INTEGER,fecha TEXT)";
    String sqlCreateParticipantes = "CREATE TABLE Participantes(id INTEGER PRIMARY KEY NOT NULL,nombre TEXT,idJunta INTEGER,Pagado INTEGER,FOREIGN KEY (idJunta)REFERENCES Juntas(id))";
    String sqlCreateSorteo = "CREATE TABLE Sorteo(id INTEGER PRIMARY KEY NOT NULL,Nombre TEXT,Cobrado INTEGER)";


    public OpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreateJuntas);
        db.execSQL(sqlCreateParticipantes);
        db.execSQL(sqlCreateSorteo);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Juntas");
        db.execSQL("DROP TABLE IF EXISTS Participantes");
        db.execSQL("DROP TABLE IF EXISTS Sorteo");
        db.execSQL(sqlCreateJuntas);
        db.execSQL(sqlCreateParticipantes);
        db.execSQL(sqlCreateSorteo);
    }
}
