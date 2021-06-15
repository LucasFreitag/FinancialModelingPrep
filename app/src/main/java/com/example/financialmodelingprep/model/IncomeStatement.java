package com.example.financialmodelingprep.model;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IncomeStatement {

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

    @SerializedName("date")
    private String data;
    @SerializedName("symbol")
    private String nome;
    @SerializedName("fillingDate")
    private  String dataFilling;
    @SerializedName("acceptedDate")
    private  String dataAceita;
    @SerializedName("period")
    private  String periodo;
    @SerializedName("revenue")
    private  long receita;

    private transient String receitaEDataFormatada;

    public String getReceitaEDataFormatada() {
        try {
            return getReceita() + " - " + sdf.format(sdf1.parse(data));
        } catch (ParseException e) {
            return getReceita()+"";
        }
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataFilling() {
        return dataFilling;
    }

    public void setDataFilling(String dataFilling) {
        this.dataFilling = dataFilling;
    }

    public String getDataAceita() {
        return dataAceita;
    }

    public void setDataAceita(String dataAceita) {
        this.dataAceita = dataAceita;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public long getReceita() {
        return receita / 1000000000;
    }

    public void setReceita(long receita) {
        this.receita = receita;
    }
}
