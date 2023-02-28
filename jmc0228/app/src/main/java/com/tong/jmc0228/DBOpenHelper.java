package com.tong.jmc0228;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBOpenHelper extends SQLiteOpenHelper {
    private SQLiteDatabase db;
    public DBOpenHelper(Context context){
        super(context,"db_test",null,1);
        db=getReadableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS yonghu("+"zhanghao TEXT,"+"mima TEXT,"+"xuehao TEXT,"+"shouji TEXT,"+"banji TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS yonghu");
        onCreate(db);
    }
    public void zeng(String zhanghao,String mima,String xuehao,String shouji,String banji){
        db.execSQL("INSERT INTO yonghu (zhanghao,mima,xuehao,shouji,banji) VALUES(?,?,?,?,?)",new Object[]{zhanghao,mima,xuehao,shouji,banji});
    }
    public void shan(String zhanghao,String mima){
        db.execSQL("DELETE FROM user WHERE zhanghao = AND mima ="+zhanghao+mima);
    }
    public void gai(String mima){
        db.execSQL("UPDATE user SET mima = ?",new Object[]{mima});
    }
    public ArrayList<yonghu> getall(){

        ArrayList<yonghu> list = new ArrayList<yonghu>();
        Cursor cursor1 = db.query("yonghu",null,null,null,null,null,"zhanghao DESC");
        Cursor cursor= db.rawQuery("SELECT * FROM yonghu",null);
        while(cursor.moveToNext()){
            @SuppressLint("Range") String zhanghao = cursor.getString(cursor.getColumnIndex("zhanghao"));
            @SuppressLint("Range") String mima = cursor.getString(cursor.getColumnIndex("mima"));
            @SuppressLint("Range") String xuehao = cursor.getString(cursor.getColumnIndex("xuehao"));
            @SuppressLint("Range") String shouji = cursor.getString(cursor.getColumnIndex("shouji"));
            @SuppressLint("Range") String banji = cursor.getString(cursor.getColumnIndex("banji"));
            list.add(new yonghu(zhanghao,mima,xuehao,shouji,banji));
        }
        return list;
    }
}
