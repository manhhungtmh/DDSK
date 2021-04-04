package com.example.ddsk;

public class Member {
    String tenThanhVien;
    String ngaySinh;
    String soCMND;
    String email;
    public Member()
    {

    }
    public Member(String tenThanhVien, String ngaySinh, String soCMND, String email) {
        this.tenThanhVien = tenThanhVien;
        this.ngaySinh = ngaySinh;
        this.soCMND = soCMND;
        this.email = email;
    }

    public String getTenThanhVien() {
        return tenThanhVien;
    }

    public void setTenThanhVien(String tenThanhVien) {
        this.tenThanhVien = tenThanhVien;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getSoCMND() {
        return soCMND;
    }

    public void setSoCMND(String soCMND) {
        this.soCMND = soCMND;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
