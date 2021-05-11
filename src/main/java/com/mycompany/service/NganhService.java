/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.Nganh;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dell
 */
public class NganhService {
    public List<Nganh> getDsNganhByKhoa(int maKhoa) throws SQLException {
        Connection conn = JdbcUtils.getConnect();
        String sql = "SELECT * FROM nganh WHERE khoa=?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, maKhoa);
        ResultSet rs = stm.executeQuery();
        
        List<Nganh> dsNganh = new ArrayList<>();
        while (rs.next()) {
            Nganh n = new Nganh();
            n.setMaNganh(rs.getInt("maNganh"));
            n.setTenNganh(rs.getString("tenNganh"));
            n.setKhoa(rs.getInt("khoa"));

            
            dsNganh.add(n);
        }
        
        conn.close();
        return dsNganh;
    }
}
