/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.qlsv;

import com.mycompany.pojo.DKMH;
import com.mycompany.service.DKMHService;
import com.mycompany.service.JdbcUtils;
import com.mycompany.service.Utils;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author dell
 */
public class MonHocDangKyController implements Initializable{

    @FXML
    private TableView<DKMH> tbMonHoc;
    @FXML
    private Label lbHocPhi;
    @FXML
    private PasswordField pfMatKhauCap2;
    @FXML
    private Button btnXacNhan;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnXacNhan.setDisable(false);
        try {
            loadColumns();
        } catch (SQLException ex) {
            Logger.getLogger(MonHocDangKyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadDsMonHocDangKy();
    }
    
    private void loadColumns() throws SQLException {
        TableColumn colMaMH = new TableColumn("Mã môn học");
        colMaMH.setCellValueFactory(new PropertyValueFactory("maMonHoc"));
        TableColumn colTenMH = new TableColumn("Tên môn học");
        colTenMH.setCellValueFactory(new PropertyValueFactory("tenMonHoc"));
        TableColumn colSoTinChi = new TableColumn("Số tín chỉ");
        colSoTinChi.setCellValueFactory(new PropertyValueFactory("soTinChi"));
        TableColumn colHocPhi = new TableColumn("Học phí");
        colHocPhi.setCellValueFactory(new PropertyValueFactory("hocPhi"));
        
        this.tbMonHoc.getColumns().addAll(colMaMH, colTenMH, colSoTinChi, colHocPhi);
    }
    
    @FXML
    private void btnXacNhan_Click(ActionEvent evt) throws SQLException {
        DangNhapController dnctrl = new DangNhapController();
        DKMHService dkmhsv = new DKMHService(JdbcUtils.getConnect());
        if(pfMatKhauCap2.getText().equals(dkmhsv.getMatKhauCap2(dnctrl.getSv().getMssv()))){            
            if(dkmhsv.dangKyMonHoc(dnctrl.getSv().getMssv()) == true){
                Utils.getBox("Quá trình đăng ký môn học thành công!!!", Alert.AlertType.INFORMATION).show();
                btnXacNhan.setDisable(true);
            }
            else
                Utils.getBox("Đăng ký thất bại!!!", Alert.AlertType.ERROR).show();
        }
        else
            Utils.getBox("Sai mật khẩu!!!", Alert.AlertType.ERROR).show();
    }
    
    private void loadDsMonHocDangKy() {
        BigDecimal tongHocPhi = new BigDecimal("0.0");
        DangKyMonHocController dkmhctrl = new DangKyMonHocController();
        tbMonHoc.setItems(FXCollections.observableArrayList(dkmhctrl.getDsMonHocDangKy()));
        for(DKMH mhdk : dkmhctrl.getDsMonHocDangKy()){
            tongHocPhi = tongHocPhi.add(mhdk.getHocPhi());
        }
        lbHocPhi.setText("Tổng học phí: " + String.valueOf(tongHocPhi) + " VNĐ");
    }
}
