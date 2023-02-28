package com.tong.jmc0228;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class denglu extends AppCompatActivity implements View.OnClickListener {
    private DBOpenHelper dbo;
    private EditText zhanghao0;
    private EditText mima0;
    private Button denglu0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denglu);
        zhanghao0 = findViewById(R.id.zhanghao);
        mima0 = findViewById(R.id.mima);
        denglu0 = findViewById(R.id.denglu); /* 设置点击事件监听器*/
        denglu0.setOnClickListener(this); /*mTvLoginactivityRegister.setOnClickListener(this);*/
        dbo = new DBOpenHelper(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            /*case R.id.zhuce:
                startActivities(new Intent(this, zhuce.class));
                finish();
                break;*/
            case R.id.denglu:
                String zhanghao = zhanghao0.getText().toString().trim();
                String mima = mima0.getText().toString().trim();
                if (!TextUtils.isEmpty(zhanghao) && !TextUtils.isEmpty(mima)) {
                    ArrayList<yonghu> data = dbo.getall();
                    boolean ischenggong = false;
                    for (int i = 0; i < data.size(); i++) {
                        yonghu yh = data.get(i);
                        if (zhanghao.equals(yh.getZhanghao()) && mima.equals(yh.getMima())) {
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