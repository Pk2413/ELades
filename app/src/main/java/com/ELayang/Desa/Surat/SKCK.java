package com.ELayang.Desa.Surat;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ELayang.Desa.API.APIRequestData;
import com.ELayang.Desa.API.RetroServer;
import com.ELayang.Desa.DataModel.Surat.ModelSkck;
import com.ELayang.Desa.DataModel.Surat.ResponSkck;
import com.ELayang.Desa.R;
import com.google.android.gms.common.api.Api;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SKCK extends AppCompatActivity {

    DatePickerDialog picker;
    String selectedGender;
    private boolean hasilCek = true;

//    ImageButton kembali;
//    TextView kode = findViewById(R.id.kode_surat),
//        ket= findViewById(R.id.keterangan);

    EditText nik, nama, tempat_lahir, kebangsaan, agama, status, pekerjaan, tempat_tinggal, tanggal;
    Button kirim, update;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.surat_skck);
        SharedPreferences sharedPreferences = getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
//        String keterangan = sharedPreferences.getString("keterangan","");
//        kode.setText(kode_surat);
//        ket.setText(keterangan);

        kirim = findViewById(R.id.kirim);
        update = findViewById(R.id.update);


        nik = findViewById(R.id.e_nik);
        nama = findViewById(R.id.e_nama);
        tempat_lahir = findViewById(R.id.e_tempat_lahir);
        kebangsaan = findViewById(R.id.e_kebangsaan);
        agama = findViewById(R.id.e_agama);
        status = findViewById(R.id.e_kawin);
        pekerjaan = findViewById(R.id.e_pekerjaan);
        tempat_tinggal = findViewById(R.id.e_tempat_tinggal);
        tanggal = findViewById(R.id.e_tanggal);

        tanggal.setFocusableInTouchMode(false);
        tanggal.setOnClickListener(v -> {
            final Calendar cldr = Calendar.getInstance();
            int day = cldr.get(Calendar.DAY_OF_MONTH);
            int month = cldr.get(Calendar.MONTH);
            int year = cldr.get(Calendar.YEAR);

            picker = new DatePickerDialog(SKCK.this, (view, year1, monthOfYear, dayOfMonth) -> {
                cldr.set(year1, monthOfYear, dayOfMonth);
                SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", new Locale("id", "ID"));
                String formattedDate = sdf.format(cldr.getTime());
                tanggal.setText(formattedDate);
            }, year, month, day);
            picker.show();
        });
        Intent intent = getIntent();
        String nopengajuan = intent.getStringExtra("nopengajuan");
        if (nopengajuan != null) {
            kirim.setVisibility(View.INVISIBLE);

            String kode = "0";
            APIRequestData apiRequestData = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<ResponSkck> call = apiRequestData.ambilskck(nopengajuan, kode);

            call.enqueue(new Callback<ResponSkck>() {
                @Override
                public void onResponse(Call<ResponSkck> call, Response<ResponSkck> response) {
                    if (response.body().kode) {
                        Toast.makeText(SKCK.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                        ModelSkck model = response.body().getData().get(0);
                        nik.setText(model.getNik());
                        nama.setText(model.getNama());
                        tempat_lahir.setText(model.getTempat());
                        kebangsaan.setText(model.getKebangsaan());
                        agama.setText(model.getAgama());
                        status.setText(model.getStatus_perkawinan());
                        pekerjaan.setText(model.getPekerjaan());
                        tempat_tinggal.setText(model.getTempat_tinggal());
                        tanggal.setText(model.getTanggal());
                    } else {
                        Toast.makeText(SKCK.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                    }
                }


                @Override
                public void onFailure(Call<ResponSkck> call, Throwable t) {
                    Log.e("error setText", t.getMessage());
                }
            });

        } else {
            update.setVisibility(View.GONE);
        }

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
                Toast.makeText(SKCK.this, "Pilih Jenis Kelamin", Toast.LENGTH_SHORT).show();
            }
        });


//        kembali.findViewById(R.id.balikbos);
//        kembali.setOnClickListener(v->{
//            finish();
//        });


        kirim.setEnabled(true);
        kirim.setOnClickListener(v -> {
            cek(nik);
            cek(nama);
            cek(tempat_lahir);
            cek(kebangsaan);
            cek(agama);
            cek(status);
            cek(pekerjaan);
            cek(tempat_tinggal);
//            cek(tanggal);

            if (!hasilCek) {
                Toast.makeText(this, "Isi semua formulir terlebih dahulu", Toast.LENGTH_SHORT).show();
                reset();
            } else {
                kirim.setEnabled(false);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Apakah data yang anda inputkan sudah benar?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                String tempat_tanggal_lahir = tempat_lahir.getText().toString() + ", " + tanggal.getText().toString();

                                APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
                                Call<ResponSkck> skck = ardData.skck(nama.getText().toString(), nik.getText().toString()
                                        , tempat_tanggal_lahir, kebangsaan.getText().toString(), agama.getText().toString()
                                        , status.getText().toString(), pekerjaan.getText().toString()
                                        , tempat_tinggal.getText().toString(), username, selectedGender
                                );
                                skck.enqueue(new Callback<ResponSkck>() {

                                    @Override
                                    public void onResponse(Call<ResponSkck> call, Response<ResponSkck> response) {
                                        ResponSkck responSkck = response.body();
                                        if (responSkck.isKode()) {
                                            Toast.makeText(SKCK.this, "berhasil menambahkan", Toast.LENGTH_SHORT).show();


                                            finish();
                                        } else {
                                            kirim.setEnabled(true);
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ResponSkck> call, Throwable t) {
                                        Toast.makeText(SKCK.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                                        kirim.setEnabled(true);
                                    }
                                });
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
            }
        });

//        update.setEnabled(true);
        update.setOnClickListener(v -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Apakah data yang anda masukan sudah benar?")
                    .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String tempat_tanggal_lahir = tempat_lahir.getText().toString() + ", " + tanggal.getText().toString();
                            String kode = "1";
                            APIRequestData apiRequestData = RetroServer.konekRetrofit().create(APIRequestData.class);
                            Call<ResponSkck> call = apiRequestData.updateskck(nopengajuan, kode, nama.getText().toString(), nik.getText().toString()
                                    , tempat_tanggal_lahir, kebangsaan.getText().toString(), agama.getText().toString()
                                    , status.getText().toString(), pekerjaan.getText().toString()
                                    , tempat_tinggal.getText().toString(), selectedGender);

                            call.enqueue(new Callback<ResponSkck>() {
                                @Override
                                public void onResponse(Call<ResponSkck> call, Response<ResponSkck> response) {
                                    if (response.body().kode){
                                        Toast.makeText(SKCK.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                                        finish();
                                    }else {
                                        Toast.makeText(SKCK.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponSkck> call, Throwable t) {
                                    Log.e("error update skck", t.getMessage());
                                }
                            });
                        }
                    })
                    .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

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
        builder.setMessage("Apakah anda yakin ingin kembali?")
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

    private void cek(EditText editText) {
        if (TextUtils.isEmpty(editText.getText().toString())) {
            editText.setError("Harus Diisi");
            editText.requestFocus();
            hasilCek = false;
        } else {
            hasilCek = true;
        }
    }

    private void reset() {
        hasilCek = true;
    }

}