package com.ELayang.Desa.Asset.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ELayang.Desa.DataModel.ModelKolom;
import com.ELayang.Desa.R;

import java.util.List;

public class IsiSuratAdapter extends RecyclerView.Adapter<IsiSuratAdapter.RecycleViewHolder> {

    private List<ModelKolom> data;

    public IsiSuratAdapter(List<ModelKolom> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_isi_surat, parent, false);
        return new RecycleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position) {
        ModelKolom item = data.get(position);
        holder.textViewNamaKolom.setText(item.getNamaKolom());
        holder.editTextNilaiKolom.setText(item.getNilaiKolom());
    }

    @Override
    public int getItemCount() {
        return (data != null) ? data.size() : 0;
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
