package com.ELayang.Desa.Menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ELayang.Desa.DataModel.ModelKolom;
import com.ELayang.Desa.R;
import com.ELayang.Desa.Surat.SKCK;
import com.ELayang.Desa.Surat.SKTM;
import com.ELayang.Desa.Surat.Surat_Ijin;

import java.util.ArrayList;
import java.util.Objects;

public class detail_permintaan_surat extends AppCompatActivity {
    private ImageView kembali;
    ArrayList<ModelKolom> modelKoloms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_permintaan_surat);
        TextView kodeSuratTextView = null;
        String kodeSurat = null;
        String nopengajuan = null;

        kembali = findViewById(R.id.kembali);
        kembali.setOnClickListener(v -> {
            finish();
        });
        Intent intent = getIntent();
        if (intent != null) {

            kodeSurat = intent.getStringExtra("kode_surat");
            String keterangan = intent.getStringExtra("keterangan");
            nopengajuan = intent.getStringExtra("no_pengajuan");

            // Tampilkan data di TextView atau komponen lainnya
            kodeSuratTextView = findViewById(R.id.kode_surat);
            TextView keteranganTextView = findViewById(R.id.keterangan);

            kodeSuratTextView.setText(kodeSurat);
            keteranganTextView.setText(keterangan);
        }

        if (Objects.equals(kodeSurat, "skck")) {
            intent = new Intent(this, SKCK.class);
            intent.putExtra("nopengajuan", nopengajuan);
            finish();
            startActivity(intent);

        } else if (Objects.equals(kodeSurat, "surat_ijin")) {
            intent = new Intent(this, Surat_Ijin.class);
            intent.putExtra("nopengajuan", nopengajuan);
            finish();
            startActivity(intent);

        } else if (Objects.equals(kodeSurat, "sktm")) {
            intent = new Intent(this, SKTM.class);
            intent.putExtra("nopengajuan", nopengajuan);
            finish();
            startActivity(intent);
        } else {
            Toast.makeText(this, "maaf sedang terjadi error", Toast.LENGTH_SHORT).show();
        }


    }
}