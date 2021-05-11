/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.Khoa;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dell
 */
public class KhoaService {
    public List<Khoa> getDsKhoa() throws SQLException {
        Connection conn = JdbcUtils.getConnect();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM khoa");
        
        List<Khoa> dsKhoa = new ArrayList<>();
        while (rs.next()) {
            Khoa k = new Khoa();
            k.setMaKhoa(rs.getInt("maKhoa"));
            k.setTenKhoa(rs.getString("tenKhoa"));
            
            dsKhoa.add(k);
        }
        
        conn.close();
        return dsKhoa;
    }
}
