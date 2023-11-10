package com.ELayang.Desa.Surat;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
import com.ELayang.Desa.DataModel.Surat.ResponSkck;
import com.ELayang.Desa.DataModel.Surat.ResponSktm;
import com.ELayang.Desa.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SKTM extends AppCompatActivity {
    DatePickerDialog picker;
    String selectedGender, username;

    String tempattanggallahirbapak, tempattanggallahiribu, tempattanggallahiranak;

    EditText namabapak, tempatbapak, tanggalbapak, pekerjaanbapak, alamatbapak;
    EditText namaibu, tempatibu, tanggalibu, pekerjaanibu, alamatibu;

    EditText namaanak, tempatanak, tanggalanak, alamatanak;
    Spinner jeniskelaminanak;

    Button kirim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.surat_sktm);

        SharedPreferences sharedPreferences = getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        username = sharedPreferences.getString("username", "");

        //bapak
        namabapak = findViewById(R.id.namabapak);
        tempatbapak = findViewById(R.id.tempatbapak);
        tanggalbapak = findViewById(R.id.tanggalbapak);
        tanggalbapak.setFocusableInTouchMode(false);
        tanggalbapak.setOnClickListener(v -> {
            final Calendar cldr = Calendar.getInstance();
            int day = cldr.get(Calendar.DAY_OF_MONTH);
            int month = cldr.get(Calendar.MONTH);
            int year = cldr.get(Calendar.YEAR);

            picker = new DatePickerDialog(SKTM.this, (view, year1, monthOfYear, dayOfMonth) -> {
                cldr.set(year1, monthOfYear, dayOfMonth);
                SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", new Locale("id", "ID"));
                String formattedDate = sdf.format(cldr.getTime());
                tanggalbapak.setText(formattedDate);
            }, year, month, day);
            picker.show();
        });
        pekerjaanbapak = findViewById(R.id.pekerjaanbapak);
        alamatbapak = findViewById(R.id.alamatbapak);

        //ibu
        namaibu = findViewById(R.id.namaibu);
        tempatibu = findViewById(R.id.tempatibu);
        tanggalibu = findViewById(R.id.tanggalibu);
        tanggalibu.setFocusableInTouchMode(false);
        tanggalibu.setOnClickListener(v -> {
            final Calendar cldr = Calendar.getInstance();
            int day = cldr.get(Calendar.DAY_OF_MONTH);
            int month = cldr.get(Calendar.MONTH);
            int year = cldr.get(Calendar.YEAR);

            picker = new DatePickerDialog(SKTM.this, (view, year1, monthOfYear, dayOfMonth) -> {
                cldr.set(year1, monthOfYear, dayOfMonth);
                SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", new Locale("id", "ID"));
                String formattedDate = sdf.format(cldr.getTime());
                tanggalibu.setText(formattedDate);
            }, year, month, day);
            picker.show();
        });
        pekerjaanibu = findViewById(R.id.pekerjaanibu);
        alamatibu = findViewById(R.id.alamatibu);

        //anak
        namaanak = findViewById(R.id.namaanak);
        tempatanak = findViewById(R.id.tempatanak);
        tanggalanak = findViewById(R.id.tanggalanak);
        tanggalanak.setFocusableInTouchMode(false);
        tanggalanak.setOnClickListener(v -> {
            final Calendar cldr = Calendar.getInstance();
            int day = cldr.get(Calendar.DAY_OF_MONTH);
            int month = cldr.get(Calendar.MONTH);
            int year = cldr.get(Calendar.YEAR);

            picker = new DatePickerDialog(SKTM.this, (view, year1, monthOfYear, dayOfMonth) -> {
                cldr.set(year1, monthOfYear, dayOfMonth);
                SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", new Locale("id", "ID"));
                String formattedDate = sdf.format(cldr.getTime());
                tanggalanak.setText(formattedDate);
            }, year, month, day);
            picker.show();
        });
        alamatanak = findViewById(R.id.alamatanak);

        //pilihan jenis kelamin
        String[] genderOptions = getResources().getStringArray(R.array.jenis_kelamin_array);
        jeniskelaminanak = findViewById(R.id.jeniskelamin);
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genderOptions);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jeniskelaminanak.setAdapter(genderAdapter);
        jeniskelaminanak.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedGender = genderOptions[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                Toast.makeText(SKTM.this, "Pilih Jenis Kelamin", Toast.LENGTH_SHORT).show();
            }
        });




        //api
//        APIRequestData apiRequestData = RetroServer.konekRetrofit().create(APIRequestData.class);
//        Call<ResponSktm> call = apiRequestData.sktm(username, namabapak.getText().toString(), tempattanggallahirbapak,pekerjaanbapak.getText().toString(), alamatbapak.getText().toString(),
//                namaibu.getText().toString(), tempattanggallahiribu, pekerjaanibu.getText().toString(), alamatibu.getText().toString(),
//                namaanak.getText().toString(), tempattanggallahiranak,selectedGender,alamatanak.getText().toString());
        kirim = findViewById(R.id.kirim);
        kirim.setEnabled(true);
        kirim.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Apakah kamu yakin ingin melanjutkan?")
                    .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            tempattanggallahirbapak = tempatbapak.getText().toString() + ", " + tanggalbapak.getText().toString();
                            tempattanggallahiribu = tempatibu.getText().toString() + ", " + tanggalibu.getText().toString();
                            tempattanggallahiranak = tempatanak.getText().toString() + ", " + tanggalanak.getText().toString();

                            APIRequestData apiRequestData = RetroServer.konekRetrofit().create(APIRequestData.class);
                            Call<ResponSktm> call = apiRequestData.sktm(username, namabapak.getText().toString(), tempattanggallahirbapak, pekerjaanbapak.getText().toString(), alamatbapak.getText().toString(),
                                    namaibu.getText().toString(), tempattanggallahiribu, pekerjaanibu.getText().toString(), alamatibu.getText().toString(),
                                    namaanak.getText().toString(), tempattanggallahiranak, selectedGender, alamatanak.getText().toString());


                            call.enqueue(new Callback<ResponSktm>() {
                                @Override
                                public void onResponse(Call<ResponSktm> call, Response<ResponSktm> response) {
                                    ResponSktm respon = response.body();
                                    if (respon.isKode() == true) {
                                        Toast.makeText(SKTM.this, "berhasil menambahkan", Toast.LENGTH_SHORT).show();

                                        kirim.setEnabled(false);
                                        finish();
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponSktm> call, Throwable t) {
                                    Toast.makeText(SKTM.this, "Gagal menambahkan data", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    })
                    .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Kode yang akan dijalankan jika tombol "Tidak" diklik
                            // Misalnya, batalkan operasi
                        }
                    })
                    .show();
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
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Apakah kamu yakin ingin melanjutkan?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();

    }

    private void tampilkanKonfirmasi() {

    }
}