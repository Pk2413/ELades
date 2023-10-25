package com.ELayang.Desa.Asset;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ELayang.Desa.DataModel.ModelSurat;
import com.ELayang.Desa.R;

import java.util.List;

public class SuratAdapter extends RecyclerView.Adapter<SuratAdapter.RecycleViewHolder> {
    private List<ModelSurat> data;
    private static View.OnClickListener clickListener;

    public SuratAdapter(List<ModelSurat> data) {
        this.data = data;
    }

    public static void setOnItemClickListener(View.OnClickListener listener) {
        clickListener = listener;
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
        holder.icon.setImageResource(R.drawable.kertas);

        // Menangani klik pada item di sini
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    int position = holder.getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        ModelSurat clickedItem = data.get(position);
                        clickListener.onClick(v); // Memanggil metode onClick dengan parameter view
                        v.setTag(clickedItem.getKode_surat()); // Menyimpan kode_surat sebagai tag
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class RecycleViewHolder extends RecyclerView.ViewHolder {
        private TextView nama, keterangan;
        private ImageView icon;

        public RecycleViewHolder(View view) {
            super(view);
            nama = view.findViewById(R.id.textsatu);
            keterangan = view.findViewById(R.id.textdua);
            icon = view.findViewById(R.id.ikon);
        }
    }
}
