package com.ELayang.Desa.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.ImageButton;

import com.ELayang.Desa.R;

public class register1 extends AppCompatActivity {
    ImageButton kembali;
    Button lanjut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);


        kembali = findViewById(R.id.kembali);
        kembali.setOnClickListener(view -> {
            finish();
        });
        lanjut = findViewById(R.id.lanjut);
        lanjut.setOnClickListener(view ->{
            Intent buka = new Intent(this, register2.class);
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