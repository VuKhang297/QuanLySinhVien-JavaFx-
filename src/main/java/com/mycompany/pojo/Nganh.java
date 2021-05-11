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
public class Nganh {

    /**
     * @return the maNganh
     */
    public int getMaNganh() {
        return maNganh;
    }

    /**
     * @param maNganh the maNganh to set
     */
    public void setMaNganh(int maNganh) {
        this.maNganh = maNganh;
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
     * @return the tenNganh
     */
    public String getTenNganh() {
        return tenNganh;
    }

    /**
     * @param tenNganh the tenNganh to set
     */
    public void setTenNganh(String tenNganh) {
        this.tenNganh = tenNganh;
    }
    private int maNganh, khoa;
    private String tenNganh;
    
    @Override
    public String toString(){
        return this.tenNganh;
    }
}
