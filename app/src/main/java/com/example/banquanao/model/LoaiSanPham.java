package com.example.banquanao.model;

public class LoaiSanPham {
    private int ID;
    private String TenLoaiSanPham;
    private String HinhAnhLoaiSanPham;

    public LoaiSanPham() {
    }

    public LoaiSanPham(int ID, String tenLoaiSanPham, String hinhAnhLoaiSanPham) {
        this.ID = ID;
        TenLoaiSanPham = tenLoaiSanPham;
        HinhAnhLoaiSanPham = hinhAnhLoaiSanPham;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenLoaiSanPham() {
        return TenLoaiSanPham;
    }

    public void setTenLoaiSanPham(String tenLoaiSanPham) {
        TenLoaiSanPham = tenLoaiSanPham;
    }

    public String getHinhAnhLoaiSanPham() {
        return HinhAnhLoaiSanPham;
    }

    public void setHinhAnhLoaiSanPham(String hinhAnhLoaiSanPham) {
        HinhAnhLoaiSanPham = hinhAnhLoaiSanPham;
    }
}
