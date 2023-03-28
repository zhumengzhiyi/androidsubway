package com.example.subway2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Dao1 extends MySqlHelp {
    public List<Bean1> selectByName(String name) {
        List<Bean1> list = new ArrayList<Bean1>();
        try {
            //获得链接对象
            Connection connection = MySqlHelp.getConnection();
            String sql = "select * from date where name=?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,name);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                Bean1 subject = new Bean1();
                subject.setID(rs.getInt("ID"));
                subject.setName(rs.getString("name"));
                System.out.println(rs.getString("name"));
                subject.setNol(rs.getString("nol"));
                list.add(subject);
            }
            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    //由线路查询，车站点
    public  List<Bean1> selectByNol(String nol) {
        List<Bean1> list = new ArrayList<Bean1>();
        try {
            //获得链接对象
            Connection connection = getConnection();
            String sql = "select * from date where nol=?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,nol);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                Bean1 subject = new Bean1();
                subject.setID(rs.getInt("ID"));
                subject.setName(rs.getString("name"));
                subject.setNol(rs.getString("nol"));
                subject.setNolstation(rs.getString("nolstation"));
                list.add(subject);
            }
            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public String[] selectBystation(String name) {
        String[] nolstation=new String[10];
        try {
            //获得链接对象
            Connection connection = MySqlHelp.getConnection();
            String sql = "select * from date where name=?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,name);
            ResultSet rs = pstmt.executeQuery();
            int i=0;
            while(rs.next()) {

                nolstation[i++]=rs.getString("nolstation");


            }
            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return nolstation;
    }
}
