package com.mycompany.agenciabancaria.controller;

import com.mycompany.agenciabancaria.model.Conta;
import com.mycompany.agenciabancaria.model.ContaCorrente;
import com.mycompany.agenciabancaria.model.ContaPoupanca;
import com.mycompany.agenciabancaria.model.Titular;
import java.util.ArrayList;
import java.util.List;

public class AgenciaController 
{
    private List<Titular> usuarios = new ArrayList();
    private Titular usuarioLogado;
    
    public boolean cadastrarUsuario (String nome, String cpf, String senha)
    {
        for ( Titular titular : usuarios)
        {
            if(titular.getCPF().equals(cpf))
            {
                return false;
            }
        }
        usuarios.add(new Titular(nome, cpf, senha));
        return true;
    }
    public Titular autenticarUsuario(String cpf, String senha)
    {
        for(Titular usuario: usuarios)
        {
            if(usuario.getCPF().equals(cpf) && usuario.getSenha().equals(senha))
            {
                usuarioLogado = usuario;
                return usuarioLogado;
            }
        }
        return null;
    }
    public Titular getUsuarioLogado()
    {
        return usuarioLogado;
    }
    
    public double getSaldo(int numeroConta)
    {
            
        for (Conta conta : usuarioLogado.getContas())
        {
            if(conta.getNumero() == numeroConta)
            {
             return conta.getSaldo();
            }
        }
        return -1;
    }
    public boolean depositar(double valor, int numeroConta)
    {
        for(Conta conta : usuarioLogado.getContas())
        {
            if(conta.getNumero() == numeroConta)
            {
                conta.Depositar(valor);
                return true;
            }
        }
        return false;
    }
    public boolean sacar(double valor, int numeroConta)
    {
        for(Conta conta : usuarioLogado.getContas())
        {
            if(conta.getNumero() == numeroConta && valor <=conta.getSaldo())
            {
                conta.Sacar(valor);
                return true;
            }
        }
        return false;
    }
    public boolean transferir(double valor, int contaRemetente, int contaDestinataria)
    {
    Conta contaOrigem = null;
    Conta contaDestino = null;
    
        for(Conta conta : usuarioLogado.getContas()) //procurando a primeira conta na lista de contas do usuário
        {
            if(conta.getNumero() == contaRemetente )
            {
                contaOrigem = conta;
                break;
            }
        }
        for(Titular usuario : usuarios) // procurando a conta destino na lista de contas dos usuários da aplicação
        {
            for(Conta conta : usuario.getContas())
            {
                if(conta.getNumero() == contaDestinataria )
                {
                    contaDestino = conta;
                    break;
                }
            }
            if(contaDestino != null)
            {
                break;
            }
        }
        if(contaOrigem != null && contaDestino != null && contaOrigem.getSaldo() >= valor)
        {
            contaOrigem.transferir(valor, contaDestino);
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean criarNovaConta(int numero, double saldoInicial, int tipo)
    {
        if(usuarioLogado != null)
        {
            Conta novaConta = null;
            boolean exist = false;
            
            for( Conta conta : usuarioLogado.getContas())
            {
                if(conta.getNumero() == numero)
                {
                    exist = true;
                    break;
                }
            }
            if(tipo == 1 && !exist)
            {
                novaConta = new ContaCorrente(numero, saldoInicial, usuarioLogado);
            }
            else if(tipo == 2 && !exist)
            {
                novaConta = new ContaPoupanca(numero, saldoInicial, usuarioLogado); // porque dá erro se a classe abstrata está importando
            }
            
            usuarioLogado.AdicionarConta(novaConta);
            return true;
        }
        else
        {
            return false;

        }
    }  
}
