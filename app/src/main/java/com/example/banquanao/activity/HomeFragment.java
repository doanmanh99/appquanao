package com.example.banquanao.activity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.banquanao.activity.MainActivity.kt;

public class HomeFragment extends Fragment {
    Toolbar toolbarmanhinhchinh;
    ViewFlipper viewflipper;
    RecyclerView recyclerviewQuanJeans,recyclerviewQuanShort,recyclerviewJoggerShort,recyclerviewSoMi,recyclerviewThun;
    NavigationView navigationview;
    ArrayList<SanPham> mangquanjeans,mangquanshort,mangquanjogger,mangaosomi,mangaothun;
    SanPhamAdapter quanjeansAdapter,quanshortAdapter,quanjoggerAdapter,aosomiAdapter,aothunAdapter;
    TextView txtvAllQuanJeans,txtvAllQuanShort,txtvAllQuanJogger,txtvAllAoSoMi,txtvAllAoThun;
    DrawerLayout drawerlayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        viewflipper=view.findViewById(R.id.viewflipper);
        recyclerviewQuanJeans=view.findViewById(R.id.recyclerviewQuanJeans);
        navigationview=view.findViewById(R.id.navigationview);
        recyclerviewQuanShort=view.findViewById(R.id.recyclerviewQuanShort);
        recyclerviewJoggerShort=view.findViewById(R.id.recyclerviewJoggerShort);
        recyclerviewSoMi=view.findViewById(R.id.recyclerviewSoMi);
        recyclerviewThun=view.findViewById(R.id.recyclerviewThun);
        txtvAllQuanJeans=view.findViewById(R.id.txtvAllQuanJeans);
        txtvAllQuanShort=view.findViewById(R.id.txtvAllQuanShort);
        txtvAllQuanJogger=view.findViewById(R.id.txtvAllQuanJogger);
        txtvAllAoSoMi=view.findViewById(R.id.txtvAllAoSoMi);
        txtvAllAoThun=view.findViewById(R.id.txtvAllAoThun);
        toolbarmanhinhchinh=view.findViewById(R.id.toolbarmanhinhchinh);
        drawerlayout=view.findViewById(R.id.drawerlayout);
        if(kt==true){

        }
        Actionbar();
        ActionViewFlipper();
        loadDataQuanJean();
        loadDataQuanShort();
        loadDataQuanJogger();
        loadDataAoSoMi();
        loadDataAoThun();
        txtvAllQuanJeans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),Quan_JeansActivity.class);
                startActivity(intent);
            }
        });
        txtvAllQuanShort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),QuanShortActivity.class);
                startActivity(intent);
            }
        });
        txtvAllQuanJogger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),QuanJoggerActivity.class);
                startActivity(intent);
            }
        });
        txtvAllAoSoMi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),AoSoMiActivity.class);
                startActivity(intent);
            }
        });
        txtvAllAoThun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),AoThunActivity.class);
                startActivity(intent);
            }
        });
        EventClickNavigationView();
        return view;
    }
    private void Actionbar() {
        ((MainActivity) getActivity()).setSupportActionBar(toolbarmanhinhchinh);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("");
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarmanhinhchinh.setNavigationIcon(R.drawable.ic_menu);
        toolbarmanhinhchinh.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerlayout.openDrawer(GravityCompat.START);
            }
        });
    }
    private void EventClickNavigationView() {
        navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menuQuanJeans:
                        Intent intent=new Intent(getContext(),Quan_JeansActivity.class);
                        startActivity(intent);
                        return true;
                    case  R.id.menuQuanShort:
                        Intent intent1=new Intent(getContext(),QuanShortActivity.class);
                        startActivity(intent1);
                        return true;
                    case  R.id.menuQuanJogger:
                        Intent intent2=new Intent(getContext(),QuanJoggerActivity.class);
                        startActivity(intent2);
                        return true;
                    case  R.id.menuAoSoMi:
                        Intent intent3=new Intent(getContext(),AoSoMiActivity.class);
                        startActivity(intent3);
                        return true;
                    case  R.id.menuAoThun:
                        Intent intent4=new Intent(getContext(),AoThunActivity.class);
                        startActivity(intent4);
                        return true;
                    case R.id.menuDangNhap:
                        if (MainActivity.kt==true){
                            Toast.makeText(getContext(),"Bạn đã đăng nhập rồi",Toast.LENGTH_LONG).show();
                            break;
                        }else {
                            LoginDialog loginDialog = new LoginDialog();
                            loginDialog.show(getParentFragmentManager(), loginDialog.getTag());
                            MainActivity.kt = true;
                            break;
                        }
                    case R.id.menuDangXuat:
                        if (MainActivity.kt==false){
                            Toast.makeText(getContext(),"Bạn chưa đăng nhập mời bạn đăng nhập",Toast.LENGTH_LONG).show();
                            LoginDialog loginDialog=new LoginDialog();
                            loginDialog.show(getParentFragmentManager(),loginDialog.getTag());
                            break;
                        }
                        MainActivity.kt=false;
                        return true;
                    case R.id.menuGioHang:
                        MainActivity mainActivity=(MainActivity) getActivity();
                        mainActivity.changeToCart();
                        return true;
                }
                return true;
            }
        });
    }
    private void loadDataAoThun() {
        int IDAOTHUN=5;
        mangaothun=new ArrayList<>();
        aothunAdapter=new SanPhamAdapter(mangaothun,getContext());
        recyclerviewThun.setHasFixedSize(true);
        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerviewThun.setLayoutManager(horizontalLayoutManagaer);
        recyclerviewThun.setAdapter(aothunAdapter);
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        StringRequest stringRequest =new StringRequest(Request.Method.POST, StringURL.URL_SAN_PHAM_THEO_LOAI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object=jsonArray.getJSONObject(i);
                        mangaothun.add(new SanPham(object.getInt("id"),object.getString("tensanpham"),object.getInt("giasanpham"),
                                object.getString("hinhanhsanpham"),object.getString("motasanpham"),object.getInt("idloaisanpham")));
                        aothunAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("AAA",error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map=new HashMap<String,String>();
                map.put("idloaisanpham",String.valueOf(IDAOTHUN));
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void loadDataAoSoMi() {
        int IDAOSOMI=4;
        mangaosomi=new ArrayList<>();
        aosomiAdapter=new SanPhamAdapter(mangaosomi,getContext());
        recyclerviewSoMi.setHasFixedSize(true);
        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerviewSoMi.setLayoutManager(horizontalLayoutManagaer);
        recyclerviewSoMi.setAdapter(aosomiAdapter);
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        StringRequest stringRequest =new StringRequest(Request.Method.POST, StringURL.URL_SAN_PHAM_THEO_LOAI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object=jsonArray.getJSONObject(i);
                        mangaosomi.add(new SanPham(object.getInt("id"),object.getString("tensanpham"),object.getInt("giasanpham"),
                                object.getString("hinhanhsanpham"),object.getString("motasanpham"),object.getInt("idloaisanpham")));
                        aosomiAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("AAA",error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map=new HashMap<String,String>();
                map.put("idloaisanpham",String.valueOf(IDAOSOMI));
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void loadDataQuanJogger() {
        int IDQUANSHORT=3;
        mangquanjogger=new ArrayList<>();
        quanjoggerAdapter=new SanPhamAdapter(mangquanjogger,getContext());
        recyclerviewJoggerShort.setHasFixedSize(true);
        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerviewJoggerShort.setLayoutManager(horizontalLayoutManagaer);
        recyclerviewJoggerShort.setAdapter(quanjoggerAdapter);
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        StringRequest stringRequest =new StringRequest(Request.Method.POST, StringURL.URL_SAN_PHAM_THEO_LOAI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object=jsonArray.getJSONObject(i);
                        mangquanjogger.add(new SanPham(object.getInt("id"),object.getString("tensanpham"),object.getInt("giasanpham"),
                                object.getString("hinhanhsanpham"),object.getString("motasanpham"),object.getInt("idloaisanpham")));
                        quanjoggerAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("AAA",error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map=new HashMap<String,String>();
                map.put("idloaisanpham",String.valueOf(IDQUANSHORT));
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
    private void loadDataQuanShort() {
        int IDQUANSHORT=2;
        mangquanshort=new ArrayList<>();
        quanshortAdapter=new SanPhamAdapter(mangquanshort,getContext());
        recyclerviewQuanShort.setHasFixedSize(true);
        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerviewQuanShort.setLayoutManager(horizontalLayoutManagaer);
        recyclerviewQuanShort.setAdapter(quanshortAdapter);
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        StringRequest stringRequest =new StringRequest(Request.Method.POST, StringURL.URL_SAN_PHAM_THEO_LOAI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object=jsonArray.getJSONObject(i);
                        mangquanshort.add(new SanPham(object.getInt("id"),object.getString("tensanpham"),object.getInt("giasanpham"),
                                object.getString("hinhanhsanpham"),object.getString("motasanpham"),object.getInt("idloaisanpham")));
                        quanshortAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("AAA",error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map=new HashMap<String,String>();
                map.put("idloaisanpham",String.valueOf(IDQUANSHORT));
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void loadDataQuanJean() {
        int IDQUANJEANS=1;
        mangquanjeans=new ArrayList<>();
        quanjeansAdapter=new SanPhamAdapter(mangquanjeans,getContext());
        recyclerviewQuanJeans.setHasFixedSize(true);
        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerviewQuanJeans.setLayoutManager(horizontalLayoutManagaer);
        recyclerviewQuanJeans.setAdapter(quanjeansAdapter);
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        StringRequest stringRequest =new StringRequest(Request.Method.POST, StringURL.URL_SAN_PHAM_THEO_LOAI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object=jsonArray.getJSONObject(i);
                        mangquanjeans.add(new SanPham(object.getInt("id"),object.getString("tensanpham"),object.getInt("giasanpham"),
                                object.getString("hinhanhsanpham"),object.getString("motasanpham"),object.getInt("idloaisanpham")));
                        quanjeansAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("AAA",error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map=new HashMap<String,String>();
                map.put("idloaisanpham",String.valueOf(IDQUANJEANS));
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
    private void ActionViewFlipper() {
        ArrayList<String> quangcao=new ArrayList<>();
        quangcao.add("https://st.app1h.com/uploads/company72/image/2019/11/01/5dbc49c233e67.jpeg");
        quangcao.add("https://st.app1h.com/uploads/company72/image/2019/11/01/5dbc49d119437.jpeg");
        quangcao.add("https://st.app1h.com/uploads/company72/image/2019/11/01/5dbc49ddb1655.jpeg");
        for (int i = 0; i < quangcao.size(); i++) {
            ImageView imageView=new ImageView(getContext());
            Picasso.get().load(quangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewflipper.addView(imageView);
        }
        viewflipper.setFlipInterval(5000);
        viewflipper.setAutoStart(true);
        Animation animation= AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_right);
        Animation animation1= AnimationUtils.loadAnimation(getContext(),R.anim.slide_out_right);
        viewflipper.setInAnimation(animation);
        viewflipper.setOutAnimation(animation1);
    }

}