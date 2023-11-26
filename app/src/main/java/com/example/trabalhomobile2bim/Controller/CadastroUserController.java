package com.example.trabalhomobile2bim.Controller;

import android.content.Context;
import android.database.SQLException;

import com.example.trabalhomobile2bim.DAO.LogInDao;
import com.example.trabalhomobile2bim.Objetos.Usuario;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class CadastroUserController {
    private Context context;
    public CadastroUserController(Context context) {
        this.context = context;
    }

    public String CadastrarUsuario(int matricula, String Nome, String Sobrenome, String Senha){
        try{
            if(matricula == 0){
                return "A matricula não pode ser 0";
            }
            if(Nome.isEmpty() || Nome == ""){
                return "Não pode deixar o nome em branco";
            }
            if(Sobrenome.isEmpty() || Sobrenome == ""){
                return "Não pode deixar o sobrenome em branco";
            }
            if(Senha.isEmpty() || Senha ==""){
                return "A Senha não pode ser nula, nem ter menos de 8 characteres";
            }else if(Senha.length() < 8){
                return "A senha não pode ser menor que 8 characteres";
            }
            Usuario user = LogInDao.getInstancia(context).getById(matricula);
            if(user == null){
                user = new Usuario();
                user.setNome(Nome);
                user.setSobreNome(Sobrenome);
                user.setSenha(Senha);
                user.setMatricula(matricula);
                LogInDao.getInstancia(context).insert(user);
            }else{
                return "Usuario já existente";
            }
            return "";
        }catch (SQLException ex){
            return "Erro ao gravar dados no banco: "+ex.getMessage()+" \n"+ex.getStackTrace();
        }
    }
    public int GetMatricula(){
        ArrayList<Usuario> user = LogInDao.getInstancia(context).getAll();
        if(user != null){
            return user.size()+1;
        }else{
            return 1;
        }
    }
}
