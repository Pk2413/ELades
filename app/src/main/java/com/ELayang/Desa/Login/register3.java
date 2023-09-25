package com.ELayang.Desa.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.ImageButton;

import com.ELayang.Desa.R;

public class register3 extends AppCompatActivity {
ImageButton kembali;
Button lanjut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register3);

        kembali = findViewById(R.id.kembali);
        kembali.setOnClickListener(v -> {
            finish();
        });
        lanjut = findViewById(R.id.lanjut);
        lanjut.setOnClickListener(v ->{
            Intent buka = new Intent(this, login.class);
            startActivity(buka);
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