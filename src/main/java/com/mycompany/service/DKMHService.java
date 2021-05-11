/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.DKMH;
import com.mycompany.pojo.MonHocDangKy;
import com.mycompany.qlsv.DangKyMonHocController;
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
public class DKMHService {
    private Connection conn;
    
    public DKMHService(Connection conn) {
        this.conn = conn;
    }
    String ngayHienTai = String.valueOf(java.time.LocalDate.now());
    public List<DKMH> getDKMH(int maLop) throws SQLException {      
        String sql = 
        "SELECT tkbmh.maMH, mh.tenMH, mh.hocPhi, gv.tenGiangVien, tkbmh.thu, mh.soTC, tkbmh.tietBD, tkbmh.soTiet, tkbmh.phongHoc, tkbmh.tuanHoc\n" +
"FROM thoikhoabieu tkb, monhoc mh, giangvien gv, tkb_monhoc tkbmh\n" +
"WHERE mh.maMH = tkbmh.maMH and gv.maGiangVien = tkbmh.maGiangVien and tkbmh.maTKB = tkb.maTKB and tkb.maLop =?";
        PreparedStatement stm = this.conn.prepareStatement(sql);   
        stm.setInt(1, maLop);
        ResultSet rs = stm.executeQuery();
        List<DKMH> dsDKMH = new ArrayList<>();
        while (rs.next()) {
            DKMH dkmh = new DKMH();
            dkmh.setMaMonHoc(rs.getInt("maMH"));
            dkmh.setTenMonHoc(rs.getString("tenMH"));
            dkmh.setCbgv(rs.getString("tenGiangVien"));
            dkmh.setSoTinChi(rs.getInt("soTC"));
            dkmh.setHocPhi(rs.getBigDecimal("hocPhi"));
            dkmh.setThu(rs.getInt("thu"));
            dkmh.setTietBatDau(rs.getInt("tietBD"));
            dkmh.setSoTiet(rs.getInt("soTiet"));
            dkmh.setPhong(rs.getString("phongHoc"));
            dkmh.setTuanHoc(rs.getString("tuanHoc"));
            
            dsDKMH.add(dkmh);
        }   
        return dsDKMH;
    }
    
    public List<MonHocDangKy> getDsMonHocDaDangKy(int maPhieu) throws SQLException {      
        String sql = "SELECT * FROM quanlysinhvien.monhoc WHERE maMH IN "
                + "(SELECT maMH FROM monhocdangky WHERE maPhieu=?)";
        PreparedStatement stm = this.conn.prepareStatement(sql);   
        stm.setInt(1, maPhieu);
        ResultSet rs = stm.executeQuery();
        List<MonHocDangKy> dsMHDK = new ArrayList<>();
        while (rs.next()) {
            MonHocDangKy mhdk = new MonHocDangKy();
            mhdk.setMaMonHoc(rs.getInt("maMH"));
            mhdk.setTenMonHoc(rs.getString("tenMH"));
            mhdk.setHocPhi(rs.getBigDecimal("hocPhi"));
            mhdk.setSoTinChi(rs.getInt("soTC"));            
            
            dsMHDK.add(mhdk);
        }   
        return dsMHDK;
    }
    
   private int getMaPhieu(int mssv, String ngay) throws SQLException{
       String sqlGetMaPhieu = "SELECT maPhieu FROM phieu_dkmh WHERE mssv=? and ngayDK=?";
            PreparedStatement stmGetMaPhieu = this.conn.prepareStatement(sqlGetMaPhieu);
            stmGetMaPhieu.setInt(1, mssv);
            stmGetMaPhieu.setString(2, ngay);
            ResultSet rsGetMaPhieu = stmGetMaPhieu.executeQuery();
            int maPhieu = 0;
            if(rsGetMaPhieu.next())
                maPhieu = rsGetMaPhieu.getInt("maPhieu");
            return maPhieu;
   }
   
   public int getMaPhieuByMssv(int mssv, int namHoc, int hocKy) throws SQLException{
       String sqlGetMaPhieu = "SELECT maPhieu FROM phieu_dkmh WHERE mssv=? and namHoc=? and hocKy=?";
            PreparedStatement stmGetMaPhieu = this.conn.prepareStatement(sqlGetMaPhieu);
            stmGetMaPhieu.setInt(1, mssv);
            stmGetMaPhieu.setInt(2, namHoc);
            stmGetMaPhieu.setInt(3, hocKy);
            ResultSet rsGetMaPhieu = stmGetMaPhieu.executeQuery();
            int maPhieu = 0;
            if(rsGetMaPhieu.next())
                maPhieu = rsGetMaPhieu.getInt("maPhieu");
            return maPhieu;
   }
    
   public boolean dangKyMonHoc(int mssv) {
        DangKyMonHocController dkmhctrl = new DangKyMonHocController();
        try {
            String sql = "INSERT INTO phieu_dkmh(mssv, ngayDK, namHoc, hocKy) VALUES(?, ?, ?, ?)";
            PreparedStatement stm = this.conn.prepareStatement(sql);
            stm.setInt(1, mssv);
            stm.setString(2, ngayHienTai);
            stm.setInt(3, 2021);
            stm.setInt(4, 3);
            
            stm.executeUpdate();
            
            int maPhieu = getMaPhieu(mssv, ngayHienTai);
            int soLuongMonHoc = dkmhctrl.getDsMonHocDangKy().size();
            int sl = 0;           
            String sqlInsertMonHoc = "INSERT INTO monhocdangky(maPhieu, maMH) VALUES(?, ?)";
            String sqlInsertDiem = "INSERT INTO quanlysinhvien.diem(mssv, monHoc, namHoc, hocKy) VALUES(?, ?, ?, ?)";
            PreparedStatement stmInsertDiem = this.conn.prepareStatement(sqlInsertDiem);
            PreparedStatement stmInsertMonHoc = this.conn.prepareStatement(sqlInsertMonHoc);
            for(DKMH mhdk : dkmhctrl.getDsMonHocDangKy()){           
                stmInsertMonHoc.setInt(1, maPhieu);
                stmInsertMonHoc.setInt(2, mhdk.getMaMonHoc());
                stmInsertMonHoc.executeUpdate();
                
                stmInsertDiem.setInt(1, mssv);
                stmInsertDiem.setInt(2, mhdk.getMaMonHoc());
                stmInsertDiem.setInt(3, 2021);
                stmInsertDiem.setInt(4, 3);
                stmInsertDiem.executeUpdate();
                sl++;
            }
            if(soLuongMonHoc == sl)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(SinhVienService.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return false;
    }
   
   public String getMatKhauCap2(int userId) throws SQLException{
       String sql = "SELECT matKhauCap2 FROM quanlysinhvien.account WHERE maNguoiDung=?";
       PreparedStatement stm = this.conn.prepareStatement(sql);
       stm.setInt(1, userId);
       ResultSet rs = stm.executeQuery();
       String matKhauCap2 = null;
       if(rs.next())
           matKhauCap2 = rs.getString("matKhauCap2");
       
       return matKhauCap2;
   }
   
   public boolean huyPhieuDangKy(int mssv, List<MonHocDangKy> dsMHDK){
        try {
            
            String sqlXoaDiem = "DELETE FROM diem WHERE mssv=? and monHoc=? and namHoc=? and hocKy=?";
            PreparedStatement stmXoaDiem = this.conn.prepareStatement(sqlXoaDiem);
            for(MonHocDangKy mhdk : dsMHDK){
                stmXoaDiem.setInt(1, mssv);
                stmXoaDiem.setInt(2, mhdk.getMaMonHoc());
                stmXoaDiem.setInt(3, 2021);
                stmXoaDiem.setInt(4, 3);
                stmXoaDiem.executeUpdate();
            }
            String sqlGetPhieu = "SELECT maPhieu FROM phieu_dkmh WHERE mssv=? and namHoc=? and hocKy=?";
            PreparedStatement stmGetPhieu = this.conn.prepareStatement(sqlGetPhieu);
            stmGetPhieu.setInt(1, mssv);
            stmGetPhieu.setInt(2, 2021);
            stmGetPhieu.setInt(3, 3);
            ResultSet rsPhieu = stmGetPhieu.executeQuery();
            int maPhieu = 0;
            if(rsPhieu.next())
                maPhieu = rsPhieu.getInt(1);
            
            String sqlXoaMonHocDangKy = "DELETE FROM monhocdangky WHERE maPhieu=?";
            PreparedStatement stmXoaMonHocDangKy = this.conn.prepareStatement(sqlXoaMonHocDangKy);
            stmXoaMonHocDangKy.setInt(1, maPhieu);
            stmXoaMonHocDangKy.executeUpdate();
            
            String sqlXoaPhieuDangKy = "DELETE FROM phieu_dkmh WHERE maPhieu=?";
            PreparedStatement stmXoaPhieuDangKy = this.conn.prepareStatement(sqlXoaPhieuDangKy);
            stmXoaPhieuDangKy.setInt(1, maPhieu);
            int kq = stmXoaPhieuDangKy.executeUpdate();
            return kq > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DKMHService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
   }
}
