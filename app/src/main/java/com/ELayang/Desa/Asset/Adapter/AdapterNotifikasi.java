package com.ELayang.Desa.Asset.Adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ELayang.Desa.R;

import java.util.ArrayList;

public class AdapterNotifikasi extends RecyclerView.Adapter<AdapterNotifikasi.recycleViewHolder> {

private ArrayList<ModelNotifikasi> data;

    public AdapterNotifikasi(ArrayList<ModelNotifikasi> data) {
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
        holder.nopengajuan.setText(data.get(position).getNopengajuan());
        holder.keterangan.setText(data.get(position).getKeterangan());
        holder.kodesurat.setText(data.get(position).getKode());
        holder.tanggal.setText(data.get(position).getTanggal());


//        holder.icon.setImageResource(R.drawable.notif);
    }

    @Override
    public int getItemCount() {

        return (data != null) ? data.size():0;
    }
    public class recycleViewHolder extends  RecyclerView.ViewHolder{
        private TextView nopengajuan, keterangan, kodesurat, tanggal;
//        private ImageView icon;

        public recycleViewHolder(View view){
            super(view);
            kodesurat = (TextView) view.findViewById(R.id.kode_surat);
            keterangan = (TextView)  view.findViewById(R.id.keterangan);
            nopengajuan = (TextView)  view.findViewById(R.id.no_pengajuan);
            tanggal = view.findViewById(R.id.tanggal);
//            icon = view.findViewById(R.id.ikon);
        }
    }
}