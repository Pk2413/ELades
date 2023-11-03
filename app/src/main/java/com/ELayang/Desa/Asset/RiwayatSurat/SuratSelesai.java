package com.ELayang.Desa.Asset.RiwayatSurat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ELayang.Desa.DataModel.RiwayatSurat.ModelSelesai;
import com.ELayang.Desa.R;

import java.util.ArrayList;
import java.util.List;

public class SuratSelesai extends RecyclerView.Adapter<SuratSelesai.RecyclerViewHolder> {

    List<ModelSelesai> data ;

    public SuratSelesai(ArrayList<ModelSelesai> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public SuratSelesai.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_surat_diajukan, parent, false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SuratSelesai.RecyclerViewHolder holder, int position) {
        ModelSelesai item = data.get(position);
        holder.nomor.setText(item.getId());
        holder.nama.setText(item.getNama());
        holder.kode.setText(item.getKode_surat());
        holder.tanggal.setText(item.getTanggal());
    }

    @Override
    public int getItemCount() {
        return (data != null) ? data.size() : 0;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView nomor, nama, kode, tanggal;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            nomor =itemView.findViewById(R.id.nomor);
            nama = itemView.findViewById(R.id.nama);
            kode = itemView.findViewById(R.id.kode_surat);
            tanggal = itemView.findViewById(R.id.tanggal);
        }
    }
}
