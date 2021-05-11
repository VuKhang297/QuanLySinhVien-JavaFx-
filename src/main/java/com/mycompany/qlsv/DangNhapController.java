/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.qlsv;

import com.mycompany.pojo.GiangVien;
import com.mycompany.pojo.SinhVien;
import com.mycompany.pojo.User;
import com.mycompany.service.GiangVienService;
import com.mycompany.service.JdbcUtils;
import com.mycompany.service.SinhVienService;
import com.mycompany.service.UserService;
import com.mycompany.service.Utils;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author dell
 */
public class DangNhapController{

    public SinhVien getSv() {
        return sv;
    }

    public void setSv(SinhVien s) {
        sv = s;
    }

    @FXML
    private TextField txtTaiKhoan;
    @FXML
    private PasswordField pfMatKhau;
    @FXML
    private AnchorPane a;
    
    private static SinhVien sv = new SinhVien();
    private static GiangVien gv = new GiangVien();
    
    public void dangNhap(ActionEvent evt) throws SQLException, IOException{
            Connection conn = JdbcUtils.getConnect();
            UserService usv = new UserService(conn);
            User user = new User();
            SinhVienService svsv = new SinhVienService(conn);
            GiangVienService gvsv = new GiangVienService(conn);
            if(usv.getUser(txtTaiKhoan.getText(), pfMatKhau.getText()) != null){
                user = usv.getUser(txtTaiKhoan.getText(), pfMatKhau.getText());
                if(user.getQuyenHan() == 1){
                    setSv(svsv.getSinhVienByMssv(user.getMaNguoiDung()));
                    Utils.closeStage(a);
                    Utils.moveTo(evt, "MainMenu" , 900, 600);
                }
                if(user.getQuyenHan() == 2){
                    setGv(gvsv.getGiangVienById(user.getMaNguoiDung()));
                    Utils.closeStage(a);
                    Utils.moveTo(evt, "GiangVienLogged" , 900, 600);
                }
            }
            else
                Utils.getBox("Tài khoản hoặc mật khẩu không chính xác!!!", Alert.AlertType.ERROR).show();
    }

    public GiangVien getGv() {
        return gv;
    }

    public void setGv(GiangVien aGv) {
        gv = aGv;
    }
}
