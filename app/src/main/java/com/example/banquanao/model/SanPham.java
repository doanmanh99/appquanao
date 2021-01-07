package com.example.banquanao.model;

import java.io.Serializable;

public class SanPham implements Serializable {
    private int ID;
    private String TenSanPham;
    private int GiaSanPham;
    private String AnhSanPham;
    private String MoTaSanPham;
    private int IDLoaiSanPham;

    public SanPham() {
    }

    public SanPham(int ID, String tenSanPham, int giaSanPham, String anhSanPham, String moTaSanPham, int IDLoaiSanPham) {
        this.ID = ID;
        TenSanPham = tenSanPham;
        GiaSanPham = giaSanPham;
        AnhSanPham = anhSanPham;
        MoTaSanPham = moTaSanPham;
        this.IDLoaiSanPham = IDLoaiSanPham;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        TenSanPham = tenSanPham;
    }

    public int getGiaSanPham() {
        return GiaSanPham;
    }

    public void setGiaSanPham(int giaSanPham) {
        GiaSanPham = giaSanPham;
    }

    public String getAnhSanPham() {
        return AnhSanPham;
    }

    public void setAnhSanPham(String anhSanPham) {
        AnhSanPham = anhSanPham;
    }

    public String getMoTaSanPham() {
        return MoTaSanPham;
    }

    public void setMoTaSanPham(String moTaSanPham) {
        MoTaSanPham = moTaSanPham;
    }

    public int getIDLoaiSanPham() {
        return IDLoaiSanPham;
    }

    public void setIDLoaiSanPham(int IDLoaiSanPham) {
        this.IDLoaiSanPham = IDLoaiSanPham;
    }
}
