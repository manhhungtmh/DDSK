package com.example.ddsk;

public class Host {
    String tenNguoiToChuc;
    String donViToChuc;
    String email;
    public Host()
    {

    }

    public Host(String tenNguoiToChuc, String donViToChuc, String email) {
        this.tenNguoiToChuc = tenNguoiToChuc;
        this.donViToChuc = donViToChuc;
        this.email = email;
    }

    public String getTenNguoiToChuc() {
        return tenNguoiToChuc;
    }

    public void setTenNguoiToChuc(String tenNguoiToChuc) {
        this.tenNguoiToChuc = tenNguoiToChuc;
    }

    public String getDonViToChuc() {
        return donViToChuc;
    }

    public void setDonViToChuc(String donViToChuc) {
        this.donViToChuc = donViToChuc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
