package com.example.underground;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.SQLException;

public class Check1Activity extends AppCompatActivity implements View.OnClickListener {

    private DBUtil util;
    private EditText etCheck1;
    private Button btnCheck1;
    private TextView tvCheck1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check1);
        setTitle("线路查询");

        util = new DBUtil();
        //忘了干啥的了
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        etCheck1 = findViewById(R.id.et_check1);
        tvCheck1 = findViewById(R.id.tv_check1);
        btnCheck1 = findViewById(R.id.btn_check1);

        btnCheck1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_check1:
                String text = etCheck1.getText().toString().trim();
                String data= "";
                if(TextUtils.isEmpty(text)){
                    data="输入为空！";
                }
                else {
                    try {
                        data = util.getCheck1(text);
                    } catch (SQLException e) {
                        data = "线路不存在！";
                    }
                    if(data.equals("此地铁线路途径站点如下：\n")){
                        data="线路不存在！";
                    }
                }
                tvCheck1.setText(data);
                break;
        }
    }
}