/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pojo;

/**
 *
 * @author dell
 */
public class User {

    /**
     * @return the taiKhoan
     */
    public String getTaiKhoan() {
        return taiKhoan;
    }

    /**
     * @param taiKhoan the taiKhoan to set
     */
    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    /**
     * @return the matKhau
     */
    public String getMatKhau() {
        return matKhau;
    }

    /**
     * @param matKhau the matKhau to set
     */
    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    /**
     * @return the matKhauCap2
     */
    public String getMatKhauCap2() {
        return matKhauCap2;
    }

    /**
     * @param matKhauCap2 the matKhauCap2 to set
     */
    public void setMatKhauCap2(String matKhauCap2) {
        this.matKhauCap2 = matKhauCap2;
    }

    public int getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(int maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    /**
     * @return the quyenHan
     */
    public int getQuyenHan() {
        return quyenHan;
    }

    /**
     * @param quyenHan the quyenHan to set
     */
    public void setQuyenHan(int quyenHan) {
        this.quyenHan = quyenHan;
    }
    private int maNguoiDung, quyenHan, maTaiKhoan;
    private String taiKhoan, matKhau, matKhauCap2;

    /**
     * @return the maTaiKhoan
     */
    public int getMaTaiKhoan() {
        return maTaiKhoan;
    }

    /**
     * @param maTaiKhoan the maTaiKhoan to set
     */
    public void setMaTaiKhoan(int maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }
    
}
