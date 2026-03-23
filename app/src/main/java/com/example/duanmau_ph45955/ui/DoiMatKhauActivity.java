package com.example.duanmau_ph45955.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.duanmau_ph45955.R;

public class DoiMatKhauActivity extends AppCompatActivity {
    private EditText edtMatKhauCu, edtMatKhauMoi, edtNhapLaiMatKhauMoi;
    private Button btnLuu, btnHuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_doi_mat_khau);
        edtMatKhauCu = findViewById(R.id.edtOldPassword);
        edtMatKhauMoi = findViewById(R.id.edtNewPassword);
        edtNhapLaiMatKhauMoi = findViewById(R.id.edtConfirmPassword);
        btnLuu = findViewById(R.id.btnLuu);
        btnHuy = findViewById(R.id.btnHuy);


    }
}