package com.example.financialmodelingprep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.financialmodelingprep.api.ICompanyQuoteService;
import com.example.financialmodelingprep.model.CompanyQuote;

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

        retrofit = new Retrofit.Builder()
                .baseUrl("https://financialmodelingprep.com/api/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void btConsultarClick(View view) {

        String symbol = etSimbolo.getText().toString();
        if (symbol.isEmpty()) {
            Toast.makeText(this, "Informe o símbolo a ser consultado.", Toast.LENGTH_SHORT).show();
            etSimbolo.requestFocus();
            return;
        }

        ICompanyQuoteService companyQuoteService = retrofit.create(ICompanyQuoteService.class);
        Call<List<CompanyQuote>> call = companyQuoteService.consultarCotacao(symbol, API_KEY);

        call.enqueue(new Callback<List<CompanyQuote>>() {
            @Override
            public void onResponse(Call<List<CompanyQuote>> call, Response<List<CompanyQuote>> response) {
                if (response.isSuccessful()) {
                    List<CompanyQuote> obj = response.body();
                    tvNome.setText("Nome: " + obj.get(0).getNome());
                    tvSimbolo.setText("Símbolo: " + obj.get(0).getSimbolo());
                    tvPreco.setText("Preço: " + obj.get(0).getPreco());
                    tvAltaDia.setText("Alta do dia: " + obj.get(0).getAltaDia());
                }
            }

            @Override
            public void onFailure(Call<List<CompanyQuote>> call, Throwable t) {
                tvNome.setText("Não foi possível consultar a cotação.\n" + t.getMessage());
                System.out.println(t.getMessage());
            }
        });
    }

    


}



