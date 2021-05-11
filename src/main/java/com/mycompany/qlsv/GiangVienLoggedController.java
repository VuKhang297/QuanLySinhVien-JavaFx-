/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.qlsv;

import com.mycompany.service.Utils;
import java.io.IOException;
import java.net.URL;
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
public class GiangVienLoggedController implements Initializable{
    @FXML
    private Label lbUserName;
    @FXML
    private AnchorPane a;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DangNhapController dnctrl = new DangNhapController();
        lbUserName.setText(dnctrl.getGv().getTenGiangVien());
    }
    
    @FXML
    private void getQuanLySinhVienView(ActionEvent evt) throws IOException {
        Utils.closeStage(a);
        Utils.moveTo(evt, "QuanLySinhVien", 900, 600);
    }
    
    @FXML
    private void getQuanLyDiemView(ActionEvent evt) throws IOException {
        Utils.closeStage(a);
        Utils.moveTo(evt, "QuanLyDiem", 900, 600);
    }
    
    @FXML
    private void getDangNhapView(ActionEvent evt) throws IOException{
        Utils.closeStage(a);
        Utils.moveTo(evt, "DangNhap", 600, 400);
    }
    
}
