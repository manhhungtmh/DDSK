package com.example.ddsk;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class HomeAdmin extends AppCompatActivity {

    CardView cardViewDSSK;
    CardView cardViewSK;
    CardView cardViewInfo;
    CardView cardViewMC;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        //Ánh xạ các phần tử
        Init();
        SetClickItem();
        checkPermission();
    }

    private void Init() {
        cardViewDSSK = findViewById(R.id.danhsachsukien);
        cardViewSK = findViewById(R.id.sukiendathamgia);
        cardViewInfo = findViewById(R.id.info_user);
        cardViewMC = findViewById(R.id.minhchung);
    }

    private void SetClickItem() {
        cardViewDSSK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeAdmin.this, ManageEventActivity.class));
            }
        });
        cardViewSK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeAdmin.this, "an danh su kien tham gia", Toast.LENGTH_SHORT).show();
            }
        });
        cardViewInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeAdmin.this, "an thong tin ca nhan", Toast.LENGTH_SHORT).show();
            }
        });
        cardViewMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeAdmin.this, "an minh chung", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkPermission() {
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }

        if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            checkPermission();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.logout: {
                //goi ham dang xuat
                FirebaseAuth.getInstance().signOut();
                Intent goToLogin = new Intent(HomeAdmin.this,LoginActivity.class);
                startActivity(goToLogin);
                Toast.makeText(this, "an dang xuat", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.tb: {
                Toast.makeText(this, "thong bao a", Toast.LENGTH_SHORT).show();
            }
        }

        return super.onOptionsItemSelected(item);
    }
}