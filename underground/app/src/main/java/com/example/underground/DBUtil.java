package com.example.underground;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBUtil {

    //连接数据库
    public Connection getConnection()  {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://192.168.200.144:3306/test1?useUnicode=true&characterEncoding=utf8", "tong", "123456");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }

    //check1
    public String getCheck1(String text) throws SQLException {

        String data="此地铁线路途径站点如下："+"\n";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "select distinct station_name from bj_subway where line_name like ?";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1, text+"%");
        ResultSet rs=preparedStatement.executeQuery();

        while(rs.next()){
            data+=rs.getString("station_name");
            data+="\n";

        }
        return data;
    }

    //check2
    public String getCheck2(String text) throws SQLException {

        String data="途径该站点的线路如下："+"\n";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "select distinct line_name from bj_subway where station_name = ?";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1, text);
        ResultSet rs=preparedStatement.executeQuery();

        while(rs.next()){
            data+=rs.getString("line_name");
            data+="\n";

        }
        return data;
    }

    //check3
    public String getCheck3(String text1,String text2) throws SQLException {

        String data="";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "WITH RECURSIVE transfer (start_station, stop_station, stops, path) AS ( SELECT station_name, next_station, 1, CAST(CONCAT(line_name,station_name , '\n', line_name,next_station) AS CHAR(1000)) FROM bj_subway WHERE station_name = ? UNION ALL SELECT p.start_station, e.next_station, stops + 1, CONCAT(p.path, '\n', e.line_name, e.next_station) FROM transfer p JOIN bj_subway e ON p.stop_station = e.station_name AND (INSTR(p.path, e.next_station) = 0) ) SELECT * FROM transfer WHERE stop_station = ?";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1, text1);
        preparedStatement.setString(2, text2);
        ResultSet rs=preparedStatement.executeQuery();

        while(rs.next()){
            int stops=rs.getInt("stops")+1;
            data=data+"共经过"+stops+"站：\n";
            data+=rs.getString("path");
            data+="\n";
            break;

        }
        return data;
    }


}
