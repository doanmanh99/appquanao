package com.example.banquanao.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

public class CapNhatActivity extends AppCompatActivity {
    Toolbar toolbar;
    EditText edtTen,edtSDT,edtEmail,edtMatKhauCu,edtMatKhauMoi,edtMatKhauMoiOK,edtDiaChi;
    Button btnCapNhat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap_nhat);
        mapping();
        Actionbar();
        edtTen.setText(LoginDialog.tenkhachhang);
        edtEmail.setText(LoginDialog.email);
        edtSDT.setText(LoginDialog.sdt);
        edtDiaChi.setText(LoginDialog.diachi);
        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CapNhat();
            }
        });
    }

    private void CapNhat() {
        String ten=edtTen.getText().toString().trim();
        String sdt=edtSDT.getText().toString().trim();
        String email=edtEmail.getText().toString().trim();
        String matkhaucu=edtMatKhauCu.getText().toString().trim();
        String matkhaumoi=edtMatKhauMoi.getText().toString().trim();
        String matkhaumoiOK=edtMatKhauMoiOK.getText().toString().trim();
        String diachi=edtDiaChi.getText().toString().trim();
        if (matkhaumoi.equals(matkhaumoiOK)) {
            if (matkhaucu.equals(LoginDialog.matkhau)) {
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest stringRequest = new StringRequest(Request.Method.POST, StringURL.URL_UPDATE,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.equals("Success")) {
                                    Toast.makeText(getApplicationContext(), "Cập nhật thành công", Toast.LENGTH_LONG).show();
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Cập nhật không thành công", Toast.LENGTH_LONG).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("id", String.valueOf(LoginDialog.id));
                        map.put("taikhoan", String.valueOf(LoginDialog.taikhoan));
                        map.put("matkhau", matkhaumoi);
                        map.put("ten", ten);
                        map.put("sdt", sdt);
                        map.put("email", email);
                        map.put("diachi", diachi);
                        return map;
                    }
                };
                requestQueue.add(stringRequest);
            }else {
                Toast.makeText(getApplicationContext(),"Mật khẩu không đúng",Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(getApplicationContext(),"Mật khẩu mói không trùng nhau",Toast.LENGTH_LONG).show();
        }
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
    private void mapping() {
        toolbar=findViewById(R.id.toolbar);
        edtTen=findViewById(R.id.edtTen);
        edtSDT=findViewById(R.id.edtSDT);
        edtEmail=findViewById(R.id.edtEmail);
        edtMatKhauCu=findViewById(R.id.edtMatKhauCu);
        edtMatKhauMoi=findViewById(R.id.edtMatKhauMoi);
        edtMatKhauMoiOK=findViewById(R.id.edtMatKhauMoiOK);
        btnCapNhat=findViewById(R.id.btnCapNhat);
        edtDiaChi=findViewById(R.id.edtDiaChi);
    }
}