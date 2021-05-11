/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public class UserService {
    private Connection conn;
    
    public UserService(Connection conn) {
        this.conn = conn;
    }
    
    public User getUser(String taiKhoan, String matKhau) throws SQLException{
        String sql = "SELECT * FROM quanlysinhvien.account WHERE taiKhoan =? and matKhau =?";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setString(1, taiKhoan);
        stm.setString(2, matKhau);
        ResultSet rs = stm.executeQuery();
        User user = new User();
        if(rs.next()){
            user.setMaTaiKhoan(rs.getInt("maTaiKhoan"));
            user.setMaNguoiDung(rs.getInt("maNguoiDung"));
            user.setTaiKhoan(rs.getString("taiKhoan"));
            user.setMatKhau(rs.getString("matKhau"));
            user.setMatKhauCap2(rs.getString("matKhauCap2"));
            user.setQuyenHan(rs.getInt("quyenHan"));
        }
        else
            user = null;
        
        return user;
    }
    
    public boolean taoTaiKhoan(User user) {
        try {
            String sql = "INSERT INTO account(maNguoiDung, taiKhoan, matKhau, matKhauCap2, quyenHan) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement stm = this.conn.prepareStatement(sql);
            stm.setInt(1, user.getMaNguoiDung());
            stm.setString(2, user.getTaiKhoan());
            stm.setString(3, user.getMatKhau());
            stm.setString(4, user.getMatKhauCap2());
            stm.setInt(5, user.getQuyenHan());

            int kq = stm.executeUpdate();

            return kq > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
