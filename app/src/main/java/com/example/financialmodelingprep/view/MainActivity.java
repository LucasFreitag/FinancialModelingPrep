package com.example.financialmodelingprep.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.financialmodelingprep.R;
import com.example.financialmodelingprep.api.ICompanyQuoteService;
import com.example.financialmodelingprep.helper.Adapter;
import com.example.financialmodelingprep.helper.RecyclerItemClickListener;
import com.example.financialmodelingprep.model.CompanyQuote;
import com.example.financialmodelingprep.model.Quote;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private EditText etSimbolo;
    private View lytRecyler;

    private RecyclerView recyclerView;
    private Adapter adapter;
    private List<Quote> lstQuotes;

    public static final String API_KEY = "070c135253f538108a872767cc70ee21";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSimbolo = findViewById(R.id.etSimbolo);
        lytRecyler = findViewById(R.id.lytRecyler);
        recyclerView = findViewById(R.id.recyclerView);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://financialmodelingprep.com/api/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (lstQuotes == null || lstQuotes.size() == 0) return;

                Quote quote = lstQuotes.get(position);
                Intent intent = new Intent(MainActivity.this, QuoteActivity.class);
                String simbolo = quote.getSimbolo();
                intent.putExtra("simbolo", simbolo);

                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {
                if (lstQuotes == null || lstQuotes.size() == 0) return;

                Quote quote = lstQuotes.get(position);
                Toast.makeText(MainActivity.this, "Item longo " + quote.getNome(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        }));

        consultaTodosSimbolos();
    }

    private void consultaTodosSimbolos() {
        ICompanyQuoteService companyQuoteService = retrofit.create(ICompanyQuoteService.class);
        Call<List<Quote>> call = companyQuoteService.obterTodosSimbolos(API_KEY);

        call.enqueue(new Callback<List<Quote>>() {
            @Override
            public void onResponse(Call<List<Quote>> call, Response<List<Quote>> response) {
                if (response.isSuccessful()) {
                    lstQuotes = response.body();

                    adapter = new Adapter(lstQuotes);

                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
                    recyclerView.setAdapter(adapter);

                    lytRecyler.setVisibility(View.VISIBLE);
                } else
                    Toast.makeText(MainActivity.this, "Erro ao consultar", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Quote>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Não foi possível consultar lista de simbolos.\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void btConsultarClick(View view) {
        try {
            String symbol = etSimbolo.getText().toString();
            if (symbol.isEmpty()) {
                consultaTodosSimbolos();
            } else {
                Intent intent = new Intent(MainActivity.this, QuoteActivity.class);
                intent.putExtra("simbolo", symbol);
                startActivity(intent);
            }
        } catch (Exception ex) {
            Toast.makeText(this, "Ocorreu um erro durante a solicitação.\n" + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}



