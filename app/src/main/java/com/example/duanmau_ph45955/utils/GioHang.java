package com.example.duanmau_ph45955.utils;

import com.example.duanmau_ph45955.model.SanPham;

import java.util.ArrayList;
import java.util.List;

public class GioHang {
    private List<GioHangItem> danhSachGioHang;

    public GioHang() {
        danhSachGioHang = new ArrayList<>();
    }

    public void addSanPham(SanPham sp) {
        for (GioHangItem item : danhSachGioHang) {
            if (item.getSanPham().getMaSanPham().equals(sp.getMaSanPham())) {
                item.setSoLuong(item.getSoLuong() + 1);
                return;
            }
        }
        GioHangItem gioHangItem = new GioHangItem(sp, 1);
        danhSachGioHang.add(gioHangItem);
    }

    public List<GioHangItem> getDanhSachGioHang() {
        return danhSachGioHang;
    }
}
