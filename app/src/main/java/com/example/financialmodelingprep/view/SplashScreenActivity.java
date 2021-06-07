package com.example.financialmodelingprep.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.financialmodelingprep.R;
import com.example.financialmodelingprep.helper.Preference;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        final Preference preference = new Preference(this);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (preference.recuperarPreferencia()) {
                    startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                    finish();
                } else {
                    preference.salvarPreferencia(true);
                    startActivity(new Intent(SplashScreenActivity.this, IntroInfoActivity.class));
                    finish();
                }
            }
        }, 2000);

    }
}