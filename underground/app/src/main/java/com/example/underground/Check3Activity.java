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

public class Check3Activity extends AppCompatActivity implements View.OnClickListener {

    private DBUtil util;
    private EditText etCheck31;
    private EditText etCheck32;
    private Button btnCheck3;
    private TextView tvCheck3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check3);
        setTitle("起点—终点查询");

        util = new DBUtil();
        //忘了干啥的了
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        etCheck31 = findViewById(R.id.et_check31);
        etCheck32 = findViewById(R.id.et_check32);
        tvCheck3 = findViewById(R.id.tv_check3);
        btnCheck3 = findViewById(R.id.btn_check3);

        btnCheck3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_check3:
                String text1 = etCheck31.getText().toString().trim();
                String text2 = etCheck32.getText().toString().trim();
                String data= "";
                if(TextUtils.isEmpty(text1)||TextUtils.isEmpty(text2)){
                    data="输入为空！";
                }
                else {
                    try {
                        data = util.getCheck3(text1,text2);
                    } catch (SQLException e) {
                        data = "两站点间不能通行！";
                    }
                    if(data.equals("")){
                        data="两站点间不能通行！";
                    }
                }
                tvCheck3.setText(data);
                break;
        }
    }
}