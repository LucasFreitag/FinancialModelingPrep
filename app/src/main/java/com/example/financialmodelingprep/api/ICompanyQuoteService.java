package com.example.financialmodelingprep.api;

import com.example.financialmodelingprep.model.CompanyQuote;
import com.example.financialmodelingprep.model.Quote;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ICompanyQuoteService {

    @GET("quote/{symbol}")
    Call<List<CompanyQuote>> consultarCotacao(@Path("symbol") String simbolo, @Query("apikey") String apiKey);

    @GET("stock/list")
    Call<ResponseBody> obterTodosSimbolos(@Query("apiKey") String apiKey);
}
