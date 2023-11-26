package com.example.trabalhomobile2bim.Controller;

import android.content.Context;

import com.example.trabalhomobile2bim.DAO.NotaDeVendaDao;
import com.example.trabalhomobile2bim.Objetos.NotaDeVenda;

public class NotaController {
    private Context context;
    public NotaController(Context context) {
        this.context = context;
    }

    public String RetornaNotaPeloId(int Id) {
        NotaDeVenda nfe = NotaDeVendaDao.getInstancia(context).getById(Id);
        if(nfe != null) {
            return  (nfe.getProdutos() + " \n" +"Valor da Compra = "+ nfe.getVALORNOTA()+" R$");
        }else {
            return "Não há produtos na lista";
        }
    }
}
