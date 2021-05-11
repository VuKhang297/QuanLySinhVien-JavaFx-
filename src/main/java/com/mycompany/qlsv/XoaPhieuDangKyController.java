/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.qlsv;

import com.mycompany.pojo.MonHocDangKy;
import com.mycompany.qlsv.DangNhapController;
import com.mycompany.service.DKMHService;
import com.mycompany.service.JdbcUtils;
import com.mycompany.service.Utils;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author dell
 */
public class XoaPhieuDangKyController implements Initializable {

    @FXML
    private TableView<MonHocDangKy> tbMonHoc;
    @FXML
    private Label lbHocPhi;
    @FXML
    private PasswordField pfMatKhauCap2;
    @FXML
    private Button btnXoa;
    @FXML
    private AnchorPane a;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadColumns();
        } catch (SQLException ex) {
            Logger.getLogger(XoaPhieuDangKyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            loadDsMonHocDangKy();
        } catch (SQLException ex) {
            Logger.getLogger(XoaPhieuDangKyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void getMainMenuView(ActionEvent evt) throws IOException{
        Utils.closeStage(a);
        Utils.moveTo(evt, "MainMenu", 900, 600);
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
    
    private void loadDsMonHocDangKy() throws SQLException {
        DKMHService dkmhsv = new DKMHService(JdbcUtils.getConnect());
        DangNhapController dnctrl = new DangNhapController();
        int maPhieu = dkmhsv.getMaPhieuByMssv(dnctrl.getSv().getMssv(), 2021, 3);
        tbMonHoc.setItems(FXCollections.observableArrayList(dkmhsv.getDsMonHocDaDangKy(maPhieu)));
    }
    
    @FXML
    private void btnXoa_Click(ActionEvent evt) throws SQLException{
        Utils.getBox("Ban chac chan xoa khong?", Alert.AlertType.CONFIRMATION).showAndWait().ifPresent(b -> {
        if (b == ButtonType.OK) {
            DangNhapController dnctrl = new DangNhapController();
            DKMHService dkmhsv = null;
            try {
                dkmhsv = new DKMHService(JdbcUtils.getConnect());
            } catch (SQLException ex) {
                Logger.getLogger(XoaPhieuDangKyController.class.getName()).log(Level.SEVERE, null, ex);
            }
            int maPhieu = 0;
            try {
                maPhieu = dkmhsv.getMaPhieuByMssv(dnctrl.getSv().getMssv(), 2021, 3);
            } catch (SQLException ex) {
                Logger.getLogger(XoaPhieuDangKyController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if(pfMatKhauCap2.getText().equals(dkmhsv.getMatKhauCap2(dnctrl.getSv().getMssv()))){
                    if(dkmhsv.huyPhieuDangKy(dnctrl.getSv().getMssv(), dkmhsv.getDsMonHocDaDangKy(maPhieu))){
                        Utils.getBox("Đã hủy phiếu đăng ký môn học!!!", Alert.AlertType.INFORMATION).showAndWait();
                        btnXoa.setDisable(true);
                        try {
                            DangKyMonHocController dkmhctrl = new DangKyMonHocController();
                            dkmhctrl.getDsMonHocDangKy().clear();
                            Utils.closeStage(a);
                            Utils.moveTo(evt, "DangKyMonHoc", 900, 700);
                        } catch (IOException ex) {
                            Logger.getLogger(XoaPhieuDangKyController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    else
                        Utils.getBox("Lỗi!!! Không xóa được phiếu đăng ký!!!", Alert.AlertType.ERROR).show();
                }
                else
                    Utils.getBox("Sai mật khẩu!!!", Alert.AlertType.ERROR).show();
                } catch (SQLException ex) {
                    Logger.getLogger(XoaPhieuDangKyController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}