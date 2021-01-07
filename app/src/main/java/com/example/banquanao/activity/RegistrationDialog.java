package com.example.banquanao.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

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

public class RegistrationDialog extends DialogFragment {
    EditText edtTaiKhoan,edtMatKhau,edtTenKhachHang,edtSDTKhachHang,edtEmailKhachHang,edtDiaChiKhachHang;
    Button btnOK,btnCancel;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.dialog_registration,null);
        builder.setView(view).setTitle("Đăng Nhập");
        mapping(view);
        EventClickButton();
        return builder.create();
    }
    private void EventClickButton() {
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taikhoan=edtTaiKhoan.getText().toString().trim();
                String matkhau=edtMatKhau.getText().toString().trim();
                String ten=edtTenKhachHang.getText().toString().trim();
                String sdt=edtSDTKhachHang.getText().toString().trim();
                String email=edtEmailKhachHang.getText().toString().trim();
                String diachi=edtDiaChiKhachHang.getText().toString().trim();
                if (taikhoan.length()>0 && matkhau.length()>0 && ten.length()>0 && sdt.length()>0 && email.length()>0 && diachi.length()>0){
                    RequestQueue requestQueue= Volley.newRequestQueue(getContext());
                    StringRequest stringRequest=new StringRequest(Request.Method.POST, StringURL.URL_DANG_KY,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    if (response.equals("Success")){
                                        Toast.makeText(getContext(),"Đăng ký thành công",Toast.LENGTH_LONG).show();
                                        LoginDialog loginDialog=new LoginDialog();
                                        loginDialog.show(getParentFragmentManager(),loginDialog.getTag());
//                                        MainActivity.kt=true;
                                        dismiss();
                                    }else{
                                        Toast.makeText(getContext(),"Đăng ký không thành công",Toast.LENGTH_LONG).show();
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
                            HashMap<String,String> map=new HashMap<>();
                            map.put("taikhoan",taikhoan);
                            map.put("matkhau",matkhau);
                            map.put("ten",ten);
                            map.put("sdt",sdt);
                            map.put("email",email);
                            map.put("diachi",diachi);
                            return map;
                        }
                    };
                    requestQueue.add(stringRequest);
                }else {
                    Toast.makeText(getContext(),"Mời bạn nhập đủ thông tin",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void mapping(View view) {
        edtTaiKhoan=view.findViewById(R.id.edtTaiKhoan);
        edtMatKhau=view.findViewById(R.id.edtMatKhau);
        edtTenKhachHang=view.findViewById(R.id.edtTenKhachHang);
        edtSDTKhachHang=view.findViewById(R.id.edtSDTKhachHang);
        edtEmailKhachHang=view.findViewById(R.id.edtEmailKhachHang);
        edtDiaChiKhachHang=view.findViewById(R.id.edtDiaChiKhachHang);
        btnOK=view.findViewById(R.id.btnOK);
        btnCancel=view.findViewById(R.id.btnCancel);
    }
}
