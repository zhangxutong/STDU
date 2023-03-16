package com.tong.jmc0228;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class zhuce extends AppCompatActivity implements View.OnClickListener {
    private DBOpenHelper dbo;
    private DBUtil util;
    private EditText zhanghao0;
    private EditText mima0;
    private EditText xuehao0;
    private EditText shouji0;
    private EditText banji0;
    private Button denglu0;
    private Button zhuce0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        zhanghao0 = findViewById(R.id.zhanghao);
        mima0 = findViewById(R.id.mima);
        xuehao0 = findViewById(R.id.xuehao);
        shouji0 = findViewById(R.id.shouji);
        banji0 = findViewById(R.id.banji);
        denglu0 = findViewById(R.id.denglu); /* 设置点击事件监听器*/
        denglu0.setOnClickListener(this); /*mTvLoginactivityRegister.setOnClickListener(this);*/
        zhuce0=findViewById(R.id.zhuce);
        zhuce0.setOnClickListener(this);
        dbo = new DBOpenHelper(this);
        util = new DBUtil();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.denglu:
                startActivity(new Intent(this, denglu.class));
                //startActivities(new Intent(this, zhuce.class));
                finish();
                break;
            case R.id.zhuce:
                String zhanghao = zhanghao0.getText().toString().trim();
                String mima = mima0.getText().toString().trim();
                String xuehao = xuehao0.getText().toString().trim();
                String shouji = shouji0.getText().toString().trim();
                String banji = banji0.getText().toString().trim();
                if (!TextUtils.isEmpty(zhanghao) && !TextUtils.isEmpty(mima)) {
                    //dbo.zeng_yonghu(zhanghao,mima,xuehao,shouji,banji);
                    try {
                        util.zeng_yonghu(zhanghao,mima,xuehao,shouji,banji);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    Toast.makeText(this, "成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, denglu.class);
                        startActivity(intent);
                        finish();
                } else Toast.makeText(this, "失败", Toast.LENGTH_SHORT).show();
        }
    }
}