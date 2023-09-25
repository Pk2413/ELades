package com.ELayang.Desa.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;
import android.widget.ImageButton;

import com.ELayang.Desa.R;

public class register2 extends AppCompatActivity {
Button lanjut;
ImageButton kembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        kembali = findViewById(R.id.kembali);
        kembali.setOnClickListener(v -> {
            finish();
        });
        lanjut = findViewById(R.id.lanjut);
        lanjut.setOnClickListener(v ->{
                Intent buka = new Intent(this, register3.class);
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