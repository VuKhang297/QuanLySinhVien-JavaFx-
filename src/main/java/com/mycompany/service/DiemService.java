/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.Diem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public class DiemService {
    private Connection conn;
    
    public DiemService(Connection conn) {
        this.conn = conn;
    }

    public List<Diem> getDiems(int lop, int mon, int nam, int hk) throws SQLException {      
        String sql = "SELECT sv.mssv, sv.hoTen, d.diemGK, d.diemCK, d.iddiem FROM diem d, sinhvien sv WHERE sv.mssv in (select mssv from quanlysinhvien.sinhvien where lop=?) and monHoc=? and namHoc=? and hocKy=? and d.mssv = sv.mssv";  
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setInt(1, lop);
        stm.setInt(2, mon);
        stm.setInt(3, nam);
        stm.setInt(4, hk);
        ResultSet rs = stm.executeQuery();
        List<Diem> dsDiem = new ArrayList<>();
        while (rs.next()) {
            Diem diem = new Diem();
            
            diem.setMsvv(rs.getInt("mssv"));
            diem.setMaDiem(rs.getInt("iddiem"));
            diem.setTenSinhVien(rs.getString("hoTen"));
            diem.setDiemGK(rs.getDouble("diemGK"));
            diem.setDiemCK(rs.getDouble("diemCK"));
            
            dsDiem.add(diem);
        }
        
        return dsDiem;
    }
    
    public List<Diem> getDiemByMssv(int mssv) throws SQLException {
        String sql = "SELECT m.tenMH, d.* FROM diem d, monhoc m WHERE d.monHoc = m.maMH and d.mssv=?";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setInt(1, mssv);
        ResultSet rs = stm.executeQuery();
        List<Diem> dsDiemSinhVien = new ArrayList<>();
        while(rs.next()){
            Diem diemSv = new Diem();
            diemSv.setTenMH(rs.getString("tenMH"));
            diemSv.setNamHoc(rs.getInt("namHoc"));
            diemSv.setHocKy(rs.getInt("hocKy"));
            diemSv.setDiemGK(rs.getDouble("diemGK"));
            diemSv.setDiemCK(rs.getDouble("diemCK"));
            dsDiemSinhVien.add(diemSv);
        }
        return dsDiemSinhVien;
    }
    
    public boolean capNhatDiem(Diem diem) {
        try {
            String sql = "UPDATE diem SET mssv=?, monHoc=?, namHoc=?, hocKy=?, diemGK=?, diemCK=? WHERE iddiem=?";
            PreparedStatement stm = this.conn.prepareStatement(sql);
            stm.setInt(1, diem.getMsvv());
            stm.setInt(2, diem.getMonHoc());
            stm.setInt(3, diem.getNamHoc());
            stm.setInt(4, diem.getHocKy());
            stm.setDouble(5, diem.getDiemGK());
            stm.setDouble(6, diem.getDiemCK());
            stm.setInt(7, diem.getMaDiem());
            int rows = stm.executeUpdate();
            
            return rows > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DiemService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
}
