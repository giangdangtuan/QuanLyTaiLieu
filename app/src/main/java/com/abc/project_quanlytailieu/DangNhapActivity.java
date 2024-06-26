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


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DangNhapActivity extends AppCompatActivity {

    private TextInputEditText edtTen, edtPassword;
    private Button btnDangNhap;
    private ProgressBar PBLoading;
    private TextView txtDangKy;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        edtTen = findViewById(R.id.EdtTen);
        edtPassword = findViewById(R.id.EdtPassword);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        PBLoading = findViewById(R.id.PBLoading);
        txtDangKy = findViewById(R.id.txtDangKy);
        mAuth = FirebaseAuth.getInstance();

        txtDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DangNhapActivity.this, DangKyActivity.class);
                startActivity(i);
            }
        });

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PBLoading.setVisibility(View.VISIBLE);
                String ten = edtTen.getText().toString();
                String password = edtPassword.getText().toString();
                if (TextUtils.isEmpty(ten) && TextUtils.isEmpty(password)) {
                    Toast.makeText(DangNhapActivity.this, "Chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    mAuth.signInWithEmailAndPassword(ten, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                PBLoading.setVisibility(View.GONE);
                                Toast.makeText(DangNhapActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(DangNhapActivity.this, MainActivity.class);
                                startActivity(i);
                                finish();

                            } else {
                                PBLoading.setVisibility(View.GONE);
                                Toast.makeText(DangNhapActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        FirebaseUser user = mAuth.getCurrentUser();
//        if(user != null){
//            Intent i = new Intent(DangNhapActivity.this, MainActivity.class);
//            startActivity(i);
//            this.finish();
//        }
//    }
}