package com.mycompany.qlsv;

import com.mycompany.pojo.Lop;
import com.mycompany.pojo.SinhVien;
import com.mycompany.service.JdbcUtils;
import com.mycompany.service.LopService;
import com.mycompany.service.SinhVienService;
import com.mycompany.service.Utils;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

public class SinhVienController implements Initializable{
    
    @FXML
    private TextField txtHoTen;
    @FXML
    private CheckBox cbNam;
    @FXML
    private CheckBox cbNu;
    @FXML
    private CheckBox cbKhoaMssv;
    @FXML
    private DatePicker ngaySinhFld;
    @FXML
    private TextField txtNoiSinh;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtKhoaHoc;
    @FXML
    private ComboBox<Lop> cbbLop;
    @FXML
    private Button btnUpdate;

    
    
    @FXML
    private void getAddView(ActionEvent evt) throws IOException{          
        Utils.moveTo(evt, "ThemSinhVien", 391, 425);
    }
    @FXML
    private ComboBox<Lop> cbLop;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection conn = null;
        LopService l = new LopService();
        try {
            cbbLop.setItems(FXCollections.observableList(l.getDsLop()));
        } catch (SQLException ex) {
            Logger.getLogger(ThemSinhVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            loadColumns();
        } catch (SQLException ex) {
            Logger.getLogger(SinhVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            loadSinhVien("");
        } catch (SQLException ex) {
            System.out.println("ERROR" + ex);
            Logger.getLogger(SinhVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
        txt_Mssv.textProperty().addListener((obj) -> {
            try {
                loadSinhVien(txt_Mssv.getText());
            } catch (SQLException ex) {
                Logger.getLogger(SinhVienController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        this.tbSinhVien.setRowFactory(evt -> {
            TableRow row = new TableRow();
            row.setOnMouseClicked(e -> {
                try {
                    btnUpdate.setDisable(false);
                    SinhVien sv = this.tbSinhVien.getSelectionModel().getSelectedItem();
                    txtHoTen.setText(sv.getHoTen());
                    if(sv.getGioiTinh().compareTo("Nam") == 0)
                        cbNam.setSelected(true);
                    else
                        cbNu.setSelected(true);
                    ngaySinhFld.setValue(LocalDate.parse(sv.getNgaySinh()));
                    txtNoiSinh.setText(sv.getNoiSinh());
                    txtEmail.setText(sv.getEmail());
                    txtKhoaHoc.setText(String.valueOf(sv.getKhoaHoc()));
                    if(sv.getKhoaMssv() == true)
                        cbKhoaMssv.setSelected(true);
                    else
                        cbKhoaMssv.setSelected(false);                    
                    LopService ser = new LopService();
                    cbbLop.getSelectionModel().select(ser.getLopById(sv.getLop()));
                    
                } catch (SQLException ex) {
                    Logger.getLogger(SinhVienController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            return row;
        });
    }
    @FXML
    private TextField txt_Mssv;
    @FXML
    private TableView<SinhVien> tbSinhVien;
    
    private void loadColumns() throws SQLException {
        TableColumn colMssv = new TableColumn("Mssv");
        colMssv.setCellValueFactory(new PropertyValueFactory("mssv"));
        TableColumn colHoTen = new TableColumn("Họ và tên");
        colHoTen.setCellValueFactory(new PropertyValueFactory("hoTen"));
        TableColumn colGioiTinh = new TableColumn("Giới tính");
        colGioiTinh.setCellValueFactory(new PropertyValueFactory("gioiTinh"));
        TableColumn colNgaySinh = new TableColumn("Ngày sinh");
        colNgaySinh.setCellValueFactory(new PropertyValueFactory("ngaySinh"));
        TableColumn colNoiSinh = new TableColumn("Nơi sinh");
        colNoiSinh.setCellValueFactory(new PropertyValueFactory("noiSinh"));
        TableColumn colEmail = new TableColumn("Email");
        colEmail.setCellValueFactory(new PropertyValueFactory("email"));
        TableColumn colLop = new TableColumn("Lớp");
        colLop.setCellValueFactory(new PropertyValueFactory("lop"));
        TableColumn colKhoa = new TableColumn("Khóa");
        colKhoa.setCellValueFactory(new PropertyValueFactory("khoaHoc"));
        TableColumn colKhoaMssv = new TableColumn("Khóa Mssv");
        colKhoaMssv.setCellValueFactory(new PropertyValueFactory("khoaMssv"));
        
        TableColumn colAction = new TableColumn("");
        colAction.setCellFactory(obj -> {
            Button btn_Xoa = new Button("Xóa");           
            btn_Xoa.setOnAction(evt -> {
                Utils.getBox("Ban chac chan xoa khong?", Alert.AlertType.CONFIRMATION)
                     .showAndWait().ifPresent(b -> {
                         if (b == ButtonType.OK) {
                             Button bt = (Button) evt.getSource();
                             TableCell cell = (TableCell) bt.getParent();
                             SinhVien sv = (SinhVien)cell.getTableRow().getItem();
                             
                             Connection conn;
                             try {
                                 conn = JdbcUtils.getConnect();
                                 SinhVienService svsv = new SinhVienService(conn);
                                 
                                 if (svsv.xoaSinhVien(sv.getMssv())) {
                                     Utils.getBox("SUCCESSFUL", Alert.AlertType.INFORMATION).show();
                                     loadSinhVien("");
                                 } else
                                     Utils.getBox("FAILED", Alert.AlertType.ERROR).show();
                                 
                                 conn.close();
                             } catch (SQLException ex) {
                                 Logger.getLogger(SinhVienController.class.getName()).log(Level.SEVERE, null, ex);
                             }
                         }
                    });
            });
            
            TableCell cell = new TableCell();
            cell.setGraphic(btn_Xoa);
            return cell;
        });
        
        this.tbSinhVien.getColumns().addAll(colMssv, colHoTen, colGioiTinh, colEmail,
                colNoiSinh, colNgaySinh, colLop, colKhoa, colKhoaMssv, colAction);
    }
    void loadSinhVien(String kw) throws SQLException {
        try (Connection conn = JdbcUtils.getConnect()) {
            SinhVienService s = new SinhVienService(conn);           
            tbSinhVien.setItems(FXCollections.observableArrayList(s.getSinhViens(kw)));
        }
    }
    @FXML
    private void nam(MouseEvent event){
        cbNu.setSelected(false);
    }
    
    @FXML
    private void nu(MouseEvent event){
        cbNam.setSelected(false);
    }
    public void updateHandler(ActionEvent evt) throws SQLException {
        SinhVien sv = this.tbSinhVien.getSelectionModel().getSelectedItem();
        sv.setHoTen(txtHoTen.getText());
        if(cbNam.isSelected())
            sv.setGioiTinh("Nam");
        else{
            if(cbNu.isSelected())
                sv.setGioiTinh("Nữ");
            else
                Utils.getBox("Chưa chọn giới tính!!!", Alert.AlertType.ERROR).show();
        }
        sv.setNgaySinh(String.valueOf(ngaySinhFld.getValue()));
        sv.setNoiSinh(txtNoiSinh.getText());
        sv.setEmail(txtEmail.getText());
        sv.setKhoaHoc(Integer.valueOf(txtKhoaHoc.getText()));
        sv.setLop(this.cbbLop.getSelectionModel().getSelectedItem().getMaLop());
        if(cbKhoaMssv.isSelected())
            sv.setKhoaMssv(true);
        else
            sv.setKhoaMssv(false);
        try (Connection conn = JdbcUtils.getConnect()) {
            SinhVienService svsv = new SinhVienService(conn);
            if (svsv.capNhatSinhVien(sv) == true) {
                Utils.getBox("SUCCESSFUL", Alert.AlertType.INFORMATION).show();
                sv = null;
                clean();
                loadSinhVien("");
            } else
                Utils.getBox("FAILED", Alert.AlertType.ERROR).show();
        }
    }
    private void clean(){
        txtHoTen.setText("");
        cbNam.setSelected(false);
        cbNu.setSelected(false);
        ngaySinhFld.setValue(null);
        txtNoiSinh.setText("");
        txtEmail.setText("");
        txtKhoaHoc.setText("");
        cbKhoaMssv.setSelected(false);
        btnUpdate.setDisable(true);
    }
}
