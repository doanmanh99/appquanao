package com.example.banquanao.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.banquanao.R;
import com.example.banquanao.ultil.StringURL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.banquanao.activity.MainActivity.kt;


public class CaNhanFragment extends Fragment {
    Toolbar toolbar;
    TextView txtvDangNhap,txtvLichSu,txtvGioHang,txtvDangXuat,txtvCapNhat;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_ca_nhan, container, false);
        toolbar=view.findViewById(R.id.toolbar);
        txtvDangNhap=view.findViewById(R.id.txtvDangNhap);
        txtvLichSu=view.findViewById(R.id.txtvLichSu);
        txtvGioHang=view.findViewById(R.id.txtvGioHang);
        txtvDangXuat=view.findViewById(R.id.txtvDangXuat);
        txtvCapNhat=view.findViewById(R.id.txtvCapNhat);
        // Inflate the layout for this fragment
        Actionbar();
        EventClick();
        return view;
    }
    private void Actionbar() {
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("CÁ NHÂN");
        toolbar.setNavigationIcon(null);
    }
    private void EventClick() {
        txtvCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kt==false) {
                    Toast.makeText(getContext(),"Bạn chưa đăng nhâp",Toast.LENGTH_LONG).show();
                    LoginDialog loginDialog = new LoginDialog();
                    loginDialog.show(getParentFragmentManager(), loginDialog.getTag());
                }else {
                    Intent intent=new Intent(getContext(),CapNhatActivity.class);
                    startActivity(intent);
                }
            }
        });
        txtvDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kt==false) {
                    Toast.makeText(getContext(),"Bạn chưa đăng nhâp",Toast.LENGTH_LONG).show();
                    LoginDialog loginDialog = new LoginDialog();
                    loginDialog.show(getParentFragmentManager(), loginDialog.getTag());
                }else {
                    kt=false;
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmet,new CaNhanFragment()).commit();
                    Toast.makeText(getContext(),"Bạn đã đăng xuất thành công",Toast.LENGTH_LONG).show();
                }
            }
        });
            txtvDangNhap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (kt==false) {
                        LoginDialog loginDialog = new LoginDialog();
                        loginDialog.show(getParentFragmentManager(), loginDialog.getTag());
                    }
                }
            });
        txtvGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.changeToCart();
            }
        });
        txtvLichSu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),LichSuMuaHangActivity.class);
                startActivity(intent);
            }
        });
    }
}