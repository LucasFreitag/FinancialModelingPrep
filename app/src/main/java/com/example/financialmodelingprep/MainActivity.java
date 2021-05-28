package com.example.financialmodelingprep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.financialmodelingprep.api.ICompanyQuoteService;
import com.example.financialmodelingprep.helper.Adapter;
import com.example.financialmodelingprep.helper.RecyclerItemClickListener;
import com.example.financialmodelingprep.model.CompanyQuote;
import com.example.financialmodelingprep.model.Quote;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private TextView tvNome, tvSimbolo, tvPreco, tvAltaDia;
    private EditText etSimbolo;
    private View lytRecyler;

    private RecyclerView recyclerView;
    private Adapter adapter;
    private List<Quote> lstQuotes;

    private static final String API_KEY = "070c135253f538108a872767cc70ee21";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvNome = findViewById(R.id.tvNome);
        tvSimbolo = findViewById(R.id.tvSimbolo);
        tvPreco = findViewById(R.id.tvPreco);
        tvAltaDia = findViewById(R.id.tvAltaDia);
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
                Toast.makeText(MainActivity.this, "Item pressionado " + quote.getNome(), Toast.LENGTH_LONG).show();
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
    }

    private void consultaSimbolo(String simbolo) {
        ICompanyQuoteService companyQuoteService = retrofit.create(ICompanyQuoteService.class);
        Call<List<CompanyQuote>> call = companyQuoteService.consultarCotacao(simbolo, API_KEY);

        call.enqueue(new Callback<List<CompanyQuote>>() {
            @Override
            public void onResponse(Call<List<CompanyQuote>> call, Response<List<CompanyQuote>> response) {
                if (response.isSuccessful()) {
                    List<CompanyQuote> obj = response.body();
                    if (obj != null && obj.size() > 0) {
                        lytRecyler.setVisibility(View.INVISIBLE);
                        tvNome.setVisibility(View.VISIBLE);
                        tvSimbolo.setVisibility(View.VISIBLE);
                        tvPreco.setVisibility(View.VISIBLE);
                        tvAltaDia.setVisibility(View.VISIBLE);

                        tvNome.setText("Nome: " + obj.get(0).getNome());
                        tvSimbolo.setText("Símbolo: " + obj.get(0).getSimbolo());
                        tvPreco.setText("Preço: " + obj.get(0).getPreco());
                        tvAltaDia.setText("Alta do dia: " + obj.get(0).getAltaDia());
                    } else
                        Toast.makeText(MainActivity.this, "Cotação consultada não encontrada.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<CompanyQuote>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Não foi possível consultar a cotação.\n" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void consultaTodosSimbolos() {
        ICompanyQuoteService companyQuoteService = retrofit.create(ICompanyQuoteService.class);
        Call<List<Quote>> call = companyQuoteService.obterTodosSimbolos(API_KEY);

        call.enqueue(new Callback<List<Quote>>() {
            @Override
            public void onResponse(Call<List<Quote>> call, Response<List<Quote>> response) {
                if (response.isSuccessful()) {
                    lstQuotes = response.body();

                    tvNome.setVisibility(View.INVISIBLE);
                    tvSimbolo.setVisibility(View.INVISIBLE);
                    tvPreco.setVisibility(View.INVISIBLE);
                    tvAltaDia.setVisibility(View.INVISIBLE);

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
            } else
                consultaSimbolo(symbol);
        } catch (Exception ex) {
            Toast.makeText(this, "Ocorreu um erro durante a solicitação.\n" + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }


}



