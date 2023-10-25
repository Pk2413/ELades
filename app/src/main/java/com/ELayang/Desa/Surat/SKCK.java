package com.ELayang.Desa.Surat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ELayang.Desa.API.APIRequestData;
import com.ELayang.Desa.API.RetroServer;
import com.ELayang.Desa.DataModel.ResponSkck;
import com.ELayang.Desa.Menu.permintaan_surat;
import com.ELayang.Desa.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SKCK extends AppCompatActivity {

//    ImageButton kembali;

    EditText nik ,
            nama ,
            tgl_lahir ,
            kebangsaan ,
            agama ,
            status ,
            pekerjaan ,
            tempat_tinggal ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.surat_skck);

        nik = findViewById(R.id.e_nik);
        nama = findViewById(R.id.e_nama);
        tgl_lahir = findViewById(R.id.e_tempat_lahir);
        kebangsaan = findViewById(R.id.e_kebangsaan);
        agama = findViewById(R.id.e_agama);
        status = findViewById(R.id.e_kawin);
        pekerjaan = findViewById(R.id.e_pekerjaan);
        tempat_tinggal = findViewById(R.id.e_tempat_tinggal);



//        kembali.findViewById(R.id.balikbos);
//        kembali.setOnClickListener(v->{
//            finish();
//        });

        Button kirim = findViewById(R.id.kirim);
        kirim.setOnClickListener(v->{
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponSkck> skck = ardData.skck(nama.getText().toString(),nik.getText().toString(),tgl_lahir.getText().toString(),
                kebangsaan.getText().toString(),agama.getText().toString(),status.getText().toString(),
                pekerjaan.getText().toString(),tempat_tinggal.getText().toString());
        skck.enqueue(new Callback <ResponSkck>(){

            @Override
            public void onResponse(Call<ResponSkck> call, Response<ResponSkck> response) {
                ResponSkck responSkck = response.body();
                if (responSkck.isKode() == true) {
                    Toast.makeText(SKCK.this, "berhasil menambahkan", Toast.LENGTH_SHORT).show();
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
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Toast.makeText(this, "gunakan tombol kembali yang ada di atas", Toast.LENGTH_SHORT).show();
            return true;

        }
        return super.onKeyDown(keyCode, event);
    }


    public void kembali(View view) {
        finish();
    }
}