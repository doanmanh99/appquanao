package com.example.banquanao.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class LoginDialog extends DialogFragment {
    EditText edtTaiKhoan,edtMatKhau;
    CheckBox checkboxNhoTaiKhoan;
    TextView txtvQuenMatKhau,txtvDangKy;
    Button btnDangNhap;
    SharedPreferences sharedPreferences;
    static int id;
    static String tenkhachhang,sdt,email,diachi,taikhoan,matkhau;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.dialog_login,null);
        builder.setView(view).setTitle("Đăng Nhập");
        mapping(view);
        init();
        EventClickButton();
        EventClickTextView();
        return builder.create();
    }

    private void init() {
        sharedPreferences=getActivity().getSharedPreferences("login.xml",MODE_PRIVATE);
        edtTaiKhoan.setText(sharedPreferences.getString("username",""));
        edtMatKhau.setText(sharedPreferences.getString("password",""));
        checkboxNhoTaiKhoan.setChecked(sharedPreferences.getBoolean("checked",false));
    }

    private void EventClickTextView() {
        txtvDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                RegistrationDialog registrationDialog=new RegistrationDialog();
                registrationDialog.show(getParentFragmentManager(),registrationDialog.getTag());
            }
        });
    }

    private void EventClickButton() {
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tk=edtTaiKhoan.getText().toString();
                String mk=edtMatKhau.getText().toString();
                RequestQueue requestQueue= Volley.newRequestQueue(getContext());
                StringRequest stringRequest =new StringRequest(Request.Method.POST, StringURL.URL_TAI_KHOAN, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("")){
                            Toast.makeText(getContext(),"Tài khoản hoặc mật khẩu không chính xác",Toast.LENGTH_LONG).show();
                        }else{
                            try {
                                MainActivity.kt=true;
                                JSONObject object = new JSONObject(response);
                                taikhoan=object.getString("taikhoan");
                                matkhau=object.getString("matkhau");
                                id=object.getInt("id");
                                tenkhachhang=object.getString("ten");
                                sdt=object.getString("sdt");
                                email=object.getString("email");
                                diachi=object.getString("diachi");
                                if (checkboxNhoTaiKhoan.isChecked()){
                                    SharedPreferences.Editor editor= sharedPreferences.edit();
                                    editor.putString("username",taikhoan);
                                    editor.putString("password",matkhau);
                                    editor.putBoolean("checked",checkboxNhoTaiKhoan.isChecked());
                                    editor.commit();
                                }else {
                                    SharedPreferences.Editor editor= sharedPreferences.edit();
                                    editor.remove("username");
                                    editor.remove("password");
                                    editor.remove("checked");
                                    editor.commit();
                                }
                                dismiss();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
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
                        map.put("taikhoan",tk);
                        map.put("matkhau",mk);
                        return map;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });
    }

    private void mapping(View view) {
        edtTaiKhoan=view.findViewById(R.id.edtTaiKhoan);
        edtMatKhau=view.findViewById(R.id.edtMatKhau);
        checkboxNhoTaiKhoan=view.findViewById(R.id.checkboxNhoTaiKhoan);
        txtvQuenMatKhau=view.findViewById(R.id.txtvQuenMatKhau);
        txtvDangKy=view.findViewById(R.id.txtvDangKy);
        btnDangNhap=view.findViewById(R.id.btnDangNhap);
    }
}
