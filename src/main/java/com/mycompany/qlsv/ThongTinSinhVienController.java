/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.qlsv;

import com.mycompany.service.JdbcUtils;
import com.mycompany.service.Utils;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author dell
 */
public class ThongTinSinhVienController implements Initializable{

    @FXML
    private AnchorPane a;

    @FXML
    private Label lbTen;

    @FXML
    private Label lbMssv;

    @FXML
    private Label lbGioiTinh;

    @FXML
    private Label lbNgaySinh;

    @FXML
    private Label lbNoiSinh;

    @FXML
    private Label lbCmnd;

    @FXML
    private Label lbEmail;

    @FXML
    private Label lbNganh;

    @FXML
    private Label lbLop;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Connection conn = JdbcUtils.getConnect();
            DangNhapController d = new DangNhapController();
            lbTen.setText("Họ và Tên: " + d.getSv().getHoTen());
            if(d.getSv().getKhoaMssv() == false)
                lbMssv.setText("Mssv: " + String.valueOf(d.getSv().getMssv()) + "(Không bị khóa)");
            else
                lbMssv.setText("Mssv" + String.valueOf(d.getSv().getMssv()) + "(Mssv bị khóa)");
            lbGioiTinh.setText("Giới tính: " + d.getSv().getGioiTinh());
            lbNgaySinh.setText("Ngày sinh: " + d.getSv().getNgaySinh());
            lbNoiSinh.setText("Nơi sinh: " + d.getSv().getNoiSinh());
            lbCmnd.setText("CMND: " + d.getSv().getCmnd());
            lbEmail.setText("Email: " + d.getSv().getEmail());
            String sqlGetNganh = "SELECT tenNganh FROM nganh WHERE maNganh =?";
            PreparedStatement stmGetNganh = conn.prepareStatement(sqlGetNganh);
            stmGetNganh.setInt(1, d.getSv().getNganh());
            ResultSet rsGetNganh = stmGetNganh.executeQuery();
            String tenNganh = null;
            if(rsGetNganh.next())
                tenNganh = rsGetNganh.getString(1);
            lbNganh.setText("Ngành: " + tenNganh);
            
            String sqlGetLop = "SELECT tenLop FROM lop WHERE maLop =?";
            PreparedStatement stmGetLop = conn.prepareStatement(sqlGetLop);
            stmGetLop.setInt(1, d.getSv().getLop());
            ResultSet rsGetLop = stmGetLop.executeQuery();
            String tenLop = null;
            if(rsGetLop.next())
                tenLop = rsGetLop.getString(1);
            lbLop.setText("Lớp: " + tenLop);;
        } catch (SQLException ex) {
            Logger.getLogger(ThongTinSinhVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
    private void getMainMenuView(ActionEvent evt) throws IOException{
        Utils.closeStage(a);
        Utils.moveTo(evt, "MainMenu", 624, 472);
    }
    
    
    
}
