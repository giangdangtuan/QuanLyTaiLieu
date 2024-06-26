package com.abc.project_quanlytailieu;

import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TaiLieuRVAdapter.TaiLieuClickInterface {

    private RecyclerView taiLieuRV;
    private ProgressBar PBLoading;
    private FloatingActionButton themFAB;
    private FirebaseDatabase firebaseDB;
    private DatabaseReference DBReference;
    private ArrayList<TaiLieu> listTl;
    private RelativeLayout RLDialog;
    private TaiLieuRVAdapter adapterTl;
    private FirebaseAuth mAuth;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        taiLieuRV = findViewById(R.id.RVTailieu);
        PBLoading = findViewById(R.id.PBLoading);
        themFAB = findViewById(R.id.themFAB);
        RLDialog = findViewById(R.id.RLDialog);
        firebaseDB = FirebaseDatabase.getInstance();
        DBReference = firebaseDB.getReference("TaiLieus");
        listTl = new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();
        adapterTl = new TaiLieuRVAdapter(listTl, this, this);
        taiLieuRV.setLayoutManager(new LinearLayoutManager(this));
        taiLieuRV.setAdapter(adapterTl);
        themFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ThemTaiLieuActivity.class));
            }
        });

        getAllTaiLieu();

    }

    private void getAllTaiLieu(){
        listTl.clear();
        DBReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                PBLoading.setVisibility(View.GONE);
                listTl.add(snapshot.getValue(TaiLieu.class));
                adapterTl.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                PBLoading.setVisibility(View.GONE);
                adapterTl.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                PBLoading.setVisibility(View.GONE);
                adapterTl.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                PBLoading.setVisibility(View.GONE);
                adapterTl.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onTaiLieuClick(int position) {
        DisplayDialog(listTl.get(position));
    }

    private void DisplayDialog(TaiLieu taiLieu){
        final Dialog dialog = new Dialog(this);
        View layout = LayoutInflater.from(this).inflate(R.layout.dialog,RLDialog);
        dialog.setContentView(layout);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();

        TextView tenTl = layout.findViewById(R.id.txtTenTl);
        TextView chuyenNganh = layout.findViewById(R.id.txtChuyenNganhTl);
        ImageView anhTl = layout.findViewById(R.id.imgTl);
        TextView moTa = layout.findViewById(R.id.txtMoTa);
        Button btnSua = layout.findViewById(R.id.btnSua);
        Button btnChiTiet = layout.findViewById(R.id.btnChiTiet);

        tenTl.setText(taiLieu.getTenTl());
        chuyenNganh.setText(taiLieu.getChuyenNganhTl());
        Picasso.get().load(taiLieu.getAnhTl()).into(anhTl);
        moTa.setText(taiLieu.getMoTaTl());

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SuaTaiLieuActivity.class);
                i.putExtra("TaiLieu", taiLieu);
                startActivity(i);
            }
        });

        btnChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(taiLieu.getLinkTl()));
                startActivity(i);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.iconTimKiem).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapterTl.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterTl.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.dangXuat) {
            Toast.makeText(this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
            mAuth.signOut();
            Intent i = new Intent(MainActivity.this, DangNhapActivity.class);
            startActivity(i);
            this.finish();
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
    }
}

