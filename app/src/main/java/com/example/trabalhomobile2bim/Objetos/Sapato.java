package com.example.trabalhomobile2bim.Objetos;

public class Sapato {
    private String Nome;
    private Double Valor;
    private int Tamanho;
    private String Tipo;
    private int identificador;

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }
    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public Double getValor() {
        return Valor;
    }

    public void setValor(Double valor) {
        Valor = valor;
    }

    public int getTamanho() {
        return Tamanho;
    }

    public void setTamanho(int tamanho) {
        Tamanho = tamanho;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }
}
