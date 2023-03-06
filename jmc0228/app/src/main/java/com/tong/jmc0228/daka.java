package com.tong.jmc0228;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class daka extends AppCompatActivity implements View.OnClickListener {
    private DBOpenHelper dbo;
    private TextView riqi0;
    private EditText guanjianzi0;
    private EditText zongjie0;
    private TextView jianchitianshu0;
    private TextView zuichangtianshu0;
    private Button daka0;
    private Button tixing0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daka);
        riqi0=findViewById(R.id.riqi);
        guanjianzi0=findViewById(R.id.guanjianzi);
        zongjie0=findViewById(R.id.zongjie);
        jianchitianshu0=findViewById(R.id.jianchitianshu);
        zuichangtianshu0=findViewById(R.id.zuichangtianshu);
        daka0=findViewById(R.id.daka);
        daka0.setOnClickListener(this);
        tixing0=findViewById(R.id.tixing);
        tixing0.setOnClickListener(this);
        dbo=new DBOpenHelper(this);
        //dbo.zeng_meirizongjie("2023","3","4","1111111","1234567","0","0","zhanghao");
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        riqi0.setText("日期："+year+"年"+month+"月"+day+"日");
        int jianchitianshu = dbo.getjianchitianshu_meirizongjie();
        int zuichangtianshu = dbo.zuichangtianshu_meirizongjie(jianchitianshu);
        jianchitianshu0.setText("坚持天数"+jianchitianshu);
        zuichangtianshu0.setText("最长天数"+zuichangtianshu);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.daka:
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH)+1;
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                int second = calendar.get(Calendar.SECOND);
                String riqi_nian=""+year;
                String riqi_yue=""+month;
                String riqi_ri=""+day;

                String guanjianzi = guanjianzi0.getText().toString().trim();
                String zongjie = zongjie0.getText().toString().trim();
                int jianchitianshu = dbo.getjianchitianshu_meirizongjie();
                int zuichangtianshu = dbo.zuichangtianshu_meirizongjie(jianchitianshu);
                String str_jianchitianshu=""+jianchitianshu;
                String str_zuichangtianshu=""+zuichangtianshu;
                String zhanghao=dbo.getZhanghao_now();
                //String zhanghao = zhanghao0.getText().toString().trim();
                if (!TextUtils.isEmpty(guanjianzi) && !TextUtils.isEmpty(zongjie)) {
                    dbo.zeng_meirizongjie(riqi_nian,riqi_yue,riqi_ri,guanjianzi,zongjie,str_jianchitianshu,str_zuichangtianshu,zhanghao);
                    Toast.makeText(this, "成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, liulan.class);
                    startActivity(intent);
                    finish();
                } else
                    Toast.makeText(this, "失败", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tixing:
                startActivity(new Intent(this,tixing.class));
                finish();
                break;
        }
    }
}