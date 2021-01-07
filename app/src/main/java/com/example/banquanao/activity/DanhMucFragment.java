package com.example.banquanao.activity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.ListFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.banquanao.R;
import com.example.banquanao.adapter.LoaiSanPhamAdapter;
import com.example.banquanao.model.LoaiSanPham;
import com.example.banquanao.ultil.StringURL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class DanhMucFragment extends ListFragment {
    ArrayList<LoaiSanPham> mangLoaiSanPham;
    LoaiSanPhamAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_danh_muc, container, false);
        mangLoaiSanPham=new ArrayList<>();
        adapter=new LoaiSanPhamAdapter(mangLoaiSanPham,getActivity());
        setListAdapter(adapter);
        getData();
        return view;
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        LoaiSanPham loaiSanPham=mangLoaiSanPham.get(position);
        switch (loaiSanPham.getID()){
            case 1:
                Intent intent=new Intent(getContext(),Quan_JeansActivity.class);
                startActivity(intent);
                break;
            case 2:
                Intent intent1=new Intent(getContext(),QuanShortActivity.class);
                startActivity(intent1);
                break;
            case 3:
                Intent intent2=new Intent(getContext(),QuanJoggerActivity.class);
                startActivity(intent2);
                break;
            case 4:
                Intent intent3=new Intent(getContext(),AoSoMiActivity.class);
                startActivity(intent3);
                break;
            case 5:
                Intent intent4=new Intent(getContext(),AoThunActivity.class);
                startActivity(intent4);
                break;
        }
    }

    private void getData() {
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, StringURL.URL_LOAI_SP, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject object=response.getJSONObject(i);
                                mangLoaiSanPham.add(new LoaiSanPham(object.getInt("id"),object.getString("tenloaisanpham"),object.getString("hinhanhsanpham")));
                                Log.d("AAA",object.getString("tenloaisanpham"));
                                adapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(jsonArrayRequest);
    }
}