package com.example.quanlynhanvien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    Button btnDN;
    EditText taiKhoan, matKhau;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        Login();

    }

    private void Login() {
        btnDN = (Button) findViewById(R.id.btnDangNhap);
        taiKhoan = (EditText) findViewById(R.id.editTaiKhoan);
        matKhau = (EditText) findViewById(R.id.editMatKhau);
        btnDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (taiKhoan.getText().length() != 0 && matKhau.getText().length() != 0) {
                    if (taiKhoan.getText().toString().equals("admin") && matKhau.getText().toString().equals("admin")) {
                        Toast.makeText(Login.this, "Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Login.this, "Đăng Nhập Thất Bại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Login.this, "Vui Lòng Nhập Đầy Đủ", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }
}