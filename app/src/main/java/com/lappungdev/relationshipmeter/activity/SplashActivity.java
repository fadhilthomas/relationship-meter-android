package com.lappungdev.relationshipmeter.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.transition.TransitionManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lappungdev.relationshipmeter.R;

public class SplashActivity extends AppCompatActivity {

    private ImageView tvJudul;
    private TextView tvSubJudul;
    private ViewGroup transContainer;
    private boolean visibleJudul;
    private boolean visibleSubJudul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        transContainer = findViewById(R.id.transContainer);
        tvJudul = transContainer.findViewById(R.id.tvJudul);
        tvSubJudul = transContainer.findViewById(R.id.tvSubJudul);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                TransitionManager.beginDelayedTransition(transContainer);
                visibleJudul = !visibleJudul;
                tvJudul.setVisibility(visibleJudul ? View.VISIBLE : View.GONE);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        TransitionManager.beginDelayedTransition(transContainer);
                        visibleSubJudul = !visibleSubJudul;
                        tvSubJudul.setVisibility(visibleSubJudul ? View.VISIBLE : View.GONE);
                    }
                },1000);
            }
        },1000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        },3000);

    }
}
