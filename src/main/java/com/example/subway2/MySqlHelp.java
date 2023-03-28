package com.example.subway2;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 这是链接MySQL的辅助类
 */

public class MySqlHelp {


    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String name="sf";
        String password="482482";
        String url="jdbc:mysql://192.168.104.138:3306/chezhan?useSSL=false";
        Connection connection = null ;
        try {
            connection = DriverManager.getConnection(url,name,password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    public static void close(Connection connection) {
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void close(PreparedStatement preparedStatement) {
        if(preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet resultSet) {
        if(resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }



    public static int getUserSize(){
        final String CLS="com.mysql.jdbc.Driver";
        final String URL="jdbc:mysql://192.168.74.1:3306/chezhan";
        final String USER="sf";
        final String PWD="482482";
        int count=0;
        try{
            Class.forName(CLS);
            Connection connection= DriverManager.getConnection(URL,USER,PWD);
            String sql="select count(1) as sl from date";
            Statement statement=connection.createStatement();
            ResultSet rs=statement.executeQuery(sql);
            while(rs.next()){
                count=rs.getInt("sl");

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }
}

