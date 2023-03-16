package com.tong.jmc0228;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class jiaoshi extends AppCompatActivity implements View.OnClickListener{
    private Button meiridakajilu0;
    private Button guanjianzichaxun0;
    private Button zongjiechaxun0;
    private Button meirizongjiejilu0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiaoshi);
        meiridakajilu0=findViewById(R.id.meiridakajilu);
        meiridakajilu0.setOnClickListener(this);
        guanjianzichaxun0=findViewById(R.id.guanjianzichaxun);
        guanjianzichaxun0.setOnClickListener(this);
        zongjiechaxun0=findViewById(R.id.zongjiechaxun);
        zongjiechaxun0.setOnClickListener(this);
        meirizongjiejilu0=findViewById(R.id.meirizongjiejilu);
        meirizongjiejilu0.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.meiridakajilu:
                startActivity(new Intent(this, meiridakajilu.class));
                //finish();
                break;
            case R.id.guanjianzichaxun:
                startActivity(new Intent(this, guanjianzichaxun.class));
                //finish();
                break;
            case R.id.zongjiechaxun:
                startActivity(new Intent(this, zongjiechaxun.class));
                //finish();
                break;
            case R.id.meirizongjiejilu:
                startActivity(new Intent(this, meirizongjiejilu.class));
                //finish();
                break;
        }
    }
}