package com.ELayang.Desa.Asset.Adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ELayang.Desa.DataModel.Notifikasi.ModelNotifikasi;
import com.ELayang.Desa.Menu.detail_permintaan_surat;
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
        holder.keterangan.setText(data.get(position).getAlasan());
        holder.kodesurat.setText(data.get(position).getKode());
        holder.tanggal.setText(data.get(position).getTanggal());
        holder.status.setText(data.get(position).getStatus());

        holder.itemView.setOnClickListener(v->{
            if (data.get(position).getStatus().equals("Tolak")){
                String kode = data.get(position).getKode();
                String no_pengajuan = data.get(position).getNopengajuan();
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

        return (data != null) ? data.size():0;
    }
    public class recycleViewHolder extends  RecyclerView.ViewHolder{
        private TextView nopengajuan, keterangan, kodesurat, tanggal, status;
//        private ImageView icon;

        public recycleViewHolder(View view){
            super(view);
            kodesurat = (TextView) view.findViewById(R.id.kode_surat);
            keterangan = (TextView)  view.findViewById(R.id.keterangan);
            nopengajuan = (TextView)  view.findViewById(R.id.no_pengajuan);
            tanggal = view.findViewById(R.id.tanggal);
            status = view.findViewById(R.id.status);
//            icon = view.findViewById(R.id.ikon);
        }
    }
}