package com.example.trabalhomobile2bim.Controller;

import android.content.Context;
import android.database.SQLException;

import com.example.trabalhomobile2bim.DAO.NotaDeVendaDao;
import com.example.trabalhomobile2bim.DAO.SapatoDao;
import com.example.trabalhomobile2bim.Objetos.NotaDeVenda;
import com.example.trabalhomobile2bim.Objetos.Sapato;

import java.util.ArrayList;

public class SelecaoDeProdutoController {
    private Context context;
    private int IdDaCompra;
    public SelecaoDeProdutoController(Context context) {
        this.context = context;
    }

    public void SetIdCompra(int pIdCompra){
        this.IdDaCompra = pIdCompra;
    }
    public String AdicionarALista ( String sapato, String TipoSapato, int Tamanho, double valor){
        try{
            NotaDeVenda nfe = new NotaDeVenda();
            nfe.setNumNota(this.IdDaCompra);
            nfe.setVALORNOTA(valor);
            nfe.setProdutos(TipoSapato+" "+sapato+ " tamanho:"+ Tamanho);
            NotaDeVenda nfeDao = NotaDeVendaDao.getInstancia(context).getById(nfe.getNumNota());
            if(nfeDao == null){
                NotaDeVendaDao.getInstancia(context).insert(nfe);
            }else{
                NotaDeVendaDao.getInstancia(context).UpdateDirecionado(nfe);
            }
        }catch (SQLException ex){
            return "Erro : Problemas ao gravar a nota de compra: "+ex.getMessage()+" "+ex.getStackTrace();
        }
        return "";
    }
    public int NovaVenda()
    {
        ArrayList<NotaDeVenda> nfe = NotaDeVendaDao.getInstancia(context).getAll();
        return nfe.size();
    }

    public int NumeroDaVenda(){
        ArrayList<NotaDeVenda> nfe = NotaDeVendaDao.getInstancia(context).getAll();
        {
            return nfe.size() - 1;
        }

    }
    public ArrayList<Sapato> getAll(){
        return SapatoDao.getInstancia(context).getAll();
    }
}
