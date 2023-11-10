package com.ELayang.Desa.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ELayang.Desa.API.APIRequestData;
import com.ELayang.Desa.API.RetroServer;
import com.ELayang.Desa.Asset.IsiSuratAdapter;
import com.ELayang.Desa.Asset.recycleView;
import com.ELayang.Desa.Asset.settergetter;
import com.ELayang.Desa.DataModel.ModelKolom;
import com.ELayang.Desa.R;
import com.ELayang.Desa.Surat.SKCK;
import com.ELayang.Desa.Surat.SKTM;
import com.ELayang.Desa.Surat.Surat_Ijin;

import org.chromium.base.Callback;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Response;

public class detail_permintaan_surat extends AppCompatActivity {
    private ImageView kembali;
    ArrayList<ModelKolom> modelKoloms ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_permintaan_surat);
        TextView kodeSuratTextView = null;
        String kodeSurat = null;

        kembali = findViewById(R.id.kembali);
        kembali.setOnClickListener(v -> {
            finish();
        });
        Intent intent = getIntent();
        if (intent != null) {

            kodeSurat= intent.getStringExtra("kode_surat");
            String keterangan = intent.getStringExtra("keterangan");

            // Tampilkan data di TextView atau komponen lainnya
            kodeSuratTextView = findViewById(R.id.kode_surat);
            TextView keteranganTextView = findViewById(R.id.keterangan);

            kodeSuratTextView.setText(kodeSurat);
            keteranganTextView.setText(keterangan);
        }

        if(Objects.equals(kodeSurat, "skck")){
            intent = new Intent(this, SKCK.class);
            intent.putExtra("kode_surat", kodeSurat);
            finish();
            startActivity(intent);

        } else if (Objects.equals(kodeSurat, "surat_ijin")) {
            intent = new Intent(this, Surat_Ijin.class);
            intent.putExtra("kode_surat", kodeSurat);
            finish();
            startActivity(intent);

        } else if(Objects.equals(kodeSurat, "sktm")) {
        intent = new Intent(this, SKTM.class);
            finish();
        startActivity(intent);
        }
        else {
            Toast.makeText(this, "maaf sedang terjadi error", Toast.LENGTH_SHORT).show();
        }









    }



    private void addData(String nama, String keterangan, int x) {
        modelKoloms = new ArrayList<>();
        for (int i = 0; i < x; i++) {
            modelKoloms.add(new ModelKolom(String.valueOf(i), keterangan));
        }
    }
}