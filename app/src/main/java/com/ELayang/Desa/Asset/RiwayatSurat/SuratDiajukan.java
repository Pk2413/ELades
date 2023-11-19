package com.ELayang.Desa.Asset.RiwayatSurat;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ELayang.Desa.DataModel.RiwayatSurat.ModelDiajukan;
import com.ELayang.Desa.Menu.detail_permintaan_surat;
import com.ELayang.Desa.R;

import java.util.ArrayList;
import java.util.List;

public class SuratDiajukan extends RecyclerView.Adapter<SuratDiajukan.RecycleViewHolder> {

    List<ModelDiajukan> data ;

    public SuratDiajukan(ArrayList<ModelDiajukan> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public SuratDiajukan.RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_surat_diajukan, parent, false);
        return new RecycleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SuratDiajukan.RecycleViewHolder holder, int position) {
        ModelDiajukan item = data.get(position);
        holder.nomor.setText(item.getId());
        holder.nama.setText(item.getNama());
        holder.kode.setText(item.getKode_surat());
        holder.tanggal.setText(item.getTanggal());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Data dari item yang diklik
                    String kode = item.getKode_surat();
                    String no_pengajuan = item.getId();
//                    String namaAdvis = item.getNama_advis();

                    // Kirim data ke aktivitas selanjutnya
                    Intent intent = new Intent(v.getContext(), detail_permintaan_surat.class);
                    intent.putExtra("kode_surat", kode);
                    intent.putExtra("no_pengajuan", no_pengajuan);
//                    intent.putExtra("nama_advis", namaAdvis);
                    v.getContext().startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        return (data != null) ? data.size() : 0;
    }

    public class RecycleViewHolder extends RecyclerView.ViewHolder {
        private TextView nomor, nama, kode, tanggal;

        public RecycleViewHolder(@NonNull View itemView) {
            super(itemView);
            nomor =itemView.findViewById(R.id.nomor);
            nama = itemView.findViewById(R.id.nama);
            kode = itemView.findViewById(R.id.kode_surat);
            tanggal = itemView.findViewById(R.id.tanggal);
        }
    }
}