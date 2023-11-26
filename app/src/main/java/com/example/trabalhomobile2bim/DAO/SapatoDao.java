package com.example.trabalhomobile2bim.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.trabalhomobile2bim.Banco.SqLiteDataHelper;
import com.example.trabalhomobile2bim.Objetos.Sapato;

import java.util.ArrayList;

public class SapatoDao implements GenericDao<Sapato>{
    private SQLiteOpenHelper openHelper;

    //Base de Dados
    private SQLiteDatabase baseDados;

    //nome das colunas da tabela;
    private String[]colunas = {"IDENTIFICADOR", "TIPO", "NOME", "TAMANHO", "VALOR"};

    //nome da tabela
    private String tabela = "SAPATO";

    //Contexto (view)
    private Context context;

    private static SapatoDao instancia;

    public static SapatoDao getInstancia(Context context){
        if(instancia == null){
            return instancia = new SapatoDao(context);
        }else{
            return instancia;
        }
    }

    private SapatoDao(Context context){
        this.context = context;

        //Abrir a conexão com a base de dados
        openHelper = new SqLiteDataHelper(this.context,
                "PDV_BD", null, 1);

        //instanciando a base de dados
        baseDados = openHelper.getWritableDatabase();


    }

    @Override
    public long insert(Sapato obj) {
        try{
            ContentValues valores = new ContentValues();
            valores.put(colunas[0], obj.getIdentificador());
            valores.put(colunas[1], obj.getTipo());
            valores.put(colunas[2], obj.getNome());
            valores.put(colunas[3], obj.getTamanho());
            valores.put(colunas[4], obj.getValor());

            return baseDados.insert(tabela, null, valores);

        }catch (SQLException ex){
            Log.e("PDV", "ERRO: SapatoDao.insert() "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public long update(Sapato obj) {
        try{
            ContentValues valores = new ContentValues();
            valores.put(colunas[1], obj.getTipo());
            valores.put(colunas[2], obj.getNome());
            valores.put(colunas[3], obj.getTamanho());
            valores.put(colunas[4], obj.getValor());

            String[]identificador = {String.valueOf(obj.getIdentificador())};

            return baseDados.update(tabela,  valores,
                    colunas[0]+"= ?", identificador);

        }catch (SQLException ex){
            Log.e("PDV", "ERRO: SapatoDao.update() "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public long delete(Sapato obj) {
        try{
            String[]identificador = {String.valueOf(obj.getIdentificador())};

            return baseDados.delete(tabela,
                    colunas[0]+"= ?", identificador);
        }catch (SQLException ex){
            Log.e("PDV", "ERRO: SapatoDao.delet() "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public ArrayList<Sapato> getAll() {
        ArrayList<Sapato> lista = new ArrayList<>();
        try{
            Cursor cursor = baseDados.query(tabela,
                    colunas, null,
                    null, null,
                    null, colunas[0]+" desc");

            if(cursor.moveToFirst()){
                do{
                    Sapato sapato = new Sapato();
                    sapato.setIdentificador(cursor.getInt(0));
                    sapato.setTipo(cursor.getString(1));
                    sapato.setNome(cursor.getString(2));
                    sapato.setTamanho(cursor.getInt(3));
                    sapato.setValor(cursor.getDouble(4));

                    lista.add(sapato);

                }while (cursor.moveToNext());
            }

        }catch (SQLException ex){
            Log.e("PDV", "ERRO: SapatoDao.GetAll() "+ex.getMessage());
        }

        return lista;
    }

    @Override
    public Sapato getById(int id) {
        try{
            String[]identificador = {String.valueOf(id)};
            Cursor cursor = baseDados.query(tabela, colunas,
                    colunas[0]+"= ?", identificador,
                    null, null, null);

            if(cursor.moveToFirst()){
                Sapato sapato = new Sapato();
                sapato.setIdentificador(cursor.getInt(0));
                sapato.setTipo(cursor.getString(1));
                sapato.setNome(cursor.getString(2));
                sapato.setTamanho(cursor.getInt(3));
                sapato.setValor(cursor.getDouble(4));
                return sapato;
            }

        }catch (SQLException ex){
            Log.e("PDV", "ERRO: SapatoDao.GetById() "+ex.getMessage());
        }
        return null;
    }
}
