package com.example.tableview.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/java_fx_rev",
                    "root",
                    ""
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
