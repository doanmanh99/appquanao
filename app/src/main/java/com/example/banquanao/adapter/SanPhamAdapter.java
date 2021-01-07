package com.example.banquanao.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.banquanao.R;
import com.example.banquanao.activity.ChiTietSanPhamActivity;
import com.example.banquanao.activity.GioHangFragment;
import com.example.banquanao.model.LoaiSanPham;
import com.example.banquanao.model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ItemHolder> {
    List<SanPham> list;
    Context context;
    public SanPhamAdapter(List<SanPham> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.line_sanpham,null);
        ItemHolder itemHolder=new ItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        SanPham sanPham=list.get(position);
//        holder.txtvTenSanPham.setMaxLines(2);
//        holder.txtvTenSanPham.setEllipsize(TextUtils.TruncateAt.END);
        holder.txtvTenSanPham.setText(sanPham.getTenSanPham());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        holder.txtvGiaSanPham.setText("Giá : "+decimalFormat.format(sanPham.getGiaSanPham())+" Đ");
        Picasso.get().load(sanPham.getAnhSanPham())
                .error(R.drawable.khong)
                .into(holder.imgSanPham);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,ChiTietSanPhamActivity.class);
                intent.putExtra("ttsanpham",sanPham);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        public ImageView imgSanPham;
        public TextView txtvTenSanPham,txtvGiaSanPham;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            imgSanPham= itemView.findViewById(R.id.imgSanPham);
            txtvTenSanPham= itemView.findViewById(R.id.txtvTenSanPham);
            txtvGiaSanPham= itemView.findViewById(R.id.txtvGiaSanPham);
        }

    }

}
