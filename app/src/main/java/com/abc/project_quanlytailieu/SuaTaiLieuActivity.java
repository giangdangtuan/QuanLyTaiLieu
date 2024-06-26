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
//import com.google.android.material.textfield.TextInputEditText;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.database.annotations.Nullable;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class SuaTaiLieuActivity extends AppCompatActivity {
//
//    private TextInputEditText edtTenTl, edtChuyenNganhTl, edtAnhTl, edtLinkTl, edtMoTaTl;
//    private Button btnSuaTl, btnXoaTl;
//    private ProgressBar PBLoading;
//    private FirebaseDatabase firebaseDB;
//    private DatabaseReference DBReference;
//    private String idTl;
//    private TaiLieu taiLieu;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sua_tai_lieu);
//        edtTenTl = findViewById(R.id.edtTenTL);
//        edtChuyenNganhTl = findViewById(R.id.edtChuyenNganhTL);
//        edtAnhTl = findViewById(R.id.edtAnhTL);
//        edtLinkTl = findViewById(R.id.edtLinkTL);
//        edtMoTaTl = findViewById(R.id.edtMoTaTL);
//        btnSuaTl = findViewById(R.id.btnSuaTl);
//        btnXoaTl = findViewById(R.id.btnXoaTl);
//        PBLoading = findViewById(R.id.PBLoading);
//        firebaseDB = FirebaseDatabase.getInstance();
//        taiLieu = getIntent().getParcelableExtra("TaiLieu");
//        if (taiLieu != null) {
//            edtTenTl.setText(taiLieu.getTenTl());
//            edtChuyenNganhTl.setText(taiLieu.getChuyenNganhTl());
//            edtAnhTl.setText(taiLieu.getAnhTl());
//            edtLinkTl.setText(taiLieu.getLinkTl());
//            edtMoTaTl.setText(taiLieu.getMoTaTl());
//            idTl = taiLieu.getIdTl();
//        }
//
//        DBReference = firebaseDB.getReference("TaiLieus").child(idTl);
//        btnSuaTl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                PBLoading.setVisibility(View.VISIBLE);
//                String tenTl = edtTenTl.getText().toString();
//                String chuyenNganhTl = edtChuyenNganhTl.getText().toString();
//                String anhTl = edtAnhTl.getText().toString();
//                String linkTl = edtLinkTl.getText().toString();
//                String moTaTl = edtMoTaTl.getText().toString();
//
//                Map<String, Object> map = new HashMap<>();
//                map.put("tenTl", tenTl);
//                map.put("chuyenNganhTl", chuyenNganhTl);
//                map.put("anhTl", anhTl);
//                map.put("linkTl", linkTl);
//                map.put("moTaTl", moTaTl);
//                map.put("idTl", idTl);
//
//                DBReference.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        PBLoading.setVisibility(View.GONE);
//                        DBReference.updateChildren(map);
//                        Toast.makeText(SuaTaiLieuActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(SuaTaiLieuActivity.this, MainActivity.class));
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        Toast.makeText(SuaTaiLieuActivity.this, "Sửa thất bại ", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });
//
//        btnXoaTl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                xoaTaiLieu();
//            }
//        });
//    }
//
//    private void xoaTaiLieu() {
//        firebaseDB = FirebaseDatabase.getInstance();
//        DBReference = firebaseDB.getReference("TaiLieus");
//        DBReference.removeValue();
//        Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
//        startActivity(new Intent(SuaTaiLieuActivity.this, MainActivity.class));
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class SuaTaiLieuActivity extends AppCompatActivity {

    private TextInputEditText edtTenTl, edtChuyenNganhTl, edtAnhTl, edtLinkTl, edtMoTaTl;
    private Button btnSuaTl, btnXoaTl;
    private ProgressBar PBLoading;
    private FirebaseDatabase firebaseDB;
    private DatabaseReference DBReference;
    private String idTl;
    private TaiLieu taiLieu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_tai_lieu);

        edtTenTl = findViewById(R.id.edtTenTL);
        edtChuyenNganhTl = findViewById(R.id.edtChuyenNganhTL);
        edtAnhTl = findViewById(R.id.edtAnhTL);
        edtLinkTl = findViewById(R.id.edtLinkTL);
        edtMoTaTl = findViewById(R.id.edtMoTaTL);
        btnSuaTl = findViewById(R.id.btnSuaTl);
        btnXoaTl = findViewById(R.id.btnXoaTl);
        PBLoading = findViewById(R.id.PBLoading);

        firebaseDB = FirebaseDatabase.getInstance();
        taiLieu = getIntent().getParcelableExtra("TaiLieu");

        if (taiLieu != null) {
            edtTenTl.setText(taiLieu.getTenTl());
            edtChuyenNganhTl.setText(taiLieu.getChuyenNganhTl());
            edtAnhTl.setText(taiLieu.getAnhTl());
            edtLinkTl.setText(taiLieu.getLinkTl());
            edtMoTaTl.setText(taiLieu.getMoTaTl());
            idTl = taiLieu.getIdTl();
        }

        DBReference = firebaseDB.getReference("TaiLieus").child(idTl);

        btnSuaTl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PBLoading.setVisibility(View.VISIBLE);
                String tenTl = edtTenTl.getText().toString();
                String chuyenNganhTl = edtChuyenNganhTl.getText().toString();
                String anhTl = edtAnhTl.getText().toString();
                String linkTl = edtLinkTl.getText().toString();
                String moTaTl = edtMoTaTl.getText().toString();

                Map<String, Object> map = new HashMap<>();
                map.put("idTl", idTl);
                map.put("tenTl", tenTl);
                map.put("chuyenNganhTl", chuyenNganhTl);
                map.put("anhTl", anhTl);
                map.put("linkTl", linkTl);
                map.put("moTaTl", moTaTl);


                DBReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        PBLoading.setVisibility(View.GONE);
                        DBReference.updateChildren(map, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                if (error == null) {
                                    Toast.makeText(SuaTaiLieuActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(SuaTaiLieuActivity.this, MainActivity.class));
                                } else {
                                    Toast.makeText(SuaTaiLieuActivity.this, "Lỗi: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        PBLoading.setVisibility(View.GONE);
                        Toast.makeText(SuaTaiLieuActivity.this, "Lỗi: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btnXoaTl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xoaTaiLieu();
            }
        });
    }

    private void xoaTaiLieu() {
        DBReference = firebaseDB.getReference("TaiLieus").child(idTl);
        DBReference.removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if (error == null) {
                    Toast.makeText(SuaTaiLieuActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SuaTaiLieuActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(SuaTaiLieuActivity.this, "Lỗi: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}