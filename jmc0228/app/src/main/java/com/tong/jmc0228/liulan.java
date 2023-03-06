package com.tong.jmc0228;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class liulan extends AppCompatActivity {
    private DBOpenHelper dbo;
    private TextView liulan0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liulan);
        liulan0=findViewById(R.id.liulan);
        dbo=new DBOpenHelper(this);
        String liulan="坚持天数与打卡天数不包括打卡当天！！！\n\n";
        ArrayList<meirizongjie> data = dbo.getall_meirizongjie();
        for (int i = 0; i < data.size(); i++) {
            meirizongjie mrzj = data.get(i);
            liulan=liulan+"日期："+mrzj.getRiqi_nian()+"年"+mrzj.getRiqi_yue()+"月"+mrzj.getRiqi_ri()+"日\t";
            liulan=liulan+"每日关键字："+mrzj.getGuanjianzi()+"\t";
            liulan=liulan+"每日总结："+mrzj.getZongjie()+"\t";
            liulan=liulan+"坚持天数："+mrzj.getJianchitianshu()+"\t";
            liulan=liulan+"最长天数："+mrzj.getZuichangtianshu()+"\t；\n";

        }
        liulan0.setText(liulan);
    }
}