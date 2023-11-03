package com.ELayang.Desa.Asset;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
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
import com.ELayang.Desa.Menu.detail_permintaan_surat;
import com.ELayang.Desa.Menu.permintaan_surat;
import com.ELayang.Desa.R;

import java.util.List;

public class SuratAdapter extends RecyclerView.Adapter<SuratAdapter.RecycleViewHolder> {

    String kode= null;

    SharedPreferences sharedPreferences;
    private List<ModelSurat> data;
    private static View.OnClickListener clickListener;

    public SuratAdapter(List<ModelSurat> data, Context context) {
        this.sharedPreferences =context.getSharedPreferences("prefSurat", Context.MODE_PRIVATE);
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
                    int position = holder.getBindingAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        ModelSurat clickedItem = data.get(position);
//                        String kode = clickedItem.getKode_surat();
//                        v.setTag(clickedItem.getKode_surat()); // Menyimpan kode_surat sebagai tag
//                        clickListener.onClick(v); // Memanggil metode onClick dengan parameter view
//                        Log.e("KODE : ", clickedItem.getKode_surat());
//                        Log.e("clicked :", clickedItem.getKode_surat());
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("kode_surat", clickedItem.getKode_surat());
                        editor.putString("keterangan", clickedItem.getKeterangan());
                        editor.apply();
                        clickListener.onClick(v);
                    }
                }
            }
        });
    }

    public  ModelSurat getItemAtPosition(int position) {

        return data.get(position);
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
