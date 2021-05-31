package com.example.financialmodelingprep.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.financialmodelingprep.R;
import com.example.financialmodelingprep.api.ICompanyQuoteService;
import com.example.financialmodelingprep.model.CompanyQuote;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuoteActivity extends AppCompatActivity {

    TextView tvSimboloQuote, tvNomeQuote, tvPrecoQuote, tvPercMudancaQuote, tvBaixaDiaQuote, tvAltaDiaQuote, tvBaixaAnoQuote, tvAltaAnoQuote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);

        tvSimboloQuote = findViewById(R.id.tvSimboloQuote);
        tvNomeQuote = findViewById(R.id.tvNomeQuote);
        tvPrecoQuote = findViewById(R.id.tvPrecoQuote);
        tvPercMudancaQuote = findViewById(R.id.tvPercMudancaQuote);
        tvBaixaDiaQuote = findViewById(R.id.tvBaixaDiaQuote);
        tvAltaDiaQuote = findViewById(R.id.tvAltaDiaQuote);
        tvBaixaAnoQuote = findViewById(R.id.tvBaixaAnoQuote);
        tvAltaAnoQuote = findViewById(R.id.tvAltaAnoQuote);

        Bundle dados = getIntent().getExtras();
        String simbolo = dados.getString("simbolo");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://financialmodelingprep.com/api/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        if (simbolo.isEmpty())
            finish();
        else {
            try {
                consultaSimbolo(simbolo, retrofit);
            } catch (Exception ex) {
                Toast.makeText(this, "Erro ao consultar cotação.\n" + ex.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void consultaSimbolo(String simbolo, Retrofit retrofit) {
        ICompanyQuoteService companyQuoteService = retrofit.create(ICompanyQuoteService.class);
        Call<List<CompanyQuote>> call = companyQuoteService.consultarCotacao(simbolo, MainActivity.API_KEY);

        call.enqueue(new Callback<List<CompanyQuote>>() {
            @Override
            public void onResponse(Call<List<CompanyQuote>> call, Response<List<CompanyQuote>> response) {
                if (response.isSuccessful()) {
                    List<CompanyQuote> obj = response.body();
                    if (obj != null && obj.size() > 0) {

                        tvNomeQuote.setText("Nome: " + obj.get(0).getNome());
                        tvSimboloQuote.setText(obj.get(0).getSimbolo());
                        tvPrecoQuote.setText("Preço: " + obj.get(0).getPreco());
                        tvAltaDiaQuote.setText("Alta do dia: " + obj.get(0).getAltaDia());
                        tvPercMudancaQuote.setText("% Mudança: " + obj.get(0).getPorcentagemMudanca());
                        tvBaixaDiaQuote.setText("Baixa do dia: " + obj.get(0).getBaixaDia());
                        tvAltaAnoQuote.setText("Alta do ano: " + obj.get(0).getAltaAno());
                        tvBaixaAnoQuote.setText("Baixa do ano: " + obj.get(0).getBaixaAno());
                    } else
                        Toast.makeText(QuoteActivity.this, "Cotação consultada não encontrada.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<CompanyQuote>> call, Throwable t) {
                Toast.makeText(QuoteActivity.this, "Não foi possível consultar a cotação.\n" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}