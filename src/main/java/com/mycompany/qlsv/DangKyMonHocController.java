/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.qlsv;

import com.mycompany.pojo.DKMH;
import com.mycompany.pojo.Lop;
import com.mycompany.service.DKMHService;
import com.mycompany.service.JdbcUtils;
import com.mycompany.service.LopService;
import com.mycompany.service.Utils;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;


/**
 *
 * @author dell
 */
public class DangKyMonHocController implements Initializable{

    public List<DKMH> getDsMonHocDangKy() {
        return dsMonHocDangKy;
    }
    @FXML
    private TableView<DKMH> tbDKMH;
    @FXML
    private TableView<DKMH> tbTKB;
    @FXML
    private ComboBox<Lop> cbbLop;
    @FXML
    private Label lbTKB;
    @FXML
    private AnchorPane a;
    
    private static List<DKMH> dsMonHocDangKy = new ArrayList<>();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DangNhapController dnctrl = new DangNhapController();
        LopService l = new LopService();
        try {
            cbbLop.setItems(FXCollections.observableList(l.getDsLopByKhoa(dnctrl.getSv().getKhoa())));
        } catch (SQLException ex) {
            Logger.getLogger(DangKyMonHocController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            loadColumns();
        } catch (SQLException ex) {
            Logger.getLogger(DangKyMonHocController.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
        loadTableTKBColumns();
    }
    
    private void loadColumns() throws SQLException {
        TableColumn colMaMH = new TableColumn("Mã môn học");
        colMaMH.setPrefWidth(80);
        colMaMH.setCellValueFactory(new PropertyValueFactory("maMonHoc"));
        TableColumn colTenMH = new TableColumn("Tên môn học");
        colTenMH.setPrefWidth(250);
        colTenMH.setCellValueFactory(new PropertyValueFactory("tenMonHoc"));
        TableColumn colCBGV = new TableColumn("CBGV");
        colCBGV.setPrefWidth(180);
        colCBGV.setCellValueFactory(new PropertyValueFactory("cbgv"));
        TableColumn colSoTC = new TableColumn("Số TC");
        colSoTC.setPrefWidth(40);
        colSoTC.setCellValueFactory(new PropertyValueFactory("soTinChi"));
        TableColumn colThu = new TableColumn("Thứ");
        colThu.setPrefWidth(30);
        colThu.setCellValueFactory(new PropertyValueFactory("thu"));
        TableColumn colTietBD = new TableColumn("Tiết BĐ");
        colTietBD.setPrefWidth(50);
        colTietBD.setCellValueFactory(new PropertyValueFactory("tietBatDau"));
        TableColumn colSoTiet = new TableColumn("Số tiết");
        colSoTiet.setPrefWidth(50);
        colSoTiet.setCellValueFactory(new PropertyValueFactory("soTiet"));
        TableColumn colPhong = new TableColumn("Phòng");
        colPhong.setPrefWidth(50);
        colPhong.setCellValueFactory(new PropertyValueFactory("phong"));
        TableColumn colTuanHoc = new TableColumn("Tuần học");
        colTuanHoc.setPrefWidth(135);
        colTuanHoc.setCellValueFactory(new PropertyValueFactory("tuanHoc"));
        
        TableColumn colAction = new TableColumn("");
        colAction.setPrefWidth(30);
        colAction.setCellFactory(obj -> {
            CheckBox cb_DangKy = new CheckBox();
            cb_DangKy.setOnAction(evt -> {
                int soTinChi = 0;
                this.tbTKB.setVisible(true);
                this.lbTKB.setVisible(true);
                CheckBox cb = (CheckBox) evt.getSource();
                TableCell cell = (TableCell) cb.getParent();
                DKMH mhdk = (DKMH)cell.getTableRow().getItem();
                if(cb_DangKy.isSelected()){
                    if(getDsMonHocDangKy() == null)
                        getDsMonHocDangKy().add(mhdk);
                    else{
                        int i = 0; 
                        for(DKMH m : getDsMonHocDangKy()){
                            if(m.getMaMonHoc() == mhdk.getMaMonHoc()){
                                Utils.getBox("Không được đăng ký 2 môn học giống nhau cùng 1 học kỳ!!!", Alert.AlertType.ERROR).show();
                                cb_DangKy.setSelected(false);
                                i++;
                            }
                        }
                        if(i == 0)
                            getDsMonHocDangKy().add(mhdk);
                    }




                    for(DKMH m : getDsMonHocDangKy()){
                        soTinChi += m.getSoTinChi();
                    }
                    if(soTinChi > 24){
                        getDsMonHocDangKy().remove(mhdk);
                        Utils.getBox("Không được đăng ký quá 24 tín chỉ!!!", Alert.AlertType.ERROR).show();
                        cb_DangKy.setSelected(false);
                    }
                }
                else
                    getDsMonHocDangKy().remove(mhdk);
                this.tbTKB.setItems(FXCollections.observableArrayList(getDsMonHocDangKy()));
               
                });
            TableCell cell = new TableCell();
            cell.setGraphic(cb_DangKy);
            return cell; 
        });
        
        this.tbDKMH.getColumns().addAll(colAction ,colMaMH, colTenMH, colCBGV, colSoTC, colThu,
                colTietBD, colSoTiet, colPhong, colTuanHoc);
    }
    
    public void loadMonHoc() throws SQLException{
        
        try (Connection conn = JdbcUtils.getConnect()) {
            DKMHService s = new DKMHService(conn) ;           
            tbDKMH.setItems(FXCollections.observableArrayList(s.getDKMH(this.cbbLop.getSelectionModel().getSelectedItem().getMaLop())));
        }
        
    }
    
    public void btnXem_Click(ActionEvent evt) {        
        try {
            loadMonHoc();
        } catch (SQLException ex) {
            Logger.getLogger(DangKyMonHocController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    @FXML
    private void getMainMenuView(ActionEvent evt) throws IOException{
        Utils.closeStage(a);
        Utils.moveTo(evt, "MainMenu", 900, 700);
    }
    
    private void loadTableTKBColumns(){
        TableColumn colMaMH = new TableColumn("Mã môn học");
        colMaMH.setPrefWidth(80);
        colMaMH.setCellValueFactory(new PropertyValueFactory("maMonHoc"));
        TableColumn colTenMH = new TableColumn("Tên môn học");
        colTenMH.setPrefWidth(130);
        colTenMH.setCellValueFactory(new PropertyValueFactory("tenMonHoc"));
        TableColumn colSoTC = new TableColumn("Số TC");
        colSoTC.setPrefWidth(40);
        colSoTC.setCellValueFactory(new PropertyValueFactory("soTinChi"));
        TableColumn colThu = new TableColumn("Thứ");
        colThu.setPrefWidth(30);
        colThu.setCellValueFactory(new PropertyValueFactory("thu"));
        TableColumn colTietBD = new TableColumn("Tiết BĐ");
        colTietBD.setPrefWidth(50);
        colTietBD.setCellValueFactory(new PropertyValueFactory("tietBatDau"));
        TableColumn colSoTiet = new TableColumn("Số tiết");
        colSoTiet.setPrefWidth(50);
        colSoTiet.setCellValueFactory(new PropertyValueFactory("soTiet"));
        TableColumn colTuanHoc = new TableColumn("Tuần học");
        colTuanHoc.setPrefWidth(135);
        colTuanHoc.setCellValueFactory(new PropertyValueFactory("tuanHoc"));           
        
        this.tbTKB.getColumns().addAll(colMaMH, colTenMH, colSoTC, colThu, colTietBD, colSoTiet, colTuanHoc);
    }   
    
    public void btnXacNhan_Click(ActionEvent evt) {
        if(!getDsMonHocDangKy().isEmpty()){
            Utils.getBox("Xác nhận đăng ký những môn học trên?", Alert.AlertType.CONFIRMATION)
                .showAndWait().ifPresent(b -> {
                if (b == ButtonType.OK) {
                    try {
                        Utils.closeStage(a);
                        Utils.moveTo(evt, "MonHocDangKy", 900, 700);
                    } catch (IOException ex) {
                        Logger.getLogger(DangKyMonHocController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        }
        else
            Utils.getBox("Bạn chưa chọn môn học nào!!!", Alert.AlertType.ERROR).show();
    }
}
