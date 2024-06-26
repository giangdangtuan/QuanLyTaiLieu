//package com.abc.project_quanlytailieu;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ProgressBar;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//
//import com.google.android.material.textfield.TextInputEditText;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//public class ThemTaiLieuActivity extends AppCompatActivity {
//
//    private TextInputEditText edtTenTl, edtChuyenNganhTl, edtAnhTl, edtLinkTl, edtMoTaTl;
//    private Button btnThemTl;
//    private ProgressBar PBLoading;
//    private FirebaseDatabase firebaseDB;
//    private DatabaseReference DBReference;
//    private String idTl;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_them_tai_lieu);
//
//        edtTenTl = findViewById(R.id.edtTenTL);
//        edtChuyenNganhTl = findViewById(R.id.edtChuyenNganhTL);
//        edtAnhTl = findViewById(R.id.edtAnhTL);
//        edtLinkTl = findViewById(R.id.edtLinkTL);
//        edtMoTaTl = findViewById(R.id.edtMoTaTL);
//        btnThemTl = findViewById(R.id.btnThemTl);
//        PBLoading = findViewById(R.id.PBLoading);
//        firebaseDB = FirebaseDatabase.getInstance();
//        DBReference = firebaseDB.getReference("TaiLieus");
//
//
//        btnThemTl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                PBLoading.setVisibility(View.VISIBLE);
//                String tenTl = edtTenTl.getText().toString();
//                String chuyenNganhTl = edtChuyenNganhTl.getText().toString();
//                String anhTl = edtAnhTl.getText().toString();
//                String linkTl = edtLinkTl.getText().toString();
//                String moTaTl = edtMoTaTl.getText().toString();
//                idTl = tenTl;
//
//                TaiLieu tl = new TaiLieu(idTl,tenTl,chuyenNganhTl,anhTl,linkTl,moTaTl);
//
//                DBReference.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        PBLoading.setVisibility(View.GONE);
//                        DBReference.child(idTl).setValue(tl);
//                        Toast.makeText(ThemTaiLieuActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(ThemTaiLieuActivity.this, MainActivity.class));
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        PBLoading.setVisibility(View.GONE);
//                        Toast.makeText(ThemTaiLieuActivity.this, "Lỗi: " + error.toString(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });

//    }
//}

package com.abc.project_quanlytailieu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ThemTaiLieuActivity extends AppCompatActivity {

    private TextInputEditText edtTenTl, edtChuyenNganhTl, edtAnhTl, edtLinkTl, edtMoTaTl;
    private Button btnThemTl;
    private ProgressBar PBLoading;
    private DatabaseReference DBReference;
    private String idTl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_tai_lieu);

        edtTenTl = findViewById(R.id.edtTenTL);
        edtChuyenNganhTl = findViewById(R.id.edtChuyenNganhTL);
        edtAnhTl = findViewById(R.id.edtAnhTL);
        edtLinkTl = findViewById(R.id.edtLinkTL);
        edtMoTaTl = findViewById(R.id.edtMoTaTL);
        btnThemTl = findViewById(R.id.btnThemTl);
        PBLoading = findViewById(R.id.PBLoading);
        FirebaseDatabase firebaseDB = FirebaseDatabase.getInstance();
        DBReference = firebaseDB.getReference("TaiLieus");

        btnThemTl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PBLoading.setVisibility(View.VISIBLE);
                String tenTl = edtTenTl.getText().toString();
                String chuyenNganhTl = edtChuyenNganhTl.getText().toString();
                String anhTl = edtAnhTl.getText().toString();
                String linkTl = edtLinkTl.getText().toString();
                String moTaTl = edtMoTaTl.getText().toString();
                idTl = DBReference.push().getKey();

                TaiLieu tl = new TaiLieu(idTl, tenTl, chuyenNganhTl, anhTl, linkTl, moTaTl);

                DBReference.child(idTl).setValue(tl, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@NonNull DatabaseError error, @NonNull DatabaseReference ref) {
                        PBLoading.setVisibility(View.GONE);
                        if (error == null) {
                            Toast.makeText(ThemTaiLieuActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ThemTaiLieuActivity.this, MainActivity.class));
                        } else {
                            Toast.makeText(ThemTaiLieuActivity.this, "Lỗi: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}