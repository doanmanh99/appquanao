package com.example.banquanao.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.banquanao.R;
import com.example.banquanao.model.GioHang;
import com.example.banquanao.model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ChiTietSanPhamActivity extends AppCompatActivity {
    Toolbar toolbarchitietsanpham;
    ImageView imgChiTietSanPham;
    TextView txtvTenChiTietSanPham,txtvGiaChiTietSanPham,txtvMoTaChiTietSanPham;
    Spinner spinner;
    Button btnDatMua;
    Integer giasp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        mapping();
        SanPham sanPham= (SanPham) getIntent().getSerializableExtra("ttsanpham");
        ActionBar();
        getData();
        CatchEventSpinner();

        btnDatMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.mangGioHang.size()>0){
                    int sl=Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean exits=false;
                    for (int i = 0; i < MainActivity.mangGioHang.size(); i++) {
                        if (MainActivity.mangGioHang.get(i).getIDSP()== sanPham.getID()){
                            MainActivity.mangGioHang.get(i).setSoLuongSP(MainActivity.mangGioHang.get(i).getSoLuongSP()+sl);
                            MainActivity.mangGioHang.get(i).setGiaSp(MainActivity.mangGioHang.get(i).getSoLuongSP()*sanPham.getGiaSanPham());
                            exits=true;
                        }
                    }
                    if(exits==false){
                        int soluong=Integer.parseInt(spinner.getSelectedItem().toString());
                        long gia=soluong*giasp;
                        MainActivity.mangGioHang.add(new GioHang(sanPham.getID(),sanPham.getTenSanPham(),gia,sanPham.getAnhSanPham(),soluong));
                    }
                }else {
                    int soluong=Integer.parseInt(spinner.getSelectedItem().toString());
                    long gia=soluong*giasp;
                    MainActivity.mangGioHang.add(new GioHang(sanPham.getID(),sanPham.getTenSanPham(),gia,sanPham.getAnhSanPham(),soluong));
                }
                Toast.makeText(getApplicationContext(),"Thêm vào giỏ hàng thành công. Mời bạn vào giỏ hàng để kiểm tra",Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
    private void CatchEventSpinner() {
        Integer[] soluong=new Integer[]{1,2,3,4,5,6,7,8,9};
        ArrayAdapter<Integer> arrayAdapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,soluong);
        spinner.setAdapter(arrayAdapter);
    }

    private void getData() {
        SanPham sanPham= (SanPham) getIntent().getSerializableExtra("ttsanpham");
        txtvTenChiTietSanPham.setText(sanPham.getTenSanPham());
        giasp=sanPham.getGiaSanPham();
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtvGiaChiTietSanPham.setText("Giá : " + decimalFormat.format(sanPham.getGiaSanPham()) + " Đ");
        txtvMoTaChiTietSanPham.setText(sanPham.getMoTaSanPham());
        Picasso.get().load(sanPham.getAnhSanPham())
                .placeholder(R.drawable.cho)
                .error(R.drawable.khong)
                .into(imgChiTietSanPham);
    }

    private void ActionBar() {
        setSupportActionBar(toolbarchitietsanpham);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarchitietsanpham.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void mapping() {
        toolbarchitietsanpham=findViewById(R.id.toolbarchitietsanpham);
        imgChiTietSanPham=findViewById(R.id.imgChiTietSanPham);
        txtvTenChiTietSanPham=findViewById(R.id.txtvTenChiTietSanPham);
        txtvGiaChiTietSanPham=findViewById(R.id.txtvGiaChiTietSanPham);
        txtvMoTaChiTietSanPham=findViewById(R.id.txtvMoTaChiTietSanPham);
        spinner=findViewById(R.id.spinner);
        btnDatMua=findViewById(R.id.btnDatMua);
    }
}