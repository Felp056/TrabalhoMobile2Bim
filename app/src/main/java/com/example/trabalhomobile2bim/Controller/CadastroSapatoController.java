package com.example.trabalhomobile2bim.Controller;

import android.content.Context;

import com.example.trabalhomobile2bim.DAO.SapatoDao;
import com.example.trabalhomobile2bim.Objetos.Sapato;

import java.util.ArrayList;

public class CadastroSapatoController {
        private Context context;
        public CadastroSapatoController(Context context) {
                this.context = context;
        }
        public String addSapato(String nomeSapato, int Tamanho, double valorSapato, String TipoSapato, int Id){
                try {
                        if (nomeSapato.isEmpty() || nomeSapato == "") {
                                return "O nome do sapato não pode ser vazio";
                        }
                        if (Tamanho == 0) {
                                return "O tamanho do sapato não pode ser 0";
                        }
                        if (valorSapato == 0.00) {
                                return "O valor do sapato deve ser diferente de 0";
                        }
                        if (TipoSapato.isEmpty() || TipoSapato == "") {
                                return "O tipo do sapato deve ser informado";
                        }
                        Sapato sapato = SapatoDao.getInstancia(context).getById(Id);
                        if (sapato == null) {
                                sapato = new Sapato();
                                sapato.setNome(nomeSapato);
                                sapato.setTamanho(Tamanho);
                                sapato.setValor(valorSapato);
                                sapato.setTipo(TipoSapato);
                                SapatoDao.getInstancia(context).insert(sapato);
                        }else {
                                return "Erro : Sapato já existente nesse Id";
                        }
                        return "";
                }catch (Exception ex){
                        return "Erro ao gravar novo sapato";
                }

        }
        public int IdSapato(){
                ArrayList<Sapato> id = SapatoDao.getInstancia(context).getAll();
                if(id != null && id.size() > 0){
                        return id.size() +1;
                }else {
                        return  1;
                }
        }
}
