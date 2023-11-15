package com.ELayang.Desa.Asset.Adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ELayang.Desa.R;

import java.util.ArrayList;

public class recycleView extends RecyclerView.Adapter<recycleView.recycleViewHolder> {

private ArrayList<settergetter> data;

    public recycleView(ArrayList<settergetter> data) {
        this.data = data;
    }


    @NonNull
    @Override
    public recycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycle_view, parent, false);
        return new recycleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull recycleViewHolder holder, int position) {
        holder.nama.setText(data.get(position).getNama());
        holder.keterangan.setText(data.get(position).getKeterangan());
        holder.icon.setImageResource(R.drawable.notif);
    }

    @Override
    public int getItemCount() {

        return (data != null) ? data.size():0;
    }
    public class recycleViewHolder extends  RecyclerView.ViewHolder{
        private TextView  nama, keterangan;
        private ImageView icon;

        public recycleViewHolder(View view){
            super(view);
            nama = (TextView) view.findViewById(R.id.textsatu);
            keterangan = (TextView)  view.findViewById(R.id.textdua);
            icon = view.findViewById(R.id.ikon);
        }
    }
}