/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pojo;

import java.math.BigDecimal;
import javafx.scene.control.CheckBox;

/**
 *
 * @author dell
 */
public class DKMH {

    /**
     * @return the tenMonHoc
     */
    public String getTenMonHoc() {
        return tenMonHoc;
    }

    /**
     * @param tenMonHoc the tenMonHoc to set
     */
    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    /**
     * @return the cbgv
     */
    public String getCbgv() {
        return cbgv;
    }

    /**
     * @param cbgv the cbgv to set
     */
    public void setCbgv(String cbgv) {
        this.cbgv = cbgv;
    }

    /**
     * @return the phong
     */
    public String getPhong() {
        return phong;
    }

    /**
     * @param phong the phong to set
     */
    public void setPhong(String phong) {
        this.phong = phong;
    }

    /**
     * @return the tuanHoc
     */
    public String getTuanHoc() {
        return tuanHoc;
    }

    /**
     * @param tuanHoc the tuanHoc to set
     */
    public void setTuanHoc(String tuanHoc) {
        this.tuanHoc = tuanHoc;
    }

    /**
     * @return the maMonHoc
     */
    public int getMaMonHoc() {
        return maMonHoc;
    }

    /**
     * @param maMonHoc the maMonHoc to set
     */
    public void setMaMonHoc(int maMonHoc) {
        this.maMonHoc = maMonHoc;
    }

    /**
     * @return the soTinChi
     */
    public int getSoTinChi() {
        return soTinChi;
    }

    /**
     * @param soTinChi the soTinChi to set
     */
    public void setSoTinChi(int soTinChi) {
        this.soTinChi = soTinChi;
    }

    /**
     * @return the thu
     */
    public int getThu() {
        return thu;
    }

    /**
     * @param thu the thu to set
     */
    public void setThu(int thu) {
        this.thu = thu;
    }

    /**
     * @return the tietBatDau
     */
    public int getTietBatDau() {
        return tietBatDau;
    }

    /**
     * @param tietBatDau the tietBatDau to set
     */
    public void setTietBatDau(int tietBatDau) {
        this.tietBatDau = tietBatDau;
    }

    /**
     * @return the soTiet
     */
    public int getSoTiet() {
        return soTiet;
    }

    /**
     * @param soTiet the soTiet to set
     */
    public void setSoTiet(int soTiet) {
        this.soTiet = soTiet;
    }
    
    public CheckBox getCb(){
        return cb;
    }
    
    public void setCb(CheckBox cb){
        this.cb = cb;
    }
    
     private String tenMonHoc, cbgv, phong, tuanHoc;
     private int maMonHoc, soTinChi, thu, tietBatDau, soTiet;
     private CheckBox cb;

    public BigDecimal getHocPhi() {
        return hocPhi;
    }
     private BigDecimal hocPhi;

    public void setHocPhi(BigDecimal hocPhi) {
        this.hocPhi = hocPhi;
    }
}
