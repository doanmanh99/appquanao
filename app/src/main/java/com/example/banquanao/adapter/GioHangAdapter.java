package com.example.banquanao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.banquanao.R;
import com.example.banquanao.activity.GioHangFragment;
import com.example.banquanao.activity.MainActivity;
import com.example.banquanao.model.GioHang;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GioHangAdapter extends BaseAdapter {
    Context context;
    ArrayList<GioHang> list;

    public GioHangAdapter(Context context, ArrayList<GioHang> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHodel{
        ImageView imgGioHang;
        TextView txtvTenGioHang,txtvGiaGioHang;
        Button btnGiam,btnSoLuong,btnTang;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodel viewHodel=null;
        if (viewHodel==null){
            viewHodel=new ViewHodel();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.line_gio_hang,null);
            viewHodel.txtvTenGioHang=convertView.findViewById(R.id.txtvTenGioHang);
            viewHodel.txtvGiaGioHang=convertView.findViewById(R.id.txtvGiaGioHang);
            viewHodel.imgGioHang=convertView.findViewById(R.id.imgGioHang);
            viewHodel.btnGiam=convertView.findViewById(R.id.btnGiam);
            viewHodel.btnSoLuong=convertView.findViewById(R.id.btnSoLuong);
            viewHodel.btnTang=convertView.findViewById(R.id.btnTang);
            convertView.setTag(viewHodel);
        }else{
            viewHodel= (ViewHodel) convertView.getTag();
        }
        GioHang gioHang= (GioHang) getItem(position);
        viewHodel.txtvTenGioHang.setText(gioHang.getTenSP());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        viewHodel.txtvGiaGioHang.setText("Giá : "+decimalFormat.format(gioHang.getGiaSp())+" Đ");
        Picasso.get().load(gioHang.getHinhSP())
                .error(R.drawable.khong)
                .into(viewHodel.imgGioHang);
        viewHodel.btnSoLuong.setText(gioHang.getSoLuongSP()+"");
        int sl= Integer.parseInt(viewHodel.btnSoLuong.getText().toString());
        if (sl<=1){
            viewHodel.btnGiam.setVisibility(View.INVISIBLE);
        }else{
            viewHodel.btnGiam.setVisibility(View.VISIBLE);
            viewHodel.btnTang.setVisibility(View.VISIBLE);
        }
        ViewHodel finalViewHodel = viewHodel;
        viewHodel.btnTang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slmoi=Integer.parseInt(finalViewHodel.btnSoLuong.getText().toString())+1;
                int slht= MainActivity.mangGioHang.get(position).getSoLuongSP();
                long giaht=MainActivity.mangGioHang.get(position).getGiaSp();
                MainActivity.mangGioHang.get(position).setSoLuongSP(slmoi);
                long giamoi=(giaht*slmoi)/slht;
                MainActivity.mangGioHang.get(position).setGiaSp(giamoi);
                DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
                finalViewHodel.txtvGiaGioHang.setText(decimalFormat.format(giamoi)+ "Đ");
                GioHangFragment.EvenUltil();
                finalViewHodel.btnTang.setVisibility(View.VISIBLE);
                finalViewHodel.btnGiam.setVisibility(View.VISIBLE);
                finalViewHodel.btnSoLuong.setText(String.valueOf(slmoi));
            }
        });
        viewHodel.btnGiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slmoi=Integer.parseInt(finalViewHodel.btnSoLuong.getText().toString())-1;
                int slht= MainActivity.mangGioHang.get(position).getSoLuongSP();
                long giaht=MainActivity.mangGioHang.get(position).getGiaSp();
                MainActivity.mangGioHang.get(position).setSoLuongSP(slmoi);
                long giamoi=(giaht*slmoi)/slht;
                MainActivity.mangGioHang.get(position).setGiaSp(giamoi);
                DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
                finalViewHodel.txtvGiaGioHang.setText(decimalFormat.format(giamoi)+ "Đ");
                GioHangFragment.EvenUltil();
                if (slmoi<2){
                    finalViewHodel.btnGiam.setVisibility(View.INVISIBLE);
                    finalViewHodel.btnTang.setVisibility(View.VISIBLE);
                    finalViewHodel.btnSoLuong.setText(String.valueOf(slmoi));
                }else {
                    finalViewHodel.btnGiam.setVisibility(View.VISIBLE);
                    finalViewHodel.btnTang.setVisibility(View.VISIBLE);
                    finalViewHodel.btnSoLuong.setText(String.valueOf(slmoi));
                }
            }
        });
        return convertView;
    }
}
