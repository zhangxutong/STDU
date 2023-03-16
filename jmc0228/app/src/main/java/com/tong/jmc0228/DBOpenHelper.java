package com.tong.jmc0228;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Calendar;

public class DBOpenHelper extends SQLiteOpenHelper {
    private SQLiteDatabase db;
    public DBOpenHelper(Context context){
        super(context,"db_test",null,1);
        db=getReadableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS yonghu("+"zhanghao TEXT,"+"mima TEXT,"+"xuehao TEXT,"+"shouji TEXT,"+"banji TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS meirizongjie("+"riqi_nian TEXT,"+"riqi_yue TEXT,"+"riqi_ri TEXT,"+"guanjianzi TEXT,"+"zongjie TEXT,"+"jianchitianshu TEXT,"+"zuichangtianshu TEXT,"+"zhanghao TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS now("+"zhanghao TEXT,"+"mima TEXT)");
        //zeng_meirizongjie("2023","3","4","guanjianzi","zongjie","0","0","zhanghao");
        //zeng_meirizongjie("2023","3","4","1111111","1234567","0","0","zhanghao");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS yonghu");
        db.execSQL("DROP TABLE IF EXISTS meirizongjie");
        db.execSQL("DROP TABLE IF EXISTS now");
        onCreate(db);
    }
    public void zeng_yonghu(String zhanghao,String mima,String xuehao,String shouji,String banji){
        db.execSQL("INSERT INTO yonghu (zhanghao,mima,xuehao,shouji,banji) VALUES(?,?,?,?,?)",new Object[]{zhanghao,mima,xuehao,shouji,banji});
    }
    public void shan_yonghu(String zhanghao,String mima){
        db.execSQL("DELETE FROM yonghu WHERE zhanghao = AND mima ="+zhanghao+mima);
    }
    public void gai_yonghu(String mima){
        db.execSQL("UPDATE yonghu SET mima = ?",new Object[]{mima});
    }
    public ArrayList<yonghu> getall_yonghu(){

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


    public void zeng_meirizongjie(String riqi_nian,String riqi_yue,String riqi_ri,String guanjianzi,String zongjie,String jianchitianshu,String zuichangtianshu,String zhanghao){
        db.execSQL("INSERT INTO meirizongjie (riqi_nian,riqi_yue,riqi_ri,guanjianzi,zongjie,jianchitianshu,zuichangtianshu,zhanghao) VALUES(?,?,?,?,?,?,?,?)",new Object[]{riqi_nian,riqi_yue,riqi_ri,guanjianzi,zongjie,jianchitianshu,zuichangtianshu,zhanghao});
    }
    public void shan_meirizongjie(String riqi_nian,String riqi_yue,String riqi_ri,String zhanghao){
        db.execSQL("DELETE FROM meirizongjie WHERE riqi_nian = AND riqi_yue = AND riqi_ri = AND zhanghao ="+riqi_nian+riqi_yue+riqi_ri+zhanghao);
    }
//    public void gai_meirizongjie(String riqi){
//        db.execSQL("UPDATE meirizongjie SET riqi = ?",new Object[]{riqi});
//    }
    public ArrayList<meirizongjie> getall_meirizongjie(){

        ArrayList<meirizongjie> list = new ArrayList<meirizongjie>();
        Cursor cursor1 = db.query("meirizongjie",null,null,null,null,null,"zhanghao DESC");
        Cursor cursor= db.rawQuery("SELECT * FROM meirizongjie",null);
        while(cursor.moveToNext()){
            @SuppressLint("Range") String riqi_nian = cursor.getString(cursor.getColumnIndex("riqi_nian"));
            @SuppressLint("Range") String riqi_yue = cursor.getString(cursor.getColumnIndex("riqi_yue"));
            @SuppressLint("Range") String riqi_ri = cursor.getString(cursor.getColumnIndex("riqi_ri"));
            @SuppressLint("Range") String guanjianzi = cursor.getString(cursor.getColumnIndex("guanjianzi"));
            @SuppressLint("Range") String zongjie = cursor.getString(cursor.getColumnIndex("zongjie"));
            @SuppressLint("Range") String jianchitianshu = cursor.getString(cursor.getColumnIndex("jianchitianshu"));
            @SuppressLint("Range") String zuichangtianshu = cursor.getString(cursor.getColumnIndex("zuichangtianshu"));
            @SuppressLint("Range") String zhanghao = cursor.getString(cursor.getColumnIndex("zhanghao"));
            list.add(new meirizongjie(riqi_nian,riqi_yue,riqi_ri,guanjianzi,zongjie,jianchitianshu,zuichangtianshu,zhanghao));
        }
        return list;
    }
    public int getjianchitianshu_meirizongjie(){
        Cursor cursor= db.rawQuery("SELECT * FROM meirizongjie",null);
        cursor.moveToLast();
        if(cursor.getCount()==0)
            return 0;
        @SuppressLint("Range") String str_riqi_nian = cursor.getString(cursor.getColumnIndex("riqi_nian"));
        @SuppressLint("Range") String str_riqi_yue = cursor.getString(cursor.getColumnIndex("riqi_yue"));
        @SuppressLint("Range") String str_riqi_ri = cursor.getString(cursor.getColumnIndex("riqi_ri"));
        @SuppressLint("Range") String str_jianchitianshu = cursor.getString(cursor.getColumnIndex("jianchitianshu"));
        int riqi_nian=Integer.valueOf(str_riqi_nian).intValue();
        int riqi_yue=Integer.valueOf(str_riqi_yue).intValue();
        int riqi_ri=Integer.valueOf(str_riqi_ri).intValue();
        int jianchitianshu=Integer.valueOf(str_jianchitianshu).intValue();

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        if(riqi_nian==year&&riqi_yue==month&&riqi_ri==day){
            return jianchitianshu;
        }

        riqi_ri++;
        if(riqi_ri==32){
            riqi_yue++;
        } else if (riqi_ri==31&&(riqi_yue==4||riqi_yue==6||riqi_yue==9||riqi_yue==11)) {
            riqi_yue++;
        } else if (riqi_ri==30&&riqi_yue==2) {
            riqi_yue++;
        } else if (riqi_ri==29&&riqi_yue==2&&!((riqi_nian%4==0 && riqi_nian%100 !=0) || riqi_nian%400==0)) {
            riqi_yue++;
        }
        if(riqi_yue==13){
            riqi_nian++;
        }

        if(riqi_nian==year&&riqi_yue==month&&riqi_ri==day){
            //jianchitianshu++;
            return jianchitianshu;
        }


        return 0;
    }
    public int zuichangtianshu_meirizongjie(int jianchitianshu){
        Cursor cursor= db.rawQuery("SELECT * FROM meirizongjie",null);
        cursor.moveToLast();
        if(cursor.getCount()==0)
            return 0;
        @SuppressLint("Range") String str_zuichangtianshu = cursor.getString(cursor.getColumnIndex("zuichangtianshu"));
        int zuichangtianshu=Integer.valueOf(str_zuichangtianshu).intValue();

        if(jianchitianshu>=zuichangtianshu)
            zuichangtianshu=jianchitianshu;
        return zuichangtianshu;
    }

    public void zeng_now(String zhanghao,String mima){
        db.execSQL("INSERT INTO now (zhanghao,mima) VALUES(?,?)",new Object[]{zhanghao,mima});
    }
    public String getZhanghao_now(){
        Cursor cursor= db.rawQuery("SELECT * FROM now",null);
        cursor.moveToLast();
        if(cursor.getCount()==0)
            return "";
        @SuppressLint("Range") String zhanghao = cursor.getString(cursor.getColumnIndex("zhanghao"));
        return zhanghao;
    }
    public String getMima_now(){
        Cursor cursor= db.rawQuery("SELECT * FROM now",null);
        cursor.moveToLast();
        if(cursor.getCount()==0)
            return "";
        @SuppressLint("Range") String mima = cursor.getString(cursor.getColumnIndex("mima"));
        return mima;
    }
}
