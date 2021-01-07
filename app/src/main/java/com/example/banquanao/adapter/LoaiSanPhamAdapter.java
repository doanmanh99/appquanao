package com.example.banquanao.adapter;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.banquanao.R;
import com.example.banquanao.model.LoaiSanPham;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class LoaiSanPhamAdapter extends BaseAdapter {
    List<LoaiSanPham> list;
    Context context;

    public LoaiSanPhamAdapter(List<LoaiSanPham> list, Context context) {
        this.list = list;
        this.context = context;
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
    public class viewHolder{
        TextView txtvLoaiSanPham;
        ImageView imgLoaiSanPham;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder viewHolder=null;
        if (viewHolder==null){
            viewHolder=new viewHolder();
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.line_loaisanpham,null);
            viewHolder.imgLoaiSanPham=convertView.findViewById(R.id.imgLoaiSanPham);
            viewHolder.txtvLoaiSanPham=convertView.findViewById(R.id.txtvLoaiSanPham);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (viewHolder) convertView.getTag();
        }
        LoaiSanPham loaiSanPham=(LoaiSanPham) getItem(position);
        viewHolder.txtvLoaiSanPham.setText(loaiSanPham.getTenLoaiSanPham());
        Picasso.get().load(loaiSanPham.getHinhAnhLoaiSanPham()).into(viewHolder.imgLoaiSanPham);
        return convertView;
    }
}
