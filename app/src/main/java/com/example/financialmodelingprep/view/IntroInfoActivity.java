package com.example.financialmodelingprep.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.financialmodelingprep.R;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;

public class IntroInfoActivity extends IntroActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setButtonBackVisible(false);
        setButtonNextVisible(false);


        addSlide(new FragmentSlide.Builder()
                .background(R.color.black)
                .backgroundDark(R.color.colorBtnStart)
                .fragment(R.layout.intro1_activity)
                .build()
        );

        addSlide(new FragmentSlide.Builder()
                .background(R.color.black)
                .backgroundDark(R.color.colorBtnStart)
                .fragment(R.layout.intro2_activity)
                .build()
        );

        addSlide(new FragmentSlide.Builder()
                .background(R.color.black)
                .backgroundDark(R.color.colorBtnStart)
                .fragment(R.layout.intro_start_activity)
                .canGoForward(false)
                .build()
        );
    }

    public void btnComecar(View view) {
        startActivity(new Intent(IntroInfoActivity.this, MainActivity.class));
        finish();
    }

}