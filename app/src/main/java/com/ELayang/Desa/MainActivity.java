package com.ELayang.Desa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.ELayang.Desa.Login.login;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","");
        String nama = sharedPreferences.getString("nama","");
        Toast.makeText(this, username+"\n"+nama, Toast.LENGTH_SHORT).show();

        if(username.equals("")){ new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent buka = new Intent(MainActivity.this, login.class);
                startActivity(buka);
                finish(); // Tutup activity splash agar tidak kembali ke splash saat menekan tombol "Kembali".
            }
        }, 500);

        }else {


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this, menu.class);
                    startActivity(intent);
                    finish(); // Tutup activity splash agar tidak kembali ke splash saat menekan tombol "Kembali".
                }
            }, 500);
        }
    }

}