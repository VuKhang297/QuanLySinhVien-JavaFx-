/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.qlsv;

import com.mycompany.pojo.Khoa;
import com.mycompany.pojo.Lop;
import com.mycompany.pojo.Nganh;
import com.mycompany.pojo.SinhVien;
import com.mycompany.pojo.User;
import com.mycompany.service.JdbcUtils;
import com.mycompany.service.KhoaService;
import com.mycompany.service.LopService;
import com.mycompany.service.NganhService;
import com.mycompany.service.SinhVienService;
import com.mycompany.service.UserService;
import com.mycompany.service.Utils;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author dell
 */
public class ThemSinhVienController implements Initializable{
    
    @FXML
    private TextField txt_HoTen;
    @FXML
    private CheckBox cb_Nam;
    @FXML
    private CheckBox cb_Nu;
    @FXML
    private DatePicker ngaySinhFld;
    @FXML
    private TextField txt_NoiSinh;
    @FXML
    private TextField txt_CMND;
    @FXML
    private ComboBox<Khoa> cbb_Khoa;
    @FXML
    private ComboBox<Nganh> cbb_Nganh;
    @FXML
    private ComboBox<Lop> cbb_Lop;
    
    @FXML
    private void save(MouseEvent event) throws ParseException, SQLException{
        if((!cb_Nam.isSelected() && !cb_Nu.isSelected()) || txt_HoTen.getText() == null
            || ngaySinhFld.getValue() == null || txt_NoiSinh.getText() == null ||
            cbb_Lop.getSelectionModel().getSelectedItem() == null || txt_CMND.getText() == null || 
                cbb_Khoa.getSelectionModel().getSelectedItem() == null ||
                cbb_Nganh.getSelectionModel().getSelectedItem() == null)
            Utils.getBox("Chưa điền đủ thông tin cần thiết!!!", Alert.AlertType.ERROR).show();
        else{
            try {
                SinhVien sv = new SinhVien();
                Connection conn = JdbcUtils.getConnect();
                SinhVienService svsv = new SinhVienService(conn);
                sv.setHoTen(txt_HoTen.getText());
                if(cb_Nam.isSelected())
                    sv.setGioiTinh("Nam");
                else
                    if(cb_Nu.isSelected())
                        sv.setGioiTinh("Nữ");
                sv.setNgaySinh(String.valueOf(ngaySinhFld.getValue()));
                sv.setNoiSinh(txt_NoiSinh.getText());
                sv.setCmnd(txt_CMND.getText());
                sv.setKhoa(cbb_Khoa.getSelectionModel().getSelectedItem().getMaKhoa());
                sv.setNganh(cbb_Nganh.getSelectionModel().getSelectedItem().getMaNganh());
                sv.setLop(cbb_Lop.getSelectionModel().getSelectedItem().getMaLop());
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                if (svsv.themSinhVien(sv) == true){
                    String sqlGetMaxMssv = "SELECT MAX(mssv) FROM sinhvien";
                    User user = new User();
                    PreparedStatement stmMax = conn.prepareStatement(sqlGetMaxMssv);
                    ResultSet rs = stmMax.executeQuery();
                    int maxMssv = 0;
                    if(rs.next())
                        maxMssv = rs.getInt(1);
                    user.setTaiKhoan(String.valueOf(maxMssv));
                    user.setMaNguoiDung(maxMssv);
                    user.setMatKhau(txt_CMND.getText());
                    user.setMatKhauCap2(txt_CMND.getText());
                    user.setQuyenHan(1);
                    String name = Utils.removeAccent(sv.getHoTen()).trim().toLowerCase();
                    String lastName = "";
                    if(name.split("\\w+").length > 1)
                        lastName = name.substring(name.lastIndexOf(" ")+1);
                    else
                        lastName = name;
                    String sql = "UPDATE sinhvien SET email=? WHERE mssv=?";
                    String mail = String.valueOf(maxMssv) + lastName + "@ou.edu.vn";
                    PreparedStatement stm = conn.prepareStatement(sql);
                    stm.setString(1, mail);
                    stm.setInt(2, maxMssv);
                    stm.executeUpdate();
                    
                    
                    a.setContentText("Thêm sinh viên mới thành công!!!");
                    
                    UserService usv = new UserService(conn);
                    //Tạo tài khoản cho sinh viên vừa thêm vào
                    
                    if(usv.taoTaiKhoan(user) == true)            
                        a.setContentText("Tạo tài khoản cho sinh viên mới thành công!!!");
                    else
                        a.setContentText("Lỗi tạo tài khoản cho sinh viên mới!!!");
                    clean();
                }
                else
                    a.setContentText("Thêm thất bại! Vui lòng kiểm tra lại!");

                a.show();
            } catch (SQLException ex) {
                Logger.getLogger(ThemSinhVienController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        SinhVienController svctrl = new SinhVienController();
        svctrl.loadSinhVien("");
    }
    
    @FXML
    private void nam(MouseEvent event){
        cb_Nu.setSelected(false);
    }
    
    @FXML
    private void nu(MouseEvent event){
        cb_Nam.setSelected(false);
    }
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LopService l = new LopService();
        try {
            cbb_Lop.setItems(FXCollections.observableList(l.getDsLop()));
        } catch (SQLException ex) {
            Logger.getLogger(ThemSinhVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        KhoaService k = new KhoaService();
        try {
            cbb_Khoa.setItems(FXCollections.observableList(k.getDsKhoa()));
        } catch (SQLException ex) {
            Logger.getLogger(ThemSinhVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cbb_KhoaAcTionPerfomed(ActionEvent evt) throws SQLException{
        NganhService n = new NganhService();
        cbb_Nganh.setItems(FXCollections.observableList(n.getDsNganhByKhoa(cbb_Khoa.getSelectionModel().getSelectedItem().getMaKhoa())));
    }
    
    public void cbb_NganhAcTionPerfomed(ActionEvent evt) throws SQLException{
        LopService l = new LopService();
        cbb_Lop.setItems(FXCollections.observableList(l.getDsLopByNganh(cbb_Nganh.getSelectionModel().getSelectedItem().getMaNganh())));
    }
    
    @FXML
    private void clean(MouseEvent evt){
        txt_HoTen.setText("");
        cb_Nam.setSelected(false);
        cb_Nu.setSelected(false);
        ngaySinhFld.setValue(null);
        ngaySinhFld.setPromptText("");
        txt_NoiSinh.setText("");
        txt_CMND.setText("");
    }
    
    private void clean(){
        txt_HoTen.setText("");
        cb_Nam.setSelected(false);
        cb_Nu.setSelected(false);
        ngaySinhFld.setValue(null);
        ngaySinhFld.setPromptText("");
        txt_NoiSinh.setText("");
        txt_CMND.setText("");
    }

}
