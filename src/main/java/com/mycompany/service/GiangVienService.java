/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.GiangVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author dell
 */
public class GiangVienService {
    Connection conn;
    public GiangVienService(Connection conn){
        this.conn = conn;
    }
    
    public GiangVien getGiangVienById(int id) throws SQLException {
        String q = "SELECT * FROM giangvien WHERE maGiangVien=?";
        PreparedStatement stm = this.conn.prepareStatement(q);
        stm.setInt(1, id);
        
        ResultSet rs = stm.executeQuery();
        
        GiangVien gv = null;
        while (rs.next()) {
            gv = new GiangVien();
            gv.setMaGiangVien(rs.getInt("maGiangVien"));
            gv.setTenGiangVien(rs.getString("tenGiangVien"));
            gv.setHocVi(rs.getString("hocVi"));
            gv.setSdt(rs.getString("sdt"));
            gv.setEmail(rs.getString("mail"));
           
            break;
        }
        return gv;
    }
}
