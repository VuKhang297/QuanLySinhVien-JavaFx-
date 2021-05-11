/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author dell
 */
public class JdbcUtils {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
        }
    }
    public static Connection getConnect() throws SQLException{
        return DriverManager.getConnection("jdbc:mysql://localhost/quanlysinhvien", 
                "root", "khangbum10a8");
    }
}
