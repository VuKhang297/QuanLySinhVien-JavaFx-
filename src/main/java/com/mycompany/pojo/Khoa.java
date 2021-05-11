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
public class Khoa {

    /**
     * @return the maKhoa
     */
    public int getMaKhoa() {
        return maKhoa;
    }

    /**
     * @param maKhoa the maKhoa to set
     */
    public void setMaKhoa(int maKhoa) {
        this.maKhoa = maKhoa;
    }

    /**
     * @return the tenKhoa
     */
    public String getTenKhoa() {
        return tenKhoa;
    }

    /**
     * @param tenKhoa the tenKhoa to set
     */
    public void setTenKhoa(String tenKhoa) {
        this.tenKhoa = tenKhoa;
    }
    private int maKhoa;
    private String tenKhoa;
    
    @Override
    public String toString() {
        return this.tenKhoa;
    } 
}
