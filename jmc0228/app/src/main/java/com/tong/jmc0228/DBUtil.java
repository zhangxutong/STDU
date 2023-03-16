package com.tong.jmc0228;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.StrictMode;
import android.view.View;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

public class DBUtil {


    public Connection getConnection()  {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://192.168.7.144:3306/jmc0228?useUnicode=true&characterEncoding=utf8", "tong", "123456");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }
//    public void zeng_yonghu(String zhanghao,String mima,String xuehao,String shouji,String banji) throws SQLException {
//
//        Connection connection = getConnection();
//        PreparedStatement preparedStatement = null;
//
//        connection.prepareStatement("INSERT INTO yonghu (zhanghao,mima,xuehao,shouji,banji) VALUES(?,?,?,?,?)", (String[]) new Object[]{zhanghao,mima,xuehao,shouji,banji});
//    }

    public void zeng_yonghu(String zhanghao,String mima,String xuehao,String shouji,String banji)
            throws Exception {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "insert into yonghu (zhanghao,mima,xuehao,shouji,banji) values (?,?,?,?,?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, zhanghao);
        preparedStatement.setString(2, mima);
        preparedStatement.setString(3, xuehao);
        preparedStatement.setString(4, shouji);
        preparedStatement.setString(5, banji);
        preparedStatement.executeUpdate();
    }

    public ArrayList<yonghu> getall_yonghu() throws SQLException {

        ArrayList<yonghu> list = new ArrayList<yonghu>();
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "select * from yonghu";
        preparedStatement=connection.prepareStatement(sql);
        ResultSet rs=preparedStatement.executeQuery();
        while(rs.next()){
            String zhanghao = rs.getString("zhanghao");
            String mima = rs.getString("mima");
            String xuehao = rs.getString("xuehao");
            String shouji = rs.getString("shouji");
            String banji = rs.getString("banji");
            list.add(new yonghu(zhanghao,mima,xuehao,shouji,banji));
        }
        return list;
    }

    public void zeng_meirizongjie(String riqi_nian,String riqi_yue,String riqi_ri,String guanjianzi,String zongjie,String jianchitianshu,String zuichangtianshu,String zhanghao) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "insert into meirizongjie (riqi_nian,riqi_yue,riqi_ri,guanjianzi,zongjie,jianchitianshu,zuichangtianshu,zhanghao) values (?,?,?,?,?,?,?,?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, riqi_nian);
        preparedStatement.setString(2, riqi_yue);
        preparedStatement.setString(3, riqi_ri);
        preparedStatement.setString(4, guanjianzi);
        preparedStatement.setString(5, zongjie);
        preparedStatement.setString(6, jianchitianshu);
        preparedStatement.setString(7, zuichangtianshu);
        preparedStatement.setString(8, zhanghao);
        preparedStatement.executeUpdate();
    }

    public ArrayList<meirizongjie> getall_meirizongjie() throws SQLException {

        ArrayList<meirizongjie> list = new ArrayList<meirizongjie>();
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "select * from meirizongjie";
        preparedStatement=connection.prepareStatement(sql);
        ResultSet rs=preparedStatement.executeQuery();
        while(rs.next()){
            String riqi_nian =rs.getString("riqi_nian");
            String riqi_yue = rs.getString("riqi_yue");
            String riqi_ri = rs.getString("riqi_ri");
            String guanjianzi = rs.getString("guanjianzi");
            String zongjie = rs.getString("zongjie");
            String jianchitianshu = rs.getString("jianchitianshu");
            String zuichangtianshu = rs.getString("zuichangtianshu");
            String zhanghao = rs.getString("zhanghao");
            list.add(new meirizongjie(riqi_nian,riqi_yue,riqi_ri,guanjianzi,zongjie,jianchitianshu,zuichangtianshu,zhanghao));
        }
        return list;
    }

    public int getjianchitianshu_meirizongjie(DBOpenHelper dbo) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "select * from meirizongjie where zhanghao = ?";
        preparedStatement=connection.prepareStatement(sql);
        String zhanghao=dbo.getZhanghao_now();
        preparedStatement.setString(1, zhanghao);

        ResultSet rs=preparedStatement.executeQuery();
        try {
            rs.last();
            rs.getString("riqi_nian");
        } catch (SQLException e) {
            return 0;
        }
        String str_riqi_nian =rs.getString("riqi_nian");
        String str_riqi_yue = rs.getString("riqi_yue");
        String str_riqi_ri = rs.getString("riqi_ri");
        String str_jianchitianshu = rs.getString("jianchitianshu");
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
    public int zuichangtianshu_meirizongjie(int jianchitianshu,DBOpenHelper dbo) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "select * from meirizongjie where zhanghao = ?";
        preparedStatement=connection.prepareStatement(sql);
        String zhanghao=dbo.getZhanghao_now();
        preparedStatement.setString(1, zhanghao);
        ResultSet rs=preparedStatement.executeQuery();
        try {
            rs.last();
            rs.getString("zuichangtianshu");
        } catch (SQLException e) {
            return 0;
        }
        String str_zuichangtianshu = rs.getString("zuichangtianshu");
        int zuichangtianshu=Integer.valueOf(str_zuichangtianshu).intValue();

        if(jianchitianshu>=zuichangtianshu)
            zuichangtianshu=jianchitianshu;
        return zuichangtianshu;
    }

    public int zongcishu_meirizongjie(String zhanghao0) throws SQLException {

        ArrayList<meirizongjie> list = new ArrayList<meirizongjie>();
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "select * from meirizongjie where zhanghao = ?";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1, zhanghao0);
        ResultSet rs=preparedStatement.executeQuery();
        while(rs.next()){
            String riqi_nian =rs.getString("riqi_nian");
            String riqi_yue = rs.getString("riqi_yue");
            String riqi_ri = rs.getString("riqi_ri");
            String guanjianzi = rs.getString("guanjianzi");
            String zongjie = rs.getString("zongjie");
            String jianchitianshu = rs.getString("jianchitianshu");
            String zuichangtianshu = rs.getString("zuichangtianshu");
            String zhanghao = rs.getString("zhanghao");
            list.add(new meirizongjie(riqi_nian,riqi_yue,riqi_ri,guanjianzi,zongjie,jianchitianshu,zuichangtianshu,zhanghao));
        }
        return list.size();
    }

    public boolean shifoudaka_meirizongjie(DBOpenHelper dbo) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "select * from meirizongjie where zhanghao = ?";
        preparedStatement=connection.prepareStatement(sql);
        String zhanghao=dbo.getZhanghao_now();
        preparedStatement.setString(1, zhanghao);

        ResultSet rs=preparedStatement.executeQuery();
        try {
            rs.last();
            rs.getString("riqi_nian");
        } catch (SQLException e) {
            return false;
        }
        String str_riqi_nian =rs.getString("riqi_nian");
        String str_riqi_yue = rs.getString("riqi_yue");
        String str_riqi_ri = rs.getString("riqi_ri");
        int riqi_nian=Integer.valueOf(str_riqi_nian).intValue();
        int riqi_yue=Integer.valueOf(str_riqi_yue).intValue();
        int riqi_ri=Integer.valueOf(str_riqi_ri).intValue();

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        if(riqi_nian==year&&riqi_yue==month&&riqi_ri==day){
            return true;
        }
        return false;
    }
}
