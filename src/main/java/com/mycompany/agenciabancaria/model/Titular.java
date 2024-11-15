package com.mycompany.agenciabancaria.model;

import java.util.ArrayList;
import java.util.List;

public class Titular {
    private String nome;
    private String cpf;
    private String senha;
    private List<Conta> contas;
    
    public Titular (String nome, String cpf, String senha){
     this.nome = nome; 
     this.cpf = cpf;
     this.senha =  senha;
     this.contas = new ArrayList<>();
    }
    
    public String getNome()
    {
        return nome;
    }
    public String getCPF()
    {
        return cpf;
    }
    public String getSenha ()
    {
        return senha;
    }
    public List<Conta> getContas()
    {
        return contas;
    }
    public void AdicionarConta(Conta conta)
    {
        contas.add(conta);
    }
}