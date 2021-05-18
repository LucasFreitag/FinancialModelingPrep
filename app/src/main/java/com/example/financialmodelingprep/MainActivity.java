package com.example.financialmodelingprep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.financialmodelingprep.api.CepService;
import com.example.financialmodelingprep.model.Cep;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://viacep.com.br/ws/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public void btTesteClick(View view) {

        CepService cepService = retrofit.create(CepService.class);
        Call<Cep> call = cepService.consultaCep();
        Log.d("TAG", "antes do queue");
        call.enqueue(new Callback<Cep>() {
            @Override
            public void onResponse(Call<Cep> call, Response<Cep> response) {
                if(response.isSuccessful()){
                    Log.d("TAG", "Deu certo");
                    Cep obj = response.body();
                    Toast.makeText(MainActivity.this, "Localidade" + obj.getLocalidade() + "\nDDD: " + obj.getDdd(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Cep> call, Throwable t)
            {

                Log.d("TAG", "Erro\n"+t.getMessage());
            }
        });

    }
}