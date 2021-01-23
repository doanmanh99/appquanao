package com.example.banquanao.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.banquanao.R;
import com.example.banquanao.adapter.SanPhamAdapter;
import com.example.banquanao.model.SanPham;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LichSuAdapter extends RecyclerView.Adapter<LichSuAdapter.ItemHolder> {
    List<SanPham> list;
    Context context;
    public LichSuAdapter(List<SanPham> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.line_lichsu,null);
        LichSuAdapter.ItemHolder itemHolder=new LichSuAdapter.ItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        SanPham sanPham=list.get(position);
        Picasso.get().load(sanPham.getAnhSanPham())
                .error(R.drawable.khong)
                .into(holder.image);
        holder.txvName.setText(sanPham.getTenSanPham());
        holder.txvDesc.setText(sanPham.getMoTaSanPham());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView txvName,txvDesc;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            image= itemView.findViewById(R.id.image);
            txvDesc= itemView.findViewById(R.id.txvDesc);
            txvName= itemView.findViewById(R.id.txvName);
        }

    }
}
