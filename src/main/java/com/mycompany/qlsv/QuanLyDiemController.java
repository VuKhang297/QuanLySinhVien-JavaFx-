package com.mycompany.qlsv;

import com.mycompany.service.MonHocService;
import com.mycompany.pojo.Diem;
import com.mycompany.pojo.Lop;
import com.mycompany.pojo.MonHoc;
import com.mycompany.service.DiemService;
import com.mycompany.service.JdbcUtils;
import com.mycompany.service.LopService;
import com.mycompany.service.SinhVienService;
import com.mycompany.service.Utils;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class QuanLyDiemController implements Initializable{

    @FXML
    private TableView<Diem> tbDiem;
    @FXML
    private ComboBox<Lop> cbbLop;
    @FXML
    private ComboBox<MonHoc> cbbMonHoc;
    @FXML 
    private ComboBox cbbNamHoc;
    @FXML
    private ComboBox cbbHocKy;
    @FXML
    private TextField txtMssv;
    @FXML
    private TextField txtHoTen;
    @FXML
    private TextField txtDiemGK;
    @FXML
    private TextField txtDiemCK;
    private int lop, mon, nam, hk;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadColumns();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyDiemController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection conn = JdbcUtils.getConnect();
            SinhVienService svsv = new SinhVienService(conn);
            List dsNamHoc = new ArrayList<>();
            int i = 2010;
            while(i < Calendar.getInstance().get(Calendar.YEAR)){
                i++;
                dsNamHoc.add(i);
            }
            cbbNamHoc.setItems(FXCollections.observableArrayList(dsNamHoc));
            
            ObservableList<String> list = FXCollections.observableArrayList("1", "2", "3");
            cbbHocKy.setItems(list);
            
            LopService l = new LopService();
            try {
                cbbLop.setItems(FXCollections.observableList(l.getDsLop()));
            } catch (SQLException ex) {
                Logger.getLogger(QuanLyDiemController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            MonHocService mhsv = new MonHocService();
            try {
                cbbMonHoc.setItems(FXCollections.observableArrayList(mhsv.getDsMonHoc()));
            } catch (SQLException ex) {
                Logger.getLogger(QuanLyDiemController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            this.tbDiem.setRowFactory(evt -> {
                TableRow row = new TableRow();
                row.setOnMouseClicked(e -> {
                    Diem diem = this.tbDiem.getSelectionModel().getSelectedItem();
                    txtMssv.setText(String.valueOf(diem.getMsvv()));
                    try {
                        txtHoTen.setText(svsv.getHoTenByMssv(diem.getMsvv()));
                    } catch (SQLException ex) {
                        Logger.getLogger(QuanLyDiemController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    txtDiemGK.setText(String.valueOf(diem.getDiemGK()));
                    txtDiemCK.setText(String.valueOf(diem.getDiemCK()));
                });
                
                return row;
            });
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyDiemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadColumns() throws SQLException {
        TableColumn colMssv = new TableColumn("Mssv");
        colMssv.setCellValueFactory(new PropertyValueFactory("msvv"));
        TableColumn colHoTen = new TableColumn("Họ và tên");
        colHoTen.setPrefWidth(200);
        colHoTen.setCellValueFactory(new PropertyValueFactory("tenSinhVien"));
        TableColumn colGK = new TableColumn("Giữa kì");
        colGK.setCellValueFactory(new PropertyValueFactory("diemGK"));
        TableColumn colCK = new TableColumn("Cuối kì");
        colCK.setCellValueFactory(new PropertyValueFactory("diemCK"));
      
        this.tbDiem.getColumns().addAll(colMssv, colHoTen, colGK, colCK);
    }
    
    @FXML
    private void traCuu(MouseEvent event){
        Diem diem = new Diem();
        lop = cbbLop.getSelectionModel().getSelectedItem().getMaLop();        
        mon = cbbMonHoc.getSelectionModel().getSelectedItem().getMaMonHoc();
        diem.setMonHoc(mon);
        nam = Integer.valueOf(cbbNamHoc.getSelectionModel().getSelectedItem().toString());        
        diem.setNamHoc(nam);
        hk = Integer.valueOf(cbbHocKy.getSelectionModel().getSelectedItem().toString());
        diem.setHocKy(hk);
        
        try {
            loadDiem();
        } catch (SQLException ex) {
            System.out.println("ERROR" + ex);
            Logger.getLogger(QuanLyDiemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void loadDiem() throws SQLException {
        try (Connection conn = JdbcUtils.getConnect()) {
            DiemService d = new DiemService(conn);
            
            lop = cbbLop.getSelectionModel().getSelectedItem().getMaLop();
            mon = cbbMonHoc.getSelectionModel().getSelectedItem().getMaMonHoc();
            nam = Integer.valueOf(cbbNamHoc.getSelectionModel().getSelectedItem().toString());
            hk = Integer.valueOf(cbbHocKy.getSelectionModel().getSelectedItem().toString());
            
            tbDiem.setItems(FXCollections.observableArrayList(d.getDiems(lop, mon, nam, hk)));
        }
    }
    
    @FXML
    private void saveDiem(MouseEvent event) throws SQLException{
        Diem diem = tbDiem.getSelectionModel().getSelectedItem();
        
        if(Double.valueOf(txtDiemGK.getText()) <= 10.0 && Double.valueOf(txtDiemGK.getText()) >= 0 &&
            Double.valueOf(txtDiemCK.getText()) <= 10.0 && Double.valueOf(txtDiemCK.getText()) >= 0){
            diem.setMsvv(Integer.valueOf(txtMssv.getText()));
            diem.setDiemGK(Double.valueOf(txtDiemGK.getText()));
            diem.setDiemCK(Double.valueOf(txtDiemCK.getText()));


            mon = cbbMonHoc.getSelectionModel().getSelectedItem().getMaMonHoc();
            diem.setMonHoc(mon);
            nam = Integer.valueOf(cbbNamHoc.getSelectionModel().getSelectedItem().toString());        
            diem.setNamHoc(nam);
            hk = Integer.valueOf(cbbHocKy.getSelectionModel().getSelectedItem().toString());
            diem.setHocKy(hk);
            try (Connection conn = JdbcUtils.getConnect()) {
                DiemService dsv = new DiemService(conn);
                if (dsv.capNhatDiem(diem) == true) {
                    Utils.getBox("SUCCESSFUL", Alert.AlertType.INFORMATION).show();
                    loadDiem();
                } else
                    Utils.getBox("FAILED", Alert.AlertType.ERROR).show();
            }
        }
        else
            Utils.getBox("Nhập điểm không hợp lệ!!", Alert.AlertType.ERROR).show();
    }
}