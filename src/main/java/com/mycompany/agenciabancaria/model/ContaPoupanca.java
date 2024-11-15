package com.mycompany.agenciabancaria.model;

public class ContaPoupanca extends Conta 
{
    public ContaPoupanca (int numero, double saldoInicial, Titular dono)
    {
        super(numero, saldoInicial, dono);// classe concreta chama o construtor da classe abstrata
    }
    
    @Override
    public String getTipo()
    {
        return "Poupança";
    }
}
