package com.example.duanmau_ph45955.ui;

import static android.view.View.GONE;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.duanmau_ph45955.R;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    int chucVu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Home");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        toolbar.setPopupTheme(R.style.DarkPopupMenu);

        // Ánh xạ tất cả các nút
        findViewById(R.id.lnDoanhThu).setOnClickListener(this);
        findViewById(R.id.lnKhachHang).setOnClickListener(this);
        findViewById(R.id.lnTopKhachHang).setOnClickListener(this);
        findViewById(R.id.lnDanhMuc).setOnClickListener(this);
        findViewById(R.id.lnSanPham).setOnClickListener(this);
        findViewById(R.id.lnNhanVien).setOnClickListener(this);
        findViewById(R.id.lnHoaDon).setOnClickListener(this);
        findViewById(R.id.lnTopSanPham).setOnClickListener(this);
        findViewById(R.id.lnDoiMatKhau).setOnClickListener(this);
        findViewById(R.id.lnDangXuat).setOnClickListener(this);

        // NHẬN DỮ LIỆU CHỨC VỤ (1: Admin, 0: Nhân viên)
        chucVu = getIntent().getIntExtra("CHUC_VU", 0);

        // PHÂN QUYỀN GIAO DIỆN
        if (chucVu == 0) {
            // Nếu là NHÂN VIÊN: Ẩn các mục quản lý và thống kê
            findViewById(R.id.lnNhanVien).setVisibility(GONE);
            findViewById(R.id.lnDoanhThu).setVisibility(GONE);
            findViewById(R.id.lnTopKhachHang).setVisibility(GONE);
            findViewById(R.id.lnTopSanPham).setVisibility(GONE);

            // Nếu bạn có một layout cha bao quanh các mục thống kê
            if (findViewById(R.id.lnThongKe) != null) {
                findViewById(R.id.lnThongKe).setVisibility(GONE);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        // ẨN MENU ITEM NẾU LÀ NHÂN VIÊN
        if (chucVu == 0) {
            int[] itemsToHide = {R.id.menu_doanh_thu, R.id.menu_top_san_pham, R.id.menu_top_khach_hang};
            for (int id : itemsToHide) {
                MenuItem item = menu.findItem(id);
                if (item != null) item.setVisible(false);
            }
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_doanh_thu) {
            startActivity(new Intent(this, ThongKeDoanhThuActivity.class));
            return true;
        } else if (id == R.id.menu_top_san_pham) {
            startActivity(new Intent(this, ThongKeSanPhamActivity.class));
            return true;
        } else if (id == R.id.menu_top_khach_hang) {
            // ĐÃ SỬA: Mở đúng ThongKeKhachHangActivity
            startActivity(new Intent(this, ThongKeKhachHangActivity.class));
            return true;
        } else if (id == R.id.menu_doi_mat_khau) {
            startActivity(new Intent(this, DoiMatKhauActivity.class));
            return true;
        } else if (id == R.id.menu_dang_xuat) {
            dangXuat();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        // Nhóm chức năng chung cho cả Admin và Nhân viên
        if (id == R.id.lnSanPham) {
            startActivity(new Intent(this, QuanLySanPhamActivity.class));
        } else if (id == R.id.lnKhachHang) {
            startActivity(new Intent(this, QuanLyKhachHangActivity.class));
        } else if (id == R.id.lnHoaDon) {
            startActivity(new Intent(this, QuanLyHoaDonActivity.class));
        } else if (id == R.id.lnDanhMuc) {
            startActivity(new Intent(this, QuanLyDanhMucActivity.class));
        } else if (id == R.id.lnDoiMatKhau) {
            startActivity(new Intent(this, DoiMatKhauActivity.class));
        } else if (id == R.id.lnDangXuat) {
            dangXuat();
        }
        // Nhóm chức năng CHỈ DÀNH CHO ADMIN
        else if (chucVu == 1) {
            if (id == R.id.lnDoanhThu) {
                startActivity(new Intent(this, ThongKeDoanhThuActivity.class));
            } else if (id == R.id.lnTopSanPham) {
                startActivity(new Intent(this, ThongKeSanPhamActivity.class));
            } else if (id == R.id.lnTopKhachHang) {
                startActivity(new Intent(this, ThongKeKhachHangActivity.class));
            } else if (id == R.id.lnNhanVien) {
                startActivity(new Intent(this, QuanLyNhanVienActivity.class));
            }
        }
    }

    private void dangXuat() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}