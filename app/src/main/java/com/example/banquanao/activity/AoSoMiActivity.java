package com.example.banquanao.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.banquanao.R;
import com.example.banquanao.adapter.SanPhamAdapter;
import com.example.banquanao.model.SanPham;
import com.example.banquanao.ultil.StringURL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AoSoMiActivity extends AppCompatActivity{
    Toolbar toolbar;
    RecyclerView recyclerview;
    ArrayList<SanPham> mangsanpham;
    SanPhamAdapter sanPhamAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ao_so_mi);
        mapping();
        Actionbar();
        getData();
    }
    private void Actionbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void getData() {
        int ID=4;
        mangsanpham=new ArrayList<>();
        sanPhamAdapter=new SanPhamAdapter(mangsanpham,getApplicationContext());
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerview.setAdapter(sanPhamAdapter);
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest =new StringRequest(Request.Method.POST, StringURL.URL_SAN_PHAM_THEO_LOAI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object=jsonArray.getJSONObject(i);
                        mangsanpham.add(new SanPham(object.getInt("id"),object.getString("tensanpham"),object.getInt("giasanpham"),
                                object.getString("hinhanhsanpham"),object.getString("motasanpham"),object.getInt("idloaisanpham")));
                        sanPhamAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map=new HashMap<String,String>();
                map.put("idloaisanpham",String.valueOf(ID));
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
    private void mapping() {
        recyclerview=findViewById(R.id.recyclerview);
        toolbar=findViewById(R.id.toolbar);
        mangsanpham=new ArrayList<>();
        sanPhamAdapter=new SanPhamAdapter(mangsanpham,getApplicationContext());
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerview.setAdapter(sanPhamAdapter);
    }
}