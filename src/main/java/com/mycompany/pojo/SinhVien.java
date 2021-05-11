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
public class SinhVien {

    /**
     * @return the cmnd
     */
    public String getCmnd() {
        return cmnd;
    }

    /**
     * @param cmnd the cmnd to set
     */
    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }
    public int getMssv() {
        return mssv;
    }

    public int getLop() {
        return lop;
    }

    public int getKhoaHoc() {
        return khoaHoc;
    }

    public boolean getKhoaMssv() {
        return khoaMssv;
    }

    public String getHoTen() {
        return hoTen;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public String getEmail() {
        return email;
    }

    public void setMssv(int mssv) {
        this.mssv = mssv;
    }

    public void setLop(int lop) {
        this.lop = lop;
    }

    public void setKhoaHoc(int khoaHoc) {
        this.khoaHoc = khoaHoc;
    }

    public void setKhoaMssv(boolean khoaMssv) {
        this.khoaMssv = khoaMssv;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    private int mssv, lop, khoaHoc, khoa, nganh;
    private boolean khoaMssv;
    private String hoTen, gioiTinh, email, noiSinh, cmnd;

    public void setNoiSinh(String noiSinh) {
        this.noiSinh = noiSinh;
    }

    public String getNoiSinh() {
        return noiSinh;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }
    private String ngaySinh;
    @Override
    public String toString() {
        return this.gioiTinh;
    }

    /**
     * @return the khoa
     */
    public int getKhoa() {
        return khoa;
    }

    /**
     * @param khoa the khoa to set
     */
    public void setKhoa(int khoa) {
        this.khoa = khoa;
    }

    /**
     * @return the nganh
     */
    public int getNganh() {
        return nganh;
    }

    /**
     * @param nganh the nganh to set
     */
    public void setNganh(int nganh) {
        this.nganh = nganh;
    }
}
