package com.ELayang.Desa.Menu;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ELayang.Desa.API.APIRequestData;
import com.ELayang.Desa.API.RetroServer;
import com.ELayang.Desa.Asset.SuratAdapter;
import com.ELayang.Desa.DataModel.ModelSurat;
import com.ELayang.Desa.DataModel.ResponSurat;
import com.ELayang.Desa.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class permintaan_surat extends AppCompatActivity {
    private ArrayList<ModelSurat> dataSurat = new ArrayList<>(); // Inisialisasi ArrayList

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permintaan_surat);
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponSurat> getSuratRespons = ardData.surat();
        SharedPreferences sharedPreferences = getSharedPreferences("prefSurat", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        getSuratRespons.enqueue(new Callback<ResponSurat>() {

            @Override
            public void onResponse(Call<ResponSurat> call, Response<ResponSurat> response) {
                if (response.isSuccessful()) {
                    ResponSurat responSurat = response.body();
                    if (responSurat != null && responSurat.getKode() == 1) {
                        ArrayList<ModelSurat> suratList = responSurat.getdataSurat();

// Inisialisasi dataSurat jika belum diinisialisasi sebelumnya
                        if (dataSurat == null) {
                            dataSurat = new ArrayList<>();
                        }

// Ambil data pertama (jika ada) untuk disimpan ke SharedPreferences
                        if (!suratList.isEmpty()) {
                            ModelSurat surat = suratList.get(0);
                            editor.putString("kode_surat", surat.getKode_surat());
                            editor.putString("keterangan", surat.getKeterangan());
                            editor.apply();

                            // Tambahkan data surat ke ArrayList dan set up RecyclerView
                            dataSurat.addAll(suratList);
                            setUpRecyclerView();
                        }

                    } else {
                        // Handle jika respons kode bukan 1
                        Toast.makeText(permintaan_surat.this, "Kode respons bukan 1", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Handle jika respons tidak berhasil
                    Toast.makeText(permintaan_surat.this, "Respons tidak berhasil", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponSurat> call, Throwable t) {
                Toast.makeText(permintaan_surat.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Set up RecyclerView
    private void setUpRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.view);
        SuratAdapter suratAdapter = new SuratAdapter(dataSurat);
        LinearLayoutManager layoutManager = new LinearLayoutManager(permintaan_surat.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(suratAdapter);
    }
}
