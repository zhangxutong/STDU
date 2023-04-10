package com.example.underground;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnCheck1, btnCheck2, btnCheck3, btnCheck4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("地铁查询系统");
        btnCheck1 = findViewById(R.id.btn_check1);
        btnCheck2 = findViewById(R.id.btn_check2);
        btnCheck3 = findViewById(R.id.btn_check3);
        btnCheck4 = findViewById(R.id.btn_check4);

        btnCheck1.setOnClickListener(this);
        btnCheck2.setOnClickListener(this);
        btnCheck3.setOnClickListener(this);
        btnCheck4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_check1:
                startActivity(new Intent(this,Check1Activity.class));
                break;
            case R.id.btn_check2:
                startActivity(new Intent(this,Check2Activity.class));
                break;
            case R.id.btn_check3:
                startActivity(new Intent(this,Check3Activity.class));
                break;
            case R.id.btn_check4:
                Uri uri = Uri.parse("http://map.amap.com/subway/index.html");
                startActivity(new Intent(Intent.ACTION_VIEW,uri));
                break;
        }
    }
}