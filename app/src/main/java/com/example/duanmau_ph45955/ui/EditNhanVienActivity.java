package com.example.duanmau_ph45955.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duanmau_ph45955.R;
import com.example.duanmau_ph45955.database.DatabaseHelper;
import com.example.duanmau_ph45955.dto.ChucVu;
import com.example.duanmau_ph45955.model.NhanVien;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EditNhanVienActivity extends AppCompatActivity {
    private EditText edtMaNhanVien, edtTenNhanVien, edtDiaChi, edtLuong, edtMatKhau;
    private DatabaseHelper db;
    private int type;
    private List<ChucVu> chucVuList;
    private Spinner spChucVu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_nhan_vien);

        db = new DatabaseHelper(this);
        edtMaNhanVien = findViewById(R.id.edtMaNhanVien);
        edtTenNhanVien = findViewById(R.id.edtTenNhanVien);
        edtDiaChi = findViewById(R.id.edtDiaChi);
        edtLuong = findViewById(R.id.edtLuong);
        edtMatKhau = findViewById(R.id.edtMatKhau);
        spChucVu = findViewById(R.id.spChucVu);
        LinearLayout layoutMaNhanVien = findViewById(R.id.layoutMaNhanVien);

        findViewById(R.id.btnLuu).setOnClickListener(v -> luuNhanVien());
        findViewById(R.id.btnHuy).setOnClickListener(v -> finish());

        // Thiết lập Spinner Chức vụ
        chucVuList = new ArrayList<>();
        chucVuList.add(new ChucVu(0, "Nhân viên"));
        chucVuList.add(new ChucVu(1, "Quản lý"));
        ArrayAdapter<ChucVu> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, chucVuList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spChucVu.setAdapter(adapter);

        type = getIntent().getIntExtra("Type", -1);
        if (type == 0) { // Edit
            edtMaNhanVien.setEnabled(false);
            NhanVien nhanVien = getIntent().getParcelableExtra(QuanLyNhanVienActivity.NHAN_VIEN);
            if (nhanVien != null) {
                edtMaNhanVien.setText(nhanVien.getMaNhanVien());
                edtTenNhanVien.setText(nhanVien.getTenNhanVien());
                edtDiaChi.setText(nhanVien.getDiaChi());
                // Hiển thị lương dạng số nguyên để dễ sửa
                edtLuong.setText(String.valueOf((int)nhanVien.getLuong()));
                edtMatKhau.setText(nhanVien.getMatKhau());
                setSelectedChucVu(nhanVien.getChucVu());
            }
        } else if (type == 1) { // Add
            layoutMaNhanVien.setVisibility(View.GONE);
        }
    }

    private void setSelectedChucVu(int chucVu) {
        for (int i = 0; i < chucVuList.size(); i++) {
            if (chucVuList.get(i).getChucVuCode() == chucVu) {
                spChucVu.setSelection(i);
                break;
            }
        }
    }

    private void luuNhanVien() {
        String tenNhanVien = edtTenNhanVien.getText().toString().trim();
        String diaChi = edtDiaChi.getText().toString().trim();
        String luongStr = edtLuong.getText().toString().replaceAll("[^\\d]", "").trim();
        String matKhau = edtMatKhau.getText().toString().trim();

        // 1. Kiểm tra trống dữ liệu
        if (tenNhanVien.isEmpty() || luongStr.isEmpty() || matKhau.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ Tên, Lương và Mật khẩu!", Toast.LENGTH_SHORT).show();
            return;
        }

        double luong = Double.parseDouble(luongStr);
        int chucVu = ((ChucVu) spChucVu.getSelectedItem()).getChucVuCode();
        String maNhanVien;
        boolean isOK;

        if (type == 0) { // Cập nhật
            maNhanVien = edtMaNhanVien.getText().toString().trim();
            NhanVien nv = new NhanVien(maNhanVien, tenNhanVien, diaChi, chucVu, luong, matKhau);
            isOK = db.suaNhanVien(nv);
        } else { // Thêm mới
            maNhanVien = db.taoMaNhanVienMoi(); // Tự động tạo mã NV
            NhanVien nv = new NhanVien(maNhanVien, tenNhanVien, diaChi, chucVu, luong, matKhau);
            isOK = db.themNhanVien(nv);
        }

        if (isOK) {
            String message = (type == 0 ? "Cập nhật" : "Thêm") + " nhân viên thành công!";
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Thao tác thất bại!", Toast.LENGTH_SHORT).show();
        }
    }
}
