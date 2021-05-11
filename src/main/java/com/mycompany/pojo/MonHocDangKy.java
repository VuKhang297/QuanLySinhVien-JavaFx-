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
public class MonHocDangKy {

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
     * @return the hocPhi
     */
    public BigDecimal getHocPhi() {
        return hocPhi;
    }

    /**
     * @param hocPhi the hocPhi to set
     */
    public void setHocPhi(BigDecimal hocPhi) {
        this.hocPhi = hocPhi;
    }

    /**
     * @return the tongHocPhi
     */
    public BigDecimal getTongHocPhi() {
        return tongHocPhi;
    }

    /**
     * @param tongHocPhi the tongHocPhi to set
     */
    public void setTongHocPhi(BigDecimal tongHocPhi) {
        this.tongHocPhi = tongHocPhi;
    }
    private int maMonHoc, soTinChi;
    private String tenMonHoc;
    private BigDecimal hocPhi, tongHocPhi;
}
