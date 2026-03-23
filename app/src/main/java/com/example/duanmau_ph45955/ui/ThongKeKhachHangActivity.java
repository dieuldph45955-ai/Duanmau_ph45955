package com.example.duanmau_ph45955.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.duanmau_ph45955.R;

public class ThongKeKhachHangActivity extends AppCompatActivity {
    private EditText edtNgayBatDau, edtNgayKetThuc, edtSoLuong;
    private Button btnThongKeTopKhachHang;
    private TextView tvTopKhachHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_thong_ke_khach_hang);

        edtNgayBatDau = findViewById(R.id.edtNgayBatDau);
        edtNgayKetThuc = findViewById(R.id.edtNgayKetThuc);
        edtSoLuong = findViewById(R.id.edtSoLuong);
        btnThongKeTopKhachHang = findViewById(R.id.btnTopKhachHang);
        tvTopKhachHang = findViewById(R.id.tvTopKhachHang);

    }
}