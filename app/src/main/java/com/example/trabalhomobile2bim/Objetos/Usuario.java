package com.example.trabalhomobile2bim.Objetos;

public class Usuario {
    private String Nome;
    private String SobreNome;
    private int Matricula;
    private String Senha;

    public String getSenha() {return Senha; }

    public void setSenha(String senha) {Senha = senha;}

    public String getNome() {return Nome;    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getSobreNome() {
        return SobreNome;
    }

    public void setSobreNome(String sobreNome) {
        SobreNome = sobreNome;
    }

    public int getMatricula() {
        return Matricula;
    }

    public void setMatricula(int matricula) {
        Matricula = matricula;
    }
}
