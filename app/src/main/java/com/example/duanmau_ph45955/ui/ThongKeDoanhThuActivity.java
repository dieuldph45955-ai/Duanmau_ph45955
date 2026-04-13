package com.example.duanmau_ph45955.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.duanmau_ph45955.R;
import com.example.duanmau_ph45955.database.DatabaseHelper;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;

public class ThongKeDoanhThuActivity extends AppCompatActivity {
    private EditText edtNgayBatDau, edtNgayKetThuc;
    private TextView tvDoanhThu;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke_doanh_thu);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Thống kê doanh thu");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        databaseHelper = new DatabaseHelper(this);

        edtNgayBatDau = findViewById(R.id.edtNgayBatDau);
        edtNgayKetThuc = findViewById(R.id.edtNgayKetThuc);
        tvDoanhThu = findViewById(R.id.tvDoanhThu);

        edtNgayBatDau.setOnClickListener(v -> showDatePickerDialog(edtNgayBatDau));
        edtNgayKetThuc.setOnClickListener(v -> showDatePickerDialog(edtNgayKetThuc));

        findViewById(R.id.btnThongKeDoanhThu).setOnClickListener(v -> {
            String ngayBatDau = edtNgayBatDau.getText().toString().trim();
            String ngayKetThuc = edtNgayKetThuc.getText().toString().trim();

            if (ngayBatDau.isEmpty() || ngayKetThuc.isEmpty()) {
                Toast.makeText(this, "Vui lòng chọn đầy đủ ngày!", Toast.LENGTH_SHORT).show();
                return;
            }

            int doanhThu = databaseHelper.layDoanhThu(ngayBatDau, ngayKetThuc);
            
            // Định dạng tiền tệ cho đẹp (Ví dụ: 1.000.000 VND)
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
            tvDoanhThu.setText("Tổng doanh thu: " + currencyFormat.format(doanhThu));
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void showDatePickerDialog(EditText editText) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    // SỬA TẠI ĐÂY: Chuyển về định dạng YYYY-MM-DD để khớp với Database
                    String selectedDate = String.format("%04d-%02d-%02d", selectedYear, (selectedMonth + 1), selectedDay);
                    editText.setText(selectedDate);
                },
                year, month, day
        );
        datePickerDialog.show();
    }
}
