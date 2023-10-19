package com.ELayang.Desa.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.ELayang.Desa.API.APIRequestData;
import com.ELayang.Desa.API.RetroServer;
import com.ELayang.Desa.DataModel.ModelResponse;
import com.ELayang.Desa.DataModel.ModelUsers;
import com.ELayang.Desa.R;
import com.ELayang.Desa.menu;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        EditText username = findViewById(R.id.username),
                email = findViewById(R.id.email);

        String usernameText = username.getText().toString();
        String emailtext = email.getText().toString();



    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK){
            return true;

        }
        return super.onKeyDown(keyCode, event);
    }
}