/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.qlsv;

import com.mycompany.service.DKMHService;
import com.mycompany.service.JdbcUtils;
import com.mycompany.service.Utils;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author dell
 */
public class MainMenuController implements Initializable{
    
    @FXML
    private Label lbUserName;
    @FXML
    private AnchorPane a;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DangNhapController dnctrl = new DangNhapController();
        lbUserName.setText(dnctrl.getSv().getHoTen());
    }
    
    @FXML
    private void getDangKyMonHocView(ActionEvent evt) throws IOException, SQLException{
        DKMHService dkmhsv = new DKMHService(JdbcUtils.getConnect());
        DangNhapController dnctrl = new DangNhapController();
        if(dkmhsv.getMaPhieuByMssv(dnctrl.getSv().getMssv(), 2021, 3) == 0){
            Utils.closeStage(a);
            Utils.moveTo(evt, "DangKyMonHoc", 900, 700);
        }
        else{
            Utils.closeStage(a);
            Utils.moveTo(evt, "XoaPhieuDangKy", 900, 700);
        }
        
    }
    
    @FXML
    private void getXemDiemThiView(ActionEvent evt) throws IOException{
        Utils.closeStage(a);
        Utils.moveTo(evt, "XemDiemThi", 900, 700);
    }
    
    @FXML
    private void getDangNhapView(ActionEvent evt) throws IOException{
        Utils.closeStage(a);
        Utils.moveTo(evt, "DangNhap", 600, 400);
    }
    
    @FXML
    private void getThongTinSinhVienView(ActionEvent evt) throws IOException{
        Utils.closeStage(a);
        Utils.moveTo(evt, "ThongTinSinhVien", 755, 655);
    }
}
