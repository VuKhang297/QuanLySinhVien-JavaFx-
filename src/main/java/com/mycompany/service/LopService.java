/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.Lop;
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
public class LopService {
    public List<Lop> getDsLop() throws SQLException {
        Connection conn = JdbcUtils.getConnect();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM lop");
        
        List<Lop> dsLop = new ArrayList<>();
        while (rs.next()) {
            Lop l = new Lop();
            l.setMaLop(rs.getInt("maLop"));
            l.setTenLop(rs.getString("tenLop"));
            l.setSiSo(rs.getInt("siSo"));
            l.setNganh(rs.getInt("nganh"));
            
            dsLop.add(l);
        }
        
        conn.close();
        return dsLop;
    }
    
    public List<Lop> getDsLopByNganh(int maNganh) throws SQLException {
        Connection conn = JdbcUtils.getConnect();
        String sql = "SELECT * FROM lop WHERE nganh=?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, maNganh);
        ResultSet rs = stm.executeQuery();
        
        List<Lop> dsLop = new ArrayList<>();
        while (rs.next()) {
            Lop l = new Lop();
            l.setMaLop(rs.getInt("maLop"));
            l.setTenLop(rs.getString("tenLop"));
            l.setSiSo(rs.getInt("siSo"));
            l.setNganh(rs.getInt("nganh"));
            
            dsLop.add(l);
        }
        
        conn.close();
        return dsLop;
    }
    
    public Lop getLopById(int maLop) throws SQLException {
        Connection conn = JdbcUtils.getConnect();
        String q = "SELECT * FROM lop WHERE maLop=?";
        PreparedStatement stm = conn.prepareStatement(q);
        stm.setInt(1, maLop);
        
        ResultSet rs = stm.executeQuery();
        
        Lop l = null;
        while (rs.next()) {
            l = new Lop();
            l.setMaLop(rs.getInt("maLop"));
            l.setTenLop(rs.getString("tenLop"));
            l.setSiSo(rs.getInt("siSo"));
            l.setNganh(rs.getInt("nganh"));
            break;
        }
        conn.close();
        return l;
    }
    
    public List<Lop> getDsLopByKhoa(int maKhoa) throws SQLException{
        Connection conn = JdbcUtils.getConnect();
        String q = "SELECT * FROM quanlysinhvien.lop WHERE lop.nganh IN (SELECT n.maNganh FROM nganh n, khoa k WHERE n.khoa =?)";
        PreparedStatement stm = conn.prepareStatement(q);
        stm.setInt(1, maKhoa);
        
        ResultSet rs = stm.executeQuery();
        List<Lop> dsLop = new ArrayList<>();
        Lop l = null;
        while (rs.next()) {
            l = new Lop();
            l.setMaLop(rs.getInt("maLop"));
            l.setTenLop(rs.getString("tenLop"));
            l.setSiSo(rs.getInt("siSo"));
            l.setNganh(rs.getInt("nganh"));
            
            dsLop.add(l);
        }
        conn.close();
        return dsLop;
    }
}
