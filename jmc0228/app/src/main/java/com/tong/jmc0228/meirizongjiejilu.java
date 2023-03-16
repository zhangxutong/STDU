package com.tong.jmc0228;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;

public class meirizongjiejilu extends AppCompatActivity {
    private DBOpenHelper dbo;
    private DBUtil util;
    private TextView liulan0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meirizongjiejilu);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        liulan0=findViewById(R.id.liulan);
        dbo=new DBOpenHelper(this);
        util = new DBUtil();
        String liulan="";
        ArrayList<yonghu> data = null;
        try {
            data = util.getall_yonghu();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ArrayList<meirizongjie> data_meirizongjie = null;
        try {
            data_meirizongjie = util.getall_meirizongjie();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < data.size(); i++) {
            yonghu yh = data.get(i);

            int xvhao=i+1;
            liulan=liulan+"序号："+xvhao+"\t";
            liulan=liulan+"班级："+yh.getBanji()+"\t";
            liulan=liulan+"学号："+yh.getXuehao()+"\t";
            liulan=liulan+"姓名："+yh.getZhanghao()+"\t";
            try {
                liulan=liulan+"发表总次数："+util.zongcishu_meirizongjie(yh.getZhanghao())+"\t\n";
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        }
        liulan0.setText(liulan);
    }
}