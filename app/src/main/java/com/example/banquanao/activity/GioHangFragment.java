package com.example.banquanao.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
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
import com.example.banquanao.adapter.GioHangAdapter;
import com.example.banquanao.ultil.StringURL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import static com.example.banquanao.activity.MainActivity.kt;


public class GioHangFragment extends Fragment {
    static TextView txtvTien;
    ListView lvgiohang;
    TextView txtvthongbao;
    Button btnThanhToan;
    GioHangAdapter gioHangAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_gio_hang, container, false);
        lvgiohang=view.findViewById(R.id.lvgiohang);
        txtvthongbao=view.findViewById(R.id.txtvthongbao);
        txtvTien=view.findViewById(R.id.txtvTien);
        btnThanhToan=view.findViewById(R.id.btnThanhToan);
        gioHangAdapter=new GioHangAdapter(getContext(),MainActivity.mangGioHang);
        lvgiohang.setAdapter(gioHangAdapter);
        EvenUltil();
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.mangGioHang.size()>0){
                    if (kt==false) {
                        LoginDialog loginDialog = new LoginDialog();
                        loginDialog.show(getParentFragmentManager(), loginDialog.getTag());
                    }else {
                        ThemDonHang();
                    }
                }else{
                    Toast.makeText(getContext(),"Giỏ hàng chưa có sản phẩm",Toast.LENGTH_LONG).show();
                }
            }
        });
        CheckData();
        CatchOnItemListView();
        return view;
    }
    private void ThemDonHang() {

                RequestQueue queue= Volley.newRequestQueue(getContext());
                StringRequest request=new StringRequest(Request.Method.POST, StringURL.URL_CHI_TIET_DON_HANG,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.equals("Success")){
                                    MainActivity.mangGioHang.clear();
                                    Toast.makeText(getContext(),"Bạn thêm đơn hàng thành công",Toast.LENGTH_LONG).show();
                                    Intent intent=new Intent(getContext(),MainActivity.class);
                                    startActivity(intent);
                                }else {
                                    Toast.makeText(getContext(),"Bạn thêm đơn hàng thất bại",Toast.LENGTH_LONG).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        JSONArray jsonArray=new JSONArray();
                        for (int i = 0; i < MainActivity.mangGioHang.size(); i++) {
                            JSONObject jsonObject=new JSONObject();
                            try {
                                jsonObject.put("makhachhang",LoginDialog.id);
                                jsonObject.put("masanpham",MainActivity.mangGioHang.get(i).getIDSP());
                                jsonObject.put("tensanpham",MainActivity.mangGioHang.get(i).getTenSP());
                                jsonObject.put("soluong",MainActivity.mangGioHang.get(i).getSoLuongSP());
                                jsonObject.put("giatien",MainActivity.mangGioHang.get(i).getGiaSp());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            jsonArray.put(jsonObject);
                        }
                        HashMap<String,String> map=new HashMap<String,String>();
                        map.put("json",jsonArray.toString());
                        return map;
                    }
                };
                queue.add(request);
    }
    private void CatchOnItemListView() {
        lvgiohang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                builder.setTitle("Xác nhận xoá sản phẩm");
                builder.setMessage("Bạn có chắc chắn xoá sản phẩm này");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (MainActivity.mangGioHang.size() <=0){
                            txtvthongbao.setVisibility(View.VISIBLE);
                        }else {
                            MainActivity.mangGioHang.remove(position);
                            gioHangAdapter.notifyDataSetChanged();
                            EvenUltil();
                            if (MainActivity.mangGioHang.size() <=0){
                                txtvthongbao.setVisibility(View.VISIBLE);
                            }else {
                                txtvthongbao.setVisibility(View.INVISIBLE);
                                gioHangAdapter.notifyDataSetChanged();
                                EvenUltil();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        gioHangAdapter.notifyDataSetChanged();
                        EvenUltil();
                    }
                });
                builder.show();
                return true;
            }
        });
    }
    private void CheckData() {
        if(MainActivity.mangGioHang.size() <=0){
            gioHangAdapter.notifyDataSetChanged();
            txtvthongbao.setVisibility(View.VISIBLE);
            lvgiohang.setVisibility(View.INVISIBLE);
        }else {
            gioHangAdapter.notifyDataSetChanged();
            txtvthongbao.setVisibility(View.INVISIBLE);
            lvgiohang.setVisibility(View.VISIBLE);
        }
    }
    public static void EvenUltil() {
        long tongtien=0;
        for (int i = 0; i < MainActivity.mangGioHang.size(); i++) {
            tongtien+=MainActivity.mangGioHang.get(i).getGiaSp();
        }
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        txtvTien.setText(decimalFormat.format(tongtien)+" Đ");
    }
}