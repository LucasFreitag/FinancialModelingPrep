package com.example.financialmodelingprep.api;

import com.example.financialmodelingprep.model.Cep;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CepService {

    @GET("01001000/json/")
    Call<Cep> consultaCep();
}
