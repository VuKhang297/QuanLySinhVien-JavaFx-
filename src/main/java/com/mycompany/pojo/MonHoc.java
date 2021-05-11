/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pojo;

import java.math.BigDecimal;

/**
 *
 * @author dell
 */
public class MonHoc {

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
     * @return the tiLeDiemGK
     */
    public int getTiLeDiemGK() {
        return tiLeDiemGK;
    }

    /**
     * @param tiLeDiemGK the tiLeDiemGK to set
     */
    public void setTiLeDiemGK(int tiLeDiemGK) {
        this.tiLeDiemGK = tiLeDiemGK;
    }

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
     * @return the giaTien
     */
    public BigDecimal getGiaTien() {
        return giaTien;
    }

    /**
     * @param giaTien the giaTien to set
     */
    public void setGiaTien(BigDecimal giaTien) {
        this.giaTien = giaTien;
    }
    private int maMonHoc, soTinChi, tiLeDiemGK;
    private String tenMonHoc;
    private BigDecimal giaTien; 
    
    @Override
    public String toString(){
        return this.tenMonHoc;
    }
}
