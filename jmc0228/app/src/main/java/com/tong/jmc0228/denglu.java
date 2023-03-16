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

import java.sql.SQLException;
import java.util.ArrayList;

public class denglu extends AppCompatActivity implements View.OnClickListener {
    private DBOpenHelper dbo;
    private DBUtil util;
    private EditText zhanghao0;
    private EditText mima0;
    private Button denglu0;
    private Button zhuce0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denglu);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        zhanghao0 = findViewById(R.id.zhanghao);
        mima0 = findViewById(R.id.mima);
        denglu0 = findViewById(R.id.denglu); /* 设置点击事件监听器*/
        denglu0.setOnClickListener(this); /*mTvLoginactivityRegister.setOnClickListener(this);*/
        zhuce0=findViewById(R.id.zhuce);
        zhuce0.setOnClickListener(this);
        dbo = new DBOpenHelper(this);
        util = new DBUtil();
        zhanghao0.setText(dbo.getZhanghao_now());
        mima0.setText(dbo.getMima_now());
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.zhuce:
                startActivity(new Intent(this, zhuce.class));
                //startActivities(new Intent(this, zhuce.class));
                finish();
                break;
            case R.id.denglu:
                String zhanghao = zhanghao0.getText().toString().trim();
                String mima = mima0.getText().toString().trim();
                if(zhanghao.equals("admin")&&mima.equals("admin"))
                {
                    dbo.zeng_now(zhanghao,mima);
                    Intent intent = new Intent(this, jiaoshi.class);
                    startActivity(intent);
                    Toast.makeText(this, "教师用户", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                }
                if (!TextUtils.isEmpty(zhanghao) && !TextUtils.isEmpty(mima)) {
                    ArrayList<yonghu> data = null;
                    try {
                        data = util.getall_yonghu();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    boolean ischenggong = false;
                    for (int i = 0; i < data.size(); i++) {
                        yonghu yh = data.get(i);
                        if (zhanghao.equals(yh.getZhanghao()) && mima.equals(yh.getMima())) {
                            dbo.zeng_now(zhanghao,mima);
                            ischenggong = true;
                            break;
                        } else {
                            ischenggong = false;
                        }
                    }
                    if (ischenggong) {
                        Toast.makeText(this, "成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else Toast.makeText(this, "失败", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(this, "失败", Toast.LENGTH_SHORT).show();
        }
    }
}