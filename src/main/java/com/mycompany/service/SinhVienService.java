/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.SinhVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public class SinhVienService {
    private Connection conn;
    
    public SinhVienService(Connection conn) {
        this.conn = conn;
    }
    
    
    
    public List<SinhVien> getSinhViens(String kw) throws SQLException {      
        String sql = "SELECT * FROM sinhvien WHERE hoTen like concat('%', ?, '%')";
        PreparedStatement stm = this.conn.prepareStatement(sql);   
        stm.setString(1, kw);
        ResultSet rs = stm.executeQuery();
        List<SinhVien> dsSinhVien = new ArrayList<>();
        while (rs.next()) {
            SinhVien sv = new SinhVien();
            sv.setMssv(rs.getInt("mssv"));
            sv.setHoTen(rs.getString("hoTen"));
            sv.setGioiTinh(rs.getString("gioiTinh"));
            sv.setNgaySinh(rs.getString("ngaySinh"));
            sv.setNoiSinh(rs.getString("noiSinh"));
            sv.setEmail(rs.getString("email"));
            sv.setKhoaHoc(rs.getInt("khoaHoc"));
            sv.setKhoa(rs.getInt("khoa"));
            sv.setNganh(rs.getInt("nganh"));
            sv.setLop(rs.getInt("lop"));
            sv.setCmnd(rs.getString("cmnd"));
            sv.setKhoaMssv(rs.getBoolean("khoaMssv"));
            
            dsSinhVien.add(sv);
        }
        
        return dsSinhVien;
    }
    
    public SinhVien getSinhVienByMssv(int id) throws SQLException {
        String q = "SELECT * FROM sinhvien WHERE mssv=?";
        PreparedStatement stm = this.conn.prepareStatement(q);
        stm.setInt(1, id);
        
        ResultSet rs = stm.executeQuery();
        
        SinhVien sv = null;
        while (rs.next()) {
            sv = new SinhVien();
            sv.setMssv(rs.getInt("mssv"));
            sv.setHoTen(rs.getString("hoTen"));
            sv.setGioiTinh(rs.getString("gioiTinh"));
            sv.setNgaySinh(rs.getString("ngaySinh"));
            sv.setNoiSinh(rs.getString("noiSinh"));
            sv.setEmail(rs.getString("email"));
            sv.setKhoaHoc(rs.getInt("khoaHoc"));
            sv.setKhoa(rs.getInt("khoa"));
            sv.setNganh(rs.getInt("nganh"));
            sv.setLop(rs.getInt("lop"));
            sv.setCmnd(rs.getString("cmnd"));
            sv.setKhoaMssv(rs.getBoolean("khoaMssv"));
            break;
        }
        return sv;
    }
    public String getHoTenByMssv(int id) throws SQLException {
        String q = "SELECT hoTen FROM sinhvien WHERE mssv=?";
        PreparedStatement stm = this.conn.prepareStatement(q);
        stm.setInt(1, id);
        
        ResultSet rs = stm.executeQuery();
        
        String hoTen = null; 
        if (rs.next())
            hoTen = rs.getNString(1);
        return hoTen;
    }
    
    public boolean themSinhVien(SinhVien sv) {
        try {
            String sql = "INSERT INTO sinhvien(hoTen, gioiTinh, ngaySinh, noiSinh, email, khoaHoc, khoaMssv, lop, cmnd, khoa, nganh) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = this.conn.prepareStatement(sql);
            stm.setString(1, sv.getHoTen());
            stm.setString(2, sv.getGioiTinh());
            stm.setString(3, sv.getNgaySinh());
            stm.setString(4, sv.getNoiSinh());
            stm.setString(5, null);
            stm.setInt(6, Calendar.getInstance().get(Calendar.YEAR));
            stm.setBoolean(7, false);
            stm.setInt(8, sv.getLop());
            stm.setString(9, sv.getCmnd());
            stm.setInt(10, sv.getKhoa());
            stm.setInt(11, sv.getNganh());

            int kq = stm.executeUpdate();

            return kq > 0;
        } catch (SQLException ex) {
            Logger.getLogger(SinhVienService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
    
    public boolean xoaSinhVien(int mssv) throws SQLException {
        DiemService dsv = new DiemService(JdbcUtils.getConnect());
        if(dsv.getDiemByMssv(mssv) == null){
            try {
                String sql = "DELETE FROM sinhvien WHERE mssv=?";
                PreparedStatement stm = this.conn.prepareStatement(sql);
                stm.setInt(1, mssv);

                int row = stm.executeUpdate();

                return row > 0;
            } catch (SQLException ex) {
                ex.printStackTrace();
                Logger.getLogger(SinhVienService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            try {
                String sqlXoaDiem = "DELETE FROM diem WHERE mssv=?";
                PreparedStatement stmXoaDiem = this.conn.prepareStatement(sqlXoaDiem);
                stmXoaDiem.setInt(1, mssv);
                stmXoaDiem.executeUpdate();
                
                String sqlXoaMonHocDangKy = "DELETE FROM monhocdangky WHERE maPhieu = "
                        + "(SELECT maPhieu FROM phieu_dkmh WHERE mssv =?)";
                PreparedStatement stmXoaMonHocDangKy = this.conn.prepareStatement(sqlXoaMonHocDangKy);
                stmXoaMonHocDangKy.setInt(1, mssv);
                stmXoaMonHocDangKy.executeUpdate();
                
                String sqlXoaPhieuDKMH = "DELETE FROM phieu_dkmh WHERE mssv=?";
                PreparedStatement stmXoaPhieuDKMH = this.conn.prepareStatement(sqlXoaPhieuDKMH);
                stmXoaPhieuDKMH.setInt(1, mssv);
                stmXoaPhieuDKMH.executeUpdate();
                
                String sqlXoaAccount = "DELETE FROM quanlysinhvien.account WHERE maNguoiDung=?";
                PreparedStatement stmXoaAccount = this.conn.prepareStatement(sqlXoaAccount);
                stmXoaAccount.setInt(1, mssv);
                stmXoaAccount.executeUpdate();
                
                String sqlXoaSinhVien = "DELETE FROM sinhvien WHERE mssv=?";
                PreparedStatement stmXoaSinhVien = this.conn.prepareStatement(sqlXoaSinhVien);
                stmXoaSinhVien.setInt(1, mssv);
                
                int kq = stmXoaSinhVien.executeUpdate();
                return kq > 0;
                
            } catch (SQLException ex) {
                ex.printStackTrace();
                Logger.getLogger(SinhVienService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return false;
    }
    
    public boolean capNhatSinhVien(SinhVien sv) {
        try {
            String sql = "UPDATE sinhvien SET hoTen=?, gioiTinh=?, ngaySinh=?, noiSinh=?, email=?, khoaHoc=?, lop=?, khoaMssv=? WHERE mssv=?";
            PreparedStatement stm = this.conn.prepareStatement(sql);
            stm.setString(1, sv.getHoTen());
            stm.setString(2, sv.getGioiTinh());
            stm.setString(3, sv.getNgaySinh());
            stm.setString(4, sv.getNoiSinh());
            stm.setString(5, sv.getEmail());
            stm.setInt(6, sv.getKhoaHoc());
            stm.setInt(7, sv.getLop());
            stm.setBoolean(8, sv.getKhoaMssv());
            stm.setInt(9, sv.getMssv());
            int rows = stm.executeUpdate();
            
            return rows > 0;
        } catch (SQLException ex) {
            Logger.getLogger(SinhVienService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

}
