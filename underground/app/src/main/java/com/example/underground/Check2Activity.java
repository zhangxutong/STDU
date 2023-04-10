package com.example.underground;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.SQLException;

public class Check2Activity extends AppCompatActivity implements View.OnClickListener {

    private DBUtil util;
    private EditText etCheck2;
    private Button btnCheck2;
    private TextView tvCheck2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check2);
        setTitle("站点查询");

        util = new DBUtil();
        //忘了干啥的了
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        etCheck2 = findViewById(R.id.et_check2);
        tvCheck2 = findViewById(R.id.tv_check2);
        btnCheck2 = findViewById(R.id.btn_check2);

        btnCheck2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_check2:
                String text = etCheck2.getText().toString().trim();
                String data= "";
                if(TextUtils.isEmpty(text)){
                    data="输入为空！";
                }
                else {
                    try {
                        data = util.getCheck2(text);
                    } catch (SQLException e) {
                        data = "站点不存在！";
                    }
                    if(data.equals("途径该站点的线路如下：\n")){
                        data="站点不存在！";
                    }
                }
                tvCheck2.setText(data);
                break;
        }
    }
}