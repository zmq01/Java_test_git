package com.njau.shop.util;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    private static String username = "root";

    private static String password = "998114";

    private static String url = "jdbc:mysql://localhost:3306/shop?useUnicode=true&characterEncoding=utf-8";

    private static Connection connection;

    public static Connection connDB(){
        /*try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/

        try {
            connection = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public static void main(String[] args){
        ConnectionDB.connDB();
    }
}


