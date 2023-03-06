package com.tong.jmc0228;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class tixing extends AppCompatActivity implements View.OnClickListener{
    private EditText shijian0;
    private EditText shi0;
    private EditText feng0;
    private Button tixing0;
    private Button zhujiemian0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tixing);
        shijian0 = findViewById(R.id.shijian);
        shi0 = findViewById(R.id.shi);
        feng0 = findViewById(R.id.feng);
        tixing0=findViewById(R.id.tixing);
        tixing0.setOnClickListener(this);
        zhujiemian0=findViewById(R.id.zhujiemian);
        zhujiemian0.setOnClickListener(this);
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.zhujiemian:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            case R.id.tixing:
                if(TextUtils.isEmpty(shi0.getText())||TextUtils.isEmpty(feng0.getText()))
                {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
                String shijian = shijian0.getText().toString().trim();
                int shi=Integer.parseInt(shi0.getText().toString().trim());
                int feng=Integer.parseInt(feng0.getText().toString().trim());
                if (!TextUtils.isEmpty(shijian) && shi>=0&&shi<24&&feng>=0&&feng<60) {
                    Intent alarmIntent = new Intent(AlarmClock.ACTION_SET_ALARM);
                    alarmIntent.putExtra(AlarmClock.EXTRA_MESSAGE,shijian); // 标题
                    alarmIntent.putExtra(AlarmClock.EXTRA_HOUR,shi); // 小时
                    alarmIntent.putExtra(AlarmClock.EXTRA_MINUTES,feng); // 分钟
                    alarmIntent.putExtra(AlarmClock.EXTRA_SKIP_UI,true);//设置闹钟时不显示系统闹钟界面
                    alarmIntent.putExtra(AlarmClock.EXTRA_VIBRATE,true);//设置闹钟响时震动
                    alarmIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(alarmIntent);
                    Toast.makeText(this, "成功", Toast.LENGTH_SHORT).show();
                    //Intent intent = new Intent(this, MainActivity.class);
                    //startActivity(intent);
                    //finish();
                } else Toast.makeText(this, "失败", Toast.LENGTH_SHORT).show();
        }
    }
}