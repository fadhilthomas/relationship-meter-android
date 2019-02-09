package com.lappungdev.relationshipmeter.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.transition.TransitionManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lappungdev.relationshipmeter.R;

public class MainActivity extends AppCompatActivity {

    ViewGroup transContainer;
    LinearLayout llMain, llRoom;
    Button btSave, btChange, btCancel, btPair;
    TextView tvName;
    EditText etName;
    ImageView ivLogoID;
    CardView cvCouple, cvFamiliy, cvFriend;
    String name = "";
    private boolean visible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadName();
        cvCouple = findViewById(R.id.cvCouple);
        cvFamiliy = findViewById(R.id.cvFamily);
        cvFriend = findViewById(R.id.cvFriend);
        cvCouple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoID(1);
            }
        });

        cvFamiliy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoID(2);
            }
        });

        cvFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoID(3);
            }
        });

        btCancel = findViewById(R.id.btCancel);
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMain();
            }
        });

        btPair = findViewById(R.id.btPair);
        btPair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoQuestion();
            }
        });
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Tekan sekali lagi untuk keluar", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    public void change(View view) {
        btChange = findViewById(R.id.btChange);
        btSave = findViewById(R.id.btSave);
        etName = findViewById(R.id.etName);
        tvName = findViewById(R.id.tvName);
        btChange.setVisibility(View.GONE);
        tvName.setVisibility(View.GONE);
        btSave.setVisibility(View.VISIBLE);
        etName.setVisibility(View.VISIBLE);
    }

    public void save(View view) {
        btChange = findViewById(R.id.btChange);
        btSave = findViewById(R.id.btSave);
        etName = findViewById(R.id.etName);
        tvName = findViewById(R.id.tvName);
        btChange.setVisibility(View.VISIBLE);
        tvName.setVisibility(View.VISIBLE);
        btSave.setVisibility(View.GONE);
        etName.setVisibility(View.GONE);
        saveName(etName.getText().toString());
        loadName();
    }

    private void saveName(String name){
        SharedPreferences sharedPref= getSharedPreferences("name", 0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("name", name);
        editor.apply();
    }

    private void loadName(){
        SharedPreferences sharedPref = getSharedPreferences("name", 0);
        name = sharedPref.getString("name","");
        tvName = findViewById(R.id.tvName);
        tvName.setText("Hi, " + name +  " ...");
    }

    public void gotoID(int id) {
        ivLogoID = findViewById(R.id.ivLogoID);
        llMain = findViewById(R.id.llMain);
        llRoom = findViewById(R.id.llRoom);
        transContainer = findViewById(R.id.transContainer);
        switch (id) {
            case 1:
                ivLogoID.setImageResource(R.drawable.couple);
                break;
            case 2:
                ivLogoID.setImageResource(R.drawable.maternity);
                break;
            case 3:
                ivLogoID.setImageResource(R.drawable.support);
                break;
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                TransitionManager.beginDelayedTransition(transContainer);
                visible = !visible;
                llMain.setVisibility(visible ? View.GONE : View.VISIBLE);
                llRoom.setVisibility(visible ? View.VISIBLE : View.GONE);
            }
        },500);
        visible = false;
    }

    public void gotoMain() {
        llMain = findViewById(R.id.llMain);
        llRoom = findViewById(R.id.llRoom);
        transContainer = findViewById(R.id.transContainer);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                TransitionManager.beginDelayedTransition(transContainer);
                visible = !visible;
                llMain.setVisibility(visible ? View.VISIBLE : View.GONE);
                llRoom.setVisibility(visible ? View.GONE : View.VISIBLE);
            }
        },500);
        visible = false;
    }

    public void gotoQuestion(){
        startActivity(new Intent(this, QuestionActivity.class));
    }
}
