package com.example.banquanao.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.banquanao.R;
import com.example.banquanao.model.GioHang;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbarmanhinhchinh;
    DrawerLayout drawerlayout;
    BottomNavigationView bottomnavigationview;
    public static ArrayList<GioHang> mangGioHang;
    static Boolean kt=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        bottomnavigationview=findViewById(R.id.bottomnavigationview);
        getSupportFragmentManager().beginTransaction().add(R.id.fragmet,new HomeFragment()).commit();
        EventClickBottomNavigationView();
    }

    private void EventClickBottomNavigationView() {
        bottomnavigationview.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment=null;
                switch (item.getItemId()){
                    case R.id.menu_bottom_home:
                        fragment=new HomeFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmet,fragment).commit();
                        break;
                    case R.id.nmenu_bottom_danhmuc:
                        fragment=new DanhMucFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmet,fragment).commit();
                        break;
                    case R.id.nmenu_bottom_giohang:
                        fragment=new GioHangFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmet,fragment).commit();
                        break;
                    case R.id.menu_bottom_user:
                        fragment=new CaNhanFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmet,fragment).commit();
                        break;
                }
                return true;
            }
        });
    }

    public void changeToCart(){
        bottomnavigationview.setSelectedItemId(R.id.nmenu_bottom_giohang);
    }

    private void mapping() {
        toolbarmanhinhchinh=findViewById(R.id.toolbarmanhinhchinh);
        if (mangGioHang!=null){

        }else {
            mangGioHang=new ArrayList<>();
        }
    }
}