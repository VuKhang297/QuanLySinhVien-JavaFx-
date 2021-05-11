/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.MonHocDangKy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dell
 */
public class MonHocDangKyService {
    Connection conn;
//    public List<MonHocDangKy> getDsMonHocDangKy() throws SQLException {      
//        String sql = "SELECT * FROM sinhvien WHERE maMonHoc IN ()";
//        PreparedStatement stm = this.conn.prepareStatement(sql);   
//        stm.setString(1, "a");
//        ResultSet rs = stm.executeQuery();
//        List<MonHocDangKy> dsMonHocDangKy = new ArrayList<>();
//        while (rs.next()) {
//            MonHocDangKy mhdk = new MonHocDangKy();
//            mhdk.setMaMonHoc(rs.getInt("maMonHoc"));
//            mhdk.setTenMonHoc(rs.getString("hoTen"));
//            mhdk.setSoTinChi(rs.getInt("soTinChi"));
//            mhdk.setHocPhi(rs.getBigDecimal("giaTien"));
//            
//            dsMonHocDangKy.add(mhdk);
//        }
//        
//        return dsMonHocDangKy;
//        
//    }
}
