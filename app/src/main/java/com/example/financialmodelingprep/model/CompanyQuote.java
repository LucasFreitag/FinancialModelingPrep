package com.example.financialmodelingprep.model;

import com.google.gson.annotations.SerializedName;

public class CompanyQuote {

    @SerializedName(value = "symbol")
    private String simbolo;
    @SerializedName(value = "name")
    private String nome;
    @SerializedName(value = "price")
    private double preco;
    @SerializedName(value = "changesPercentage")
    private double porcentagemMudanca;
    private double change;
    @SerializedName(value = "dayLow")
    private double baixaDia;
    @SerializedName(value = "dayHigh")
    private double altaDia;
    @SerializedName(value = "yearHigh")
    private double altaAno;
    @SerializedName(value = "yearLow")
    private double baixaAno;
    @SerializedName(value = "marketCap")
    private double valorDeMercado;
    private double priceAvg50;
    private double priceAvg200;
    private double volume;
    private double avgVolume;
    @SerializedName(value = "exchange")
    private String intercambio;
    @SerializedName(value = "open")
    private double ValorAbertura;
    @SerializedName(value = "previousClose")
    private double previsaoFechamento;
    private double eps;
    private double pe;

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

    public double getPorcentagemMudanca() {
        return porcentagemMudanca;
    }

    public void setPorcentagemMudanca(double porcentagemMudanca) {
        this.porcentagemMudanca = porcentagemMudanca;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public double getBaixaDia() {
        return baixaDia;
    }

    public void setBaixaDia(double baixaDia) {
        this.baixaDia = baixaDia;
    }

    public double getAltaDia() {
        return altaDia;
    }

    public void setAltaDia(double altaDia) {
        this.altaDia = altaDia;
    }

    public double getAltaAno() {
        return altaAno;
    }

    public void setAltaAno(double altaAno) {
        this.altaAno = altaAno;
    }

    public double getBaixaAno() {
        return baixaAno;
    }

    public void setBaixaAno(double baixaAno) {
        this.baixaAno = baixaAno;
    }

    public double getValorDeMercado() {
        return valorDeMercado;
    }

    public void setValorDeMercado(double valorDeMercado) {
        this.valorDeMercado = valorDeMercado;
    }

    public double getPriceAvg50() {
        return priceAvg50;
    }

    public void setPriceAvg50(double priceAvg50) {
        this.priceAvg50 = priceAvg50;
    }

    public double getPriceAvg200() {
        return priceAvg200;
    }

    public void setPriceAvg200(double priceAvg200) {
        this.priceAvg200 = priceAvg200;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getAvgVolume() {
        return avgVolume;
    }

    public void setAvgVolume(double avgVolume) {
        this.avgVolume = avgVolume;
    }

    public String getIntercambio() {
        return intercambio;
    }

    public void setIntercambio(String intercambio) {
        this.intercambio = intercambio;
    }

    public double getValorAbertura() {
        return ValorAbertura;
    }

    public void setValorAbertura(double valorAbertura) {
        ValorAbertura = valorAbertura;
    }

    public double getPrevisaoFechamento() {
        return previsaoFechamento;
    }

    public void setPrevisaoFechamento(double previsaoFechamento) {
        this.previsaoFechamento = previsaoFechamento;
    }

    public double getEps() {
        return eps;
    }

    public void setEps(double eps) {
        this.eps = eps;
    }

    public double getPe() {
        return pe;
    }

    public void setPe(double pe) {
        this.pe = pe;
    }
}