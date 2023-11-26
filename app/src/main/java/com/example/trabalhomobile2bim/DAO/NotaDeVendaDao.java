package com.example.trabalhomobile2bim.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.trabalhomobile2bim.Banco.SqLiteDataHelper;
import com.example.trabalhomobile2bim.Objetos.NotaDeVenda;
import com.example.trabalhomobile2bim.Objetos.Usuario;

import java.util.ArrayList;

public class NotaDeVendaDao implements GenericDao<NotaDeVenda>{

    private SQLiteOpenHelper openHelper;

    //Base de Dados
    private SQLiteDatabase baseDados;

    //nome das colunas da tabela;
    private String[]colunas = {"NUMNOTA", "PRODUTOS", "VALORNOTA"};

    //nome da tabela
    private String tabela = "NOTA";

    //Contexto (view)
    private Context context;

    private static NotaDeVendaDao instancia;

    public static NotaDeVendaDao getInstancia(Context context){
        if(instancia == null){
            return instancia = new NotaDeVendaDao(context);
        }else{
            return instancia;
        }
    }

    private NotaDeVendaDao(Context context){
        this.context = context;
        openHelper = new SqLiteDataHelper(this.context,
                "PDV_BD", null, 1);
        baseDados = openHelper.getWritableDatabase();


    }
    public long insert(NotaDeVenda obj) {
        try{
            ContentValues valores = new ContentValues();
            valores.put(colunas[0], obj.getNumNota());
            valores.put(colunas[1], obj.getProdutos());
            valores.put(colunas[2], obj.getVALORNOTA());

            return baseDados.insert(tabela, null, valores);

        }catch (SQLException ex){
            Log.e("PDV", "ERRO: NotaDeVendaDao.insert() "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public long update(NotaDeVenda obj) {
        try{
            ContentValues valores = new ContentValues();
            valores.put(colunas[1], obj.getProdutos());
            valores.put(colunas[2], obj.getVALORNOTA());

            String[]identificador = {String.valueOf(obj.getNumNota())};

            return baseDados.update(tabela,  valores,
                    colunas[0]+"= ?", identificador);

        }catch (SQLException ex){
            Log.e("PDV", "ERRO: NotaDeVendaDao.update() "+ex.getMessage());
        }
        return 0;
    }
    public long UpdateDirecionado(NotaDeVenda obj)
    {
        try{
            baseDados.execSQL("UPDATE "+tabela+" SET PRODUTOS = PRODUTOS || '\n"+ obj.getProdutos()+"' , VALORNOTA = VALORNOTA + "+obj.getVALORNOTA()+" WHERE NUMNOTA = "+obj.getNumNota());
        }catch (SQLException ex){
            Log.e("PDV", "Erro ao atualizar: NotadeVendaDao.UpdateDirecionado: "+ex.getMessage()+" "+ex.getStackTrace());
        }
        return 0;
    }
    @Override
    public long delete(NotaDeVenda obj) {
        try{
            String[]identificador = {String.valueOf(obj.getNumNota())};

            return baseDados.delete(tabela,
                    colunas[0]+"= ?", identificador);
        }catch (SQLException ex){
            Log.e("PDV", "ERRO: NotaDeVendaDao.delete() "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public ArrayList<NotaDeVenda> getAll() {
        ArrayList<NotaDeVenda> lista = new ArrayList<>();
        try{
            Cursor cursor = baseDados.query(tabela,
                    colunas, null,
                    null, null,
                    null, colunas[0]+" desc");

            if(cursor.moveToFirst()){
                do{
                    NotaDeVenda nota = new NotaDeVenda();
                    nota.setNumNota(cursor.getInt(0));
                    nota.setProdutos(cursor.getString(1));
                    nota.setVALORNOTA(cursor.getDouble(2));
                    lista.add(nota);

                }while (cursor.moveToNext());
            }

        }catch (SQLException ex){
            Log.e("PDV", "ERRO: NotaDeVendaDao.getAll() "+ex.getMessage());
        }

        return lista;
    }

    @Override
    public NotaDeVenda getById(int id) {
        try{
            String[]identificador = {String.valueOf(id)};
            Cursor cursor = baseDados.query(tabela, colunas,
                    colunas[0]+"= ?", identificador,
                    null, null, null);

            if(cursor.moveToFirst()){
                NotaDeVenda nota = new NotaDeVenda();
                nota.setNumNota(cursor.getInt(0));
                nota.setProdutos(cursor.getString(1));
                nota.setVALORNOTA(cursor.getDouble(2));
                return nota;
            }

        }catch (SQLException ex){
            Log.e("PDV", "ERRO: NotaDeVendaDao.getById() "+ex.getMessage());
        }
        return null;
    }
}
