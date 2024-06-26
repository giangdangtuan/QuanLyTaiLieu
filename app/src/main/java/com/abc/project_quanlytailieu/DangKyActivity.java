package com.abc.project_quanlytailieu;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DangKyActivity extends AppCompatActivity {

    private TextInputEditText edtTen, edtPassword, edtPassword2;
    private Button btnDangKy;
    private ProgressBar PBloading;
    private TextView txtDangNhap;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        edtTen = findViewById(R.id.EdtTen);
        edtPassword = findViewById(R.id.EdtPassword);
        edtPassword2 = findViewById(R.id.EdtPassword2);
        btnDangKy = findViewById(R.id.btnDangKy);
        PBloading = findViewById(R.id.PBLoading);
        txtDangNhap = findViewById(R.id.txtDangNhap);
        mAuth = FirebaseAuth.getInstance();
        txtDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DangKyActivity.this, DangNhapActivity.class);
                startActivity(i);
            }
        });

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PBloading.setVisibility(View.VISIBLE);
                String ten = edtTen.getText().toString();
                String password = edtPassword.getText().toString();
                String password2 = edtPassword2.getText().toString();
                if(!password.equals(password2)){
                    Toast.makeText(DangKyActivity.this,"Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(ten) && TextUtils.isEmpty(password) && TextUtils.isEmpty(password2)) {
                    Toast.makeText(DangKyActivity.this,"Chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    mAuth.createUserWithEmailAndPassword(ten,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                PBloading.setVisibility(View.GONE);
                                Toast.makeText(DangKyActivity.this,"Đăng ký thành công !", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(DangKyActivity.this, DangNhapActivity.class);
                                startActivity(i);
                                finish();
                            }else {
                                PBloading.setVisibility(View.GONE);
                                Toast.makeText(DangKyActivity.this,"Đăng ký thất bại !", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}