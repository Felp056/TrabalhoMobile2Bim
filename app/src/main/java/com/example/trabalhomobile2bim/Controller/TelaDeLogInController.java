package com.example.trabalhomobile2bim.Controller;

import android.content.Context;
import android.database.SQLException;
import com.example.trabalhomobile2bim.DAO.LogInDao;
import com.example.trabalhomobile2bim.Objetos.Usuario;
public class TelaDeLogInController {
    private Context context;
    public TelaDeLogInController(Context context) {
        this.context = context;
    }

    public String LogIn(int Matricula, String senha){
        try {
            if(senha.isEmpty() || senha == ""){
                return "Preencha a senha para continuar";
            }
            Usuario user = LogInDao.getInstancia(context).getById(Matricula, senha);
            if(user == null){
                return "Senha ou matricula Incorretas";
            }
            return "";
        }catch (SQLException ex){
            return "Erro ao consultar cadastro "+ex.getMessage()+" \n"+ex.getStackTrace();
        }
    }
}
