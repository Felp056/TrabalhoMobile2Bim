package com.example.trabalhomobile2bim.Banco;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SqLiteDataHelper extends SQLiteOpenHelper {

    public SqLiteDataHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE USUARIO(MATRICULA INTEGER, NOME VARCHER(20) , SOBRENOME VARCHAR(50), SENHA VARCHAR(90))");
        db.execSQL("CREATE TABLE SAPATO(IDENTIFICADOR INTEGER, TIPO VARCHAR(20) , NOME VACHAR(50), TAMANHO INTEGER, VALOR NUMERIC)");
        db.execSQL("CREATE TABLE NOTA(NUMNOTA INTEGER, PRODUTOS VARCHAR(200), VALORNOTA NUMERIC)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
