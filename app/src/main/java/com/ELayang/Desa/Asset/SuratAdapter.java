package com.ELayang.Desa.Asset;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ELayang.Desa.DataModel.ModelSurat;
import com.ELayang.Desa.R;

import java.util.ArrayList;
import java.util.List;

public class SuratAdapter extends RecyclerView.Adapter<SuratAdapter.RecycleViewHolder> {
    private List<ModelSurat> data;

    public SuratAdapter(List<ModelSurat> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycle_view, parent, false);
        return new RecycleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position) {
        ModelSurat surat = data.get(position);
        holder.nama.setText(surat.getKode_surat());
        holder.keterangan.setText(surat.getKeterangan());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class RecycleViewHolder extends RecyclerView.ViewHolder {
        private TextView nama, keterangan;

        public RecycleViewHolder(View view) {
            super(view);
            nama = view.findViewById(R.id.textsatu);
            keterangan = view.findViewById(R.id.textdua);
        }
    }
}
