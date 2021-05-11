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
public class Diem {

    /**
     * @return the tenMH
     */
    public String getTenMH() {
        return tenMH;
    }

    /**
     * @param tenMH the tenMH to set
     */
    public void setTenMH(String tenMH) {
        this.tenMH = tenMH;
    }

    /**
     * @return the maDiem
     */
    public int getMaDiem() {
        return maDiem;
    }

    /**
     * @param maDiem the maDiem to set
     */
    public void setMaDiem(int maDiem) {
        this.maDiem = maDiem;
    }

    /**
     * @return the msvv
     */
    public int getMsvv() {
        return msvv;
    }

    /**
     * @param msvv the msvv to set
     */
    public void setMsvv(int msvv) {
        this.msvv = msvv;
    }

    /**
     * @return the monHoc
     */
    public int getMonHoc() {
        return monHoc;
    }

    /**
     * @param monHoc the monHoc to set
     */
    public void setMonHoc(int monHoc) {
        this.monHoc = monHoc;
    }

    /**
     * @return the hocKy
     */
    public int getHocKy() {
        return hocKy;
    }

    /**
     * @param hocKy the hocKy to set
     */
    public void setHocKy(int hocKy) {
        this.hocKy = hocKy;
    }

    /**
     * @return the namHoc
     */
    public int getNamHoc() {
        return namHoc;
    }

    /**
     * @param namHoc the namHoc to set
     */
    public void setNamHoc(int namHoc) {
        this.namHoc = namHoc;
    }

    /**
     * @return the diemGK
     */
    public double getDiemGK() {
        return diemGK;
    }

    /**
     * @param diem the diemGK to set
     */
    public void setDiemGK(double diemGK) {
        this.diemGK = diemGK;
    }
    
    /**
     * @return the diemGK
     */
    public double getDiemCK() {
        return diemCK;
    }

    /**
     * @param diem the diemCK to set
     */
    public void setDiemCK(double diemCK) {
        this.diemCK = diemCK;
    }
    private int maDiem, msvv, monHoc, hocKy, namHoc;
    private double diemGK, diemCK;
    private String tenMH, tenSinhVien;

    /**
     * @return the tenSinhVien
     */
    public String getTenSinhVien() {
        return tenSinhVien;
    }

    /**
     * @param tenSinhVien the tenSinhVien to set
     */
    public void setTenSinhVien(String tenSinhVien) {
        this.tenSinhVien = tenSinhVien;
    }
}
