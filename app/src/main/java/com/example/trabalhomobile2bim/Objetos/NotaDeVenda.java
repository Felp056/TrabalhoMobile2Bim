package com.example.trabalhomobile2bim.Objetos;

import java.util.ArrayList;

public class NotaDeVenda {
    private int NumNota;
    private String produtos;
    private double VALORNOTA;

    public double getVALORNOTA() {
        return VALORNOTA;
    }

    public void setVALORNOTA(double VALORNOTA) {
        this.VALORNOTA = VALORNOTA;
    }

    public int getNumNota() {
        return NumNota;
    }

    public void setNumNota(int numNota) {
        NumNota = numNota;
    }

    public String getProdutos() {
        return produtos;
    }

    public void setProdutos(String produtos) {
        this.produtos = produtos;
    }

}
