package com.ELayang.Desa.Surat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ELayang.Desa.API.APIRequestData;
import com.ELayang.Desa.API.RetroServer;
import com.ELayang.Desa.DataModel.ResponSkck;
import com.ELayang.Desa.Menu.permintaan_surat;
import com.ELayang.Desa.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SKCK extends AppCompatActivity {

    DatePickerDialog picker;
    String selectedGender;

//    ImageButton kembali;
//    TextView kode = findViewById(R.id.kode_surat),
//        ket= findViewById(R.id.keterangan);

    EditText nik, nama, tempat_lahir, kebangsaan, agama, status, pekerjaan, tempat_tinggal, tanggal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.surat_skck);




        SharedPreferences sharedPreferences = getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","");
//        String keterangan = sharedPreferences.getString("keterangan","");
//        kode.setText(kode_surat);
//        ket.setText(keterangan);


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


        Button kirim = findViewById(R.id.kirim);
        kirim.setEnabled(true);
        kirim.setOnClickListener(v -> {
            String tempat_tanggal_lahir = tempat_lahir.getText().toString() +", "+ tanggal.getText().toString();

            APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<ResponSkck> skck = ardData.skck(nama.getText().toString(), nik.getText().toString()
                    , tempat_tanggal_lahir, kebangsaan.getText().toString(), agama.getText().toString()
                    , status.getText().toString(), pekerjaan.getText().toString()
                    , tempat_tinggal.getText().toString(),username, selectedGender
                    );
            skck.enqueue(new Callback<ResponSkck>() {

                @Override
                public void onResponse(Call<ResponSkck> call, Response<ResponSkck> response) {
                    ResponSkck responSkck = response.body();
                    if (responSkck.isKode() == true) {
                        Toast.makeText(SKCK.this, "berhasil menambahkan", Toast.LENGTH_SHORT).show();

                        kirim.setEnabled(false);
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<ResponSkck> call, Throwable t) {
                    Toast.makeText(SKCK.this, t.getMessage(), Toast.LENGTH_SHORT).show();
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