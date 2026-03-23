package com.example.duanmau_ph45955.ui;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.duanmau_ph45955.R;

public class ThongKeDoanhThuActivity extends AppCompatActivity {
    private EditText edtNgayBatDau, edtNgayKetThuc;
    private TextView tvDoanhThu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_thong_ke_doanh_thu);
        edtNgayBatDau = findViewById(R.id.edtNgayBatDau);
        edtNgayKetThuc = findViewById(R.id.edtNgayKetThuc);
        tvDoanhThu = findViewById(R.id.tvDoanhThu);

    }
}