package com.ELayang.Desa.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.ImageButton;

import com.ELayang.Desa.R;

public class lupa_password extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_password);

        Button lanjut;
        ImageButton kembali;
        lanjut = findViewById(R.id.lanjut);
        lanjut.setOnClickListener(view -> {
            Intent buka = new Intent(lupa_password.this, lupa_password_2.class);
            startActivity(buka);
        });
        kembali = findViewById(R.id.kembali);
        kembali.setOnClickListener(view ->{
            finish();
        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK){
            return true;

        }
        return super.onKeyDown(keyCode, event);
    }
}