package com.example.duanmau_ph45955.utils;

public class AppContext {
    private static GioHang gioHang;
    public static String maNhanVien;
    public static int chucVu; // Thêm dòng này: 1 là Quản lý, 0 là Nhân viên

    public static GioHang getGioHang() {
        if (gioHang == null) gioHang = new GioHang();
        return gioHang;
    }
    // Hàm để reset giỏ hàng khi đăng xuất (nếu cần)
    public static void resetCart() {
        gioHang = new GioHang();
    }
}

