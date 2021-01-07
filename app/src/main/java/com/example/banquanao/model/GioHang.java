package com.example.banquanao.model;

public class GioHang {
    public int IDSP;
    public String TenSP;
    public long GiaSp;
    public String HinhSP;
    public int SoLuongSP;

    public GioHang(int IDSP, String tenSP, long giaSp, String hinhSP, int soLuongSP) {
        this.IDSP = IDSP;
        TenSP = tenSP;
        GiaSp = giaSp;
        HinhSP = hinhSP;
        SoLuongSP = soLuongSP;
    }

    public int getIDSP() {
        return IDSP;
    }

    public void setIDSP(int IDSP) {
        this.IDSP = IDSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public long getGiaSp() {
        return GiaSp;
    }

    public void setGiaSp(long giaSp) {
        GiaSp = giaSp;
    }

    public String getHinhSP() {
        return HinhSP;
    }

    public void setHinhSP(String hinhSP) {
        HinhSP = hinhSP;
    }

    public int getSoLuongSP() {
        return SoLuongSP;
    }

    public void setSoLuongSP(int soLuongSP) {
        SoLuongSP = soLuongSP;
    }
}
