package com.example.financialmodelingprep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.financialmodelingprep.api.ICompanyQuoteService;
import com.example.financialmodelingprep.helper.Adapter;
import com.example.financialmodelingprep.model.CompanyQuote;
import com.example.financialmodelingprep.model.Quote;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuotesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Adapter adapter;
    private Retrofit retrofit;
    private List<Quote> lstQuotes;
    private static final String API_KEY = "070c135253f538108a872767cc70ee21";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);

        lstQuotes = new ArrayList<Quote>();
        recyclerView = findViewById(R.id.recyclerView);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://financialmodelingprep.com/api/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ConsultarSimbolos();

        adapter = new Adapter(lstQuotes);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapter);

    }


    private void ConsultarSimbolos() {

        ICompanyQuoteService companyQuoteService = retrofit.create(ICompanyQuoteService.class);
        Call<ResponseBody> call = companyQuoteService.obterTodosSimbolos(API_KEY);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("SUCESSO", t.getMessage());
            }
        });

    }
}