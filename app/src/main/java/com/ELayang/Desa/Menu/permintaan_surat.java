package com.ELayang.Desa.Menu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ELayang.Desa.API.APIRequestData;
import com.ELayang.Desa.API.RetroServer;
import com.ELayang.Desa.Asset.Adapter.SuratAdapter;
import com.ELayang.Desa.DataModel.ModelSurat;
import com.ELayang.Desa.DataModel.ResponSurat;
import com.ELayang.Desa.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class permintaan_surat extends AppCompatActivity {
    private ArrayList<ModelSurat> data = new ArrayList<>(); // Inisialisasi ArrayList
//    RecyclerView recyclerView = findViewById(R.id.view);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permintaan_surat);
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponSurat> getSuratRespons = ardData.surat();
        SharedPreferences sharedPreferences = getSharedPreferences("prefSurat", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        getSuratRespons.enqueue(new Callback<ResponSurat>() {

            @Override
            public void onResponse(Call<ResponSurat> call, Response<ResponSurat> response) {
                if (response.isSuccessful()) {
                    ResponSurat responSurat = response.body();
                    if (responSurat != null && responSurat.getKode() == 1) {
                        ArrayList<ModelSurat> suratList = responSurat.getdata();

// Inisialisasi data jika belum diinisialisasi sebelumnya
                        if (data == null) {
                            data = new ArrayList<>();
                        }

// Ambil data pertama (jika ada) untuk disimpan ke SharedPreferences
                        if (suratList != null && !suratList.isEmpty()) {
                            // Tambahkan data surat ke ArrayList dan set up RecyclerView
                            ModelSurat user = response.body().getdata().get(0);
                            editor.putString("kode_surat", user.getKode_surat());
                            editor.putString("keterangan", user.getKeterangan());
                            editor.apply();
                            data.addAll(suratList);
                            setUpRecyclerView();
                        } else {
                            // Handle ketika data surat kosong
                            Toast.makeText(permintaan_surat.this, "Data surat kosong", Toast.LENGTH_SHORT).show();
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

//        recyclerView.setSelected(true);


        SuratAdapter.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView txtkode_surat = findViewById(R.id.textsatu);
                TextView txtketerangan = findViewById(R.id.textdua);

                String kodeSurat = sharedPreferences.getString("kode_surat", "");
                String keterangan = sharedPreferences.getString("keterangan", "");

//                String kodeSurat = (String) txtkode_surat.getText();  // Dapatkan kode_surat dari tag
//                String keterangan = (String) txtketerangan.getText();
                Intent intent = new Intent(permintaan_surat.this, detail_permintaan_surat.class);

//                Log.e("DATA",kodeSurat);
                intent.putExtra("kode_surat", kodeSurat);
                intent.putExtra("keterangan", keterangan);
                startActivity(intent);
            }


        });
    }

    // Set up RecyclerView
    private void setUpRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.view);
        SuratAdapter suratAdapter = new SuratAdapter(data, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(permintaan_surat.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(suratAdapter);
    }

}
