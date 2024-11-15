package com.mycompany.agenciabancaria.model;

public class ContaCorrente extends Conta {

    public ContaCorrente(int numero, double saldoInicial, Titular dono) 
    {
        super(numero, saldoInicial, dono); // classe concreta chama o construtor da classe abstrata
    }

    @Override
    public String getTipo() 
    {
        return "Corrente";
    }
    
}
