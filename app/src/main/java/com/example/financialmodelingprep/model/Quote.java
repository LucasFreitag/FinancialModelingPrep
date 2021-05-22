package com.example.financialmodelingprep.model;

import com.google.gson.annotations.SerializedName;

public class Quote {

    @SerializedName(value = "symbol")
    private String simbolo;
    @SerializedName(value = "name")
    private  String nome;
    @SerializedName(value = "price")
    private double preco;
    private String exchange;

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }
}
