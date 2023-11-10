package com.ELayang.Desa.Surat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ELayang.Desa.API.APIRequestData;
import com.ELayang.Desa.API.RetroServer;
import com.ELayang.Desa.DataModel.Surat.ResponSuratijin;
import com.ELayang.Desa.R;

import org.chromium.base.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Surat_Ijin extends AppCompatActivity {

    DatePickerDialog picker;
    String selectedGender;

    EditText nama, nik, jenis_kelamin, tempat_tgl_lahir, tgl_lahir,
            kewarganegaraan, agama, pekerjaan, alamat, tempat_kerja, bagian, tanggal, alasan;

    Button kirim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.surat_ijin);

        SharedPreferences sharedPreferences = getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        nama = findViewById(R.id.e_nama);
        nik = findViewById(R.id.e_nik);
        tempat_tgl_lahir = findViewById(R.id.e_tempat_lahir);
        tgl_lahir = findViewById(R.id.e_tanggal);
        kewarganegaraan = findViewById(R.id.e_kebangsaan);
        agama = findViewById(R.id.e_agama);
        pekerjaan = findViewById(R.id.e_pekerjaan);
        alamat = findViewById(R.id.e_alamat);
        tempat_kerja = findViewById(R.id.e_tempat_kerja);
        bagian = findViewById(R.id.e_bagian);
        tanggal = findViewById(R.id.e_tanggal_bawah);
        alasan = findViewById(R.id.e_alasan);


        tanggal.setFocusableInTouchMode(false);
        tanggal.setOnClickListener(v -> {
            final Calendar cldr = Calendar.getInstance();
            int day = cldr.get(Calendar.DAY_OF_MONTH);
            int month = cldr.get(Calendar.MONTH);
            int year = cldr.get(Calendar.YEAR);

            picker = new DatePickerDialog(Surat_Ijin.this, (view, year1, monthOfYear, dayOfMonth) -> {
                cldr.set(year1, monthOfYear, dayOfMonth);
                SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", new Locale("id", "ID"));
                String formattedDate = sdf.format(cldr.getTime());
                tanggal.setText(formattedDate);
            }, year, month, day);
            picker.show();
        });

        tgl_lahir.setFocusableInTouchMode(false);
        tgl_lahir.setOnClickListener(v -> {
            final Calendar cldr = Calendar.getInstance();
            int day = cldr.get(Calendar.DAY_OF_MONTH);
            int month = cldr.get(Calendar.MONTH);
            int year = cldr.get(Calendar.YEAR);

            picker = new DatePickerDialog(Surat_Ijin.this, (view, year1, monthOfYear, dayOfMonth) -> {
                cldr.set(year1, monthOfYear, dayOfMonth);
                SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", new Locale("id", "ID"));
                String formattedDate = sdf.format(cldr.getTime());
                tgl_lahir.setText(formattedDate);
            }, year, month, day);
            picker.show();
        });

        String[] genderOptions = getResources().getStringArray(R.array.jenis_kelamin_array);
        Spinner spinnerGender = findViewById(R.id.e_jenis);
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genderOptions);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(genderAdapter);
        spinnerGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedGender = genderOptions[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                Toast.makeText(Surat_Ijin.this, "Pilih Jenis Kelamin", Toast.LENGTH_SHORT).show();
            }
        });

        Button kirim = findViewById(R.id.kirim);
        kirim.setEnabled(true);
        kirim.setOnClickListener(v -> {
            String tempat_tanggal_lahir = tempat_tgl_lahir.getText() + ", " + tgl_lahir.getText();

            APIRequestData apiRequestData = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<ResponSuratijin> call = apiRequestData.suratijin(username, nama.getText().toString(),
                    nik.getText().toString(), selectedGender, tempat_tanggal_lahir, kewarganegaraan.getText().toString(),
                    agama.getText().toString(), pekerjaan.getText().toString(), alamat.getText().toString(),
                    tempat_kerja.getText().toString(), bagian.getText().toString(), tanggal.getText().toString(),
                    alasan.getText().toString());

            call.enqueue(new Callback<ResponSuratijin>() {
                @Override
                public void onResponse(Call<ResponSuratijin> call, Response<ResponSuratijin> response) {
                    ResponSuratijin respon = response.body();
                    if (respon.isKode() == true) {
                        Toast.makeText(Surat_Ijin.this, "Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponSuratijin> call, Throwable t) {
                    Toast.makeText(Surat_Ijin.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("error", t.getMessage());
                }
            });
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Toast.makeText(this, "gunakan tombol kembali yang ada di atas", Toast.LENGTH_SHORT).show();
            return true;

        }
        return super.onKeyDown(keyCode, event);
    }

    public void kembali(View view) {
        finish();
    }
}