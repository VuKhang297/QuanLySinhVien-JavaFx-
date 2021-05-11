/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.qlsv;

import com.mycompany.pojo.Diem;
import com.mycompany.service.DiemService;
import com.mycompany.service.JdbcUtils;
import com.mycompany.service.Utils;
import java.io.IOException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author dell
 */
public class XemDiemThiController implements Initializable{

    @FXML
    private TableView<Diem> tbDiem;
    @FXML
    private Label lbTen;
    @FXML
    private Label lbMssv;
    @FXML
    private AnchorPane a;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DangNhapController dnctrl = new DangNhapController();
        lbTen.setText(dnctrl.getSv().getHoTen());
        lbMssv.setText(String.valueOf(dnctrl.getSv().getMssv()));
        
        loadColumns();
        try {
            loadDiem();
        } catch (SQLException ex) {
            Logger.getLogger(XemDiemThiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadColumns(){
        TableColumn colTenMH = new TableColumn("Tên môn học");
        colTenMH.setCellValueFactory(new PropertyValueFactory("tenMH"));
        TableColumn colDiemGK = new TableColumn("Điểm giữa kỳ");
        colDiemGK.setCellValueFactory(new PropertyValueFactory("diemGK"));
        TableColumn colDiemCK = new TableColumn("Điểm cuối kỳ");
        colDiemCK.setCellValueFactory(new PropertyValueFactory("diemCK"));
        TableColumn colNamHoc = new TableColumn("Năm học");
        colNamHoc.setCellValueFactory(new PropertyValueFactory("namHoc"));
        TableColumn colHocKy = new TableColumn("Học kỳ");
        colHocKy.setCellValueFactory(new PropertyValueFactory("hocKy"));
        
        this.tbDiem.getColumns().addAll(colTenMH, colNamHoc, colHocKy, colDiemGK, colDiemCK);
    }
    
    private void loadDiem() throws SQLException {
        Connection conn = JdbcUtils.getConnect();
        DiemService dsv = new DiemService(conn);
        DangNhapController dnctrl = new DangNhapController();
        this.tbDiem.setItems(FXCollections.observableArrayList(dsv.getDiemByMssv(dnctrl.getSv().getMssv())));
    }
    
    @FXML
    private void getDangNhapView(ActionEvent evt) throws IOException{
        Utils.closeStage(a);
        Utils.moveTo(evt, "DangNhap", 600, 400);
    }
    
    @FXML
    private void getMainMenuView(ActionEvent evt) throws IOException{
        Utils.closeStage(a);
        Utils.moveTo(evt, "MainMenu", 900, 700);
    }
}