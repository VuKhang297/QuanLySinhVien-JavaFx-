/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.MonHoc;
import com.mycompany.service.JdbcUtils;
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
public class MonHocService {
    public List<MonHoc> getDsMonHoc() throws SQLException {
        List<MonHoc> dsMonHoc;
        try (Connection conn = JdbcUtils.getConnect()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM monhoc");
            dsMonHoc = new ArrayList<>();
            while (rs.next()) {
                MonHoc mon = new MonHoc();
                mon.setMaMonHoc(rs.getInt("maMH"));
                mon.setTenMonHoc(rs.getString("tenMH"));
                mon.setSoTinChi(rs.getInt("soTC"));
                mon.setGiaTien(rs.getBigDecimal("hocPhi"));
                mon.setTiLeDiemGK(rs.getInt("tiLeDiemGK"));
                
                dsMonHoc.add(mon);
            }
        }
        return dsMonHoc;
    }
}
