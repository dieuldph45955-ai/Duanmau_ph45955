package com.example.duanmau_ph45955.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.duanmau_ph45955.R;
import com.example.duanmau_ph45955.adapter.NhanVienAdapter;
import com.example.duanmau_ph45955.database.DatabaseHelper;
import com.example.duanmau_ph45955.model.NhanVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Objects;

public class QuanLyNhanVienActivity extends AppCompatActivity implements NhanVienAdapter.OnNhanVienClickListener {
    public static final String NHAN_VIEN = "NHAN_VIEN";
    private NhanVienAdapter nhanVienAdapter;
    private List<NhanVien> danhSachNhanVien;
    private DatabaseHelper db;
    private EditText etTimKiemNhanVien;

    //quay về màn hình trước
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_nhan_vien);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ListView lvNhanVien = findViewById(R.id.lvNhanVien);
        etTimKiemNhanVien = findViewById(R.id.etTimKiemNhanVien); // Ánh xạ EditText tìm kiếm

        Objects.requireNonNull(getSupportActionBar()).setTitle("Quản lý nhân viên");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new DatabaseHelper(this);
        FloatingActionButton fabThemNhanVien = findViewById(R.id.fabThemNhanVien);

        // Đổ dữ liệu ban đầu
        danhSachNhanVien = db.layTatCaNhanVien();
        nhanVienAdapter = new NhanVienAdapter(this, danhSachNhanVien);
        nhanVienAdapter.setOnNhanVienClickListener(this);
        lvNhanVien.setAdapter(nhanVienAdapter);

        // XỬ LÝ TÌM KIẾM KHI NHẬP CHỮ
        etTimKiemNhanVien.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Khi người dùng gõ chữ, gọi hàm tìm kiếm từ DatabaseHelper
                String query = s.toString();
                List<NhanVien> ketQuaMoi = db.timKiemNhanVien(query);

                // Cập nhật lại danh sách hiển thị
                danhSachNhanVien.clear();
                danhSachNhanVien.addAll(ketQuaMoi);
                nhanVienAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        fabThemNhanVien.setOnClickListener(view -> {
            Intent intent = new Intent(this, EditNhanVienActivity.class);
            intent.putExtra("Type", 1);
            this.startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        danhSachNhanVien.clear();
        danhSachNhanVien.addAll(db.layTatCaNhanVien());
        nhanVienAdapter.notifyDataSetChanged();
    }

    @Override
    public void onEditNhanVien(NhanVien nhanVien) {
        Intent intent = new Intent(this, EditNhanVienActivity.class);
        intent.putExtra("Type", 0);
        intent.putExtra(NHAN_VIEN, nhanVien);

        startActivity(intent);
    }

    @Override
    public void onDeleteNhanVien(NhanVien nhanVien) {
        DatabaseHelper db = new DatabaseHelper(this);
        boolean isDeleted = db.xoaNhanVien(nhanVien.getMaNhanVien());
        if (isDeleted) {
            danhSachNhanVien.remove(nhanVien);
            nhanVienAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Xoá nhân viên thành công", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Xoá nhân viên thất bại", Toast.LENGTH_SHORT).show();
        }
    }
}
