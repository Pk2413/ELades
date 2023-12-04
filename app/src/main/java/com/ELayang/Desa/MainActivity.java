package com.ELayang.Desa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.ELayang.Desa.API.APIRequestData;
import com.ELayang.Desa.API.RetroServer;
import com.ELayang.Desa.DataModel.Akun.ResponLogin;
import com.ELayang.Desa.Login.login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","");
        String nama = sharedPreferences.getString("nama","");
        String password = sharedPreferences.getString("password","");

        if(username.equals("")){ new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent buka = new Intent(MainActivity.this, login.class);
                startActivity(buka);
                finish();
            }
        }, 500);

        }else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this, menu.class);
                    startActivity(intent);
                    finish();
                }
            }, 500);
        }
    }

}