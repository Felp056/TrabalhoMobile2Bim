package com.example.trabalhomobile2bim.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.trabalhomobile2bim.Banco.SqLiteDataHelper;
import com.example.trabalhomobile2bim.Objetos.Usuario;

import java.util.ArrayList;

public class LogInDao implements GenericDao<Usuario>{

    private SQLiteOpenHelper openHelper;

    //Base de Dados
    private SQLiteDatabase baseDados;

    //nome das colunas da tabela;
    private String[]colunas = {"MATRICULA", "NOME", "SOBRENOME", "SENHA"};

    //nome da tabela
    private String tabela = "USUARIO";

    //Contexto (view)
    private Context context;

    private static LogInDao instancia;

    public static LogInDao getInstancia(Context context){
        if(instancia == null){
            return instancia = new LogInDao(context);
        }else{
            return instancia;
        }
    }

    private LogInDao(Context context){
        this.context = context;

        //Abrir a conexão com a base de dados
        openHelper = new SqLiteDataHelper(this.context,
                "PDV_BD", null, 1);

        //instanciando a base de dados
        baseDados = openHelper.getWritableDatabase();


    }
    public long insert(Usuario obj) {
        try{
            ContentValues valores = new ContentValues();
            valores.put(colunas[0], obj.getMatricula());
            valores.put(colunas[1], obj.getNome());
            valores.put(colunas[2], obj.getSobreNome());
            valores.put(colunas[3], obj.getSenha());

            return baseDados.insert(tabela, null, valores);

        }catch (SQLException ex){
            Log.e("PDV", "ERRO: UserDao.insert() "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public long update(Usuario obj) {
        try{
            ContentValues valores = new ContentValues();
            valores.put(colunas[1], obj.getNome());
            valores.put(colunas[2], obj.getSobreNome());

            String[]identificador = {String.valueOf(obj.getMatricula())};

            return baseDados.update(tabela,  valores,
                    colunas[0]+"= ?", identificador);

        }catch (SQLException ex){
            Log.e("PDV", "ERRO: UserDao.update() "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public long delete(Usuario obj) {
        try{
            String[]identificador = {String.valueOf(obj.getMatricula())};

            return baseDados.delete(tabela,
                    colunas[0]+"= ?", identificador);
        }catch (SQLException ex){
            Log.e("PDV", "ERRO: AlunoDao.delete() "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public ArrayList<Usuario> getAll() {
        ArrayList<Usuario> lista = new ArrayList<>();
        try{
            Cursor cursor = baseDados.query(tabela,
                    colunas, null,
                    null, null,
                    null, colunas[0]+" desc");

            if(cursor.moveToFirst()){
                do{
                    Usuario user = new Usuario();
                    user.setMatricula(cursor.getInt(0));
                    user.setNome(cursor.getString(1));
                    user.setSobreNome(cursor.getString(2));
                    lista.add(user);

                }while (cursor.moveToNext());
            }

        }catch (SQLException ex){
            Log.e("PDV", "ERRO: UserDao.getAll() "+ex.getMessage());
        }

        return lista;
    }

    @Override
    public Usuario getById(int id) {
        try{
            String[]identificador = {String.valueOf(id)};
            Cursor cursor = baseDados.query(tabela, colunas,
                    colunas[0]+"= ?", identificador,
                    null, null, null);

            if(cursor.moveToFirst()){
                Usuario aluno = new Usuario();
                aluno.setMatricula(cursor.getInt(0));
                aluno.setNome(cursor.getString(1));
                aluno.setSobreNome(cursor.getString(2));

                return aluno;
            }

        }catch (SQLException ex){
            Log.e("PDV", "ERRO: AlunoDao.getById() "+ex.getMessage());
        }
        return null;
    }
    public Usuario getById(int matricula, String senha) {
        try{
            String[]identificador = {String.valueOf(matricula), senha};
            Cursor cursor = baseDados.query(tabela, colunas,
                    colunas[0]+"= ? AND "+colunas[3]+" = ?", identificador,
                    null, null, null);

            if(cursor.moveToFirst()){
                Usuario aluno = new Usuario();
                aluno.setMatricula(cursor.getInt(0));
                aluno.setNome(cursor.getString(1));
                aluno.setSobreNome(cursor.getString(2));

                return aluno;
            }

        }catch (SQLException ex){
            Log.e("PDV", "ERRO: AlunoDao.getById() "+ex.getMessage());
        }
        return null;
    }
}
