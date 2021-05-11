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
public class Lop {

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

    public int getMaLop() {
        return maLop;
    }

    public int getSiSo() {
        return siSo;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setMaLop(int maLop) {
        this.maLop = maLop;
    }

    public void setSiSo(int siSo) {
        this.siSo = siSo;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }
    private int maLop, siSo, nganh;
    private String tenLop;
    
    @Override
    public String toString() {
        return this.tenLop;
    }
}
