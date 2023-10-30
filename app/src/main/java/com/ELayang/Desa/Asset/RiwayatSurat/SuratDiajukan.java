package com.ELayang.Desa.Asset.RiwayatSurat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.ELayang.Desa.Asset.IsiSuratAdapter;
import com.ELayang.Desa.Asset.recycleView;
import com.ELayang.Desa.R;

public class SuratDiajukan extends RecyclerView.Adapter<SuratDiajukan.RecycleViewHolder> {

    @NonNull
    @Override
    public SuratDiajukan.RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_surat_diajukan, parent, false);
        return new RecycleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SuratDiajukan.RecycleViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class RecycleViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewNamaKolom;
        public EditText editTextNilaiKolom;

        public RecycleViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNamaKolom = itemView.findViewById(R.id.textViewNamaKolom);
            editTextNilaiKolom = itemView.findViewById(R.id.editTextNilaiKolom);
        }
    }
}