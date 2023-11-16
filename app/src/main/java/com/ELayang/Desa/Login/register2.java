package com.ELayang.Desa.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ELayang.Desa.API.APIRequestData;
import com.ELayang.Desa.API.RetroServer;
import com.ELayang.Desa.DataModel.Register.ResponOTP;
import com.ELayang.Desa.DataModel.Register.ResponRegister2;
import com.ELayang.Desa.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class register2 extends AppCompatActivity {
    CountDownTimer countDownTimer;
    private long timeLeftInMillis = 30000;
Button lanjut, kirimbos;
TextView timer;
ImageButton kembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        EditText kode_otp = findViewById(R.id.kode_otp);

        SharedPreferences sharedPreferences =getSharedPreferences("prefRegister", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","");

        kembali = findViewById(R.id.kembali);
        kembali.setOnClickListener(v -> {
            finish();
        });
        lanjut = findViewById(R.id.lanjut);
        lanjut.setOnClickListener(v ->{
            String otp = kode_otp.getText().toString();

            APIRequestData apiRequestData = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<ResponRegister2> call = apiRequestData.register2(username,otp);

            call.enqueue(new Callback<ResponRegister2>() {
                @Override
                public void onResponse(Call<ResponRegister2> call, Response<ResponRegister2> response) {
                    if (response.body().kode == 0) {

                        // Simpan semua data pengguna ke SharedPreferences
//                    ModelRegister1 user = response.body().getData().get(0);

                        Intent pindah = new Intent(register2.this, register3.class);
                        startActivity(pindah);
                        finish();

                    } else if (response.body().kode == 1) {
                        Toast.makeText(register2.this, "Kode otp salah ", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponRegister2> call, Throwable t) {

                }
            });
        });

        kirimbos = findViewById(R.id.kirimbos);
        timer = findViewById(R.id.timer);

        kirimbos.setOnClickListener(view ->{
//            Toast.makeText(this, "kirim bozz", Toast.LENGTH_SHORT).show();

            APIRequestData apiRequestData = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<ResponOTP> call = apiRequestData.kirim_otp(username);

            call.enqueue(new Callback<ResponOTP>() {
                @Override
                public void onResponse(Call<ResponOTP> call, Response<ResponOTP> response) {
                    if (response.body().kode == 0){
                        Toast.makeText(register2.this, "error username tidak ikut", Toast.LENGTH_SHORT).show();
                    } else if (response.body().kode == 1) {
                        Toast.makeText(register2.this, "Berhasil terkirim", Toast.LENGTH_SHORT).show();
                    } else if (response.body().kode ==2 ) {
                        Toast.makeText(register2.this, "Gagal Mengirim Kode OTP", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponOTP> call, Throwable t) {
                    Toast.makeText(register2.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timeLeftInMillis = millisUntilFinished;
                    updateTimer();
                }

                @Override
                public void onFinish() {
                    kirimbos.setEnabled(true); // Aktifkan tombol setelah 30 detik berlalu
                    timer.setText("Selesai!");
                    resetTimer();
                }
            }.start();

            kirimbos.setEnabled(false);


        });
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                kirimbos.setEnabled(true); // Aktifkan tombol setelah 30 detik berlalu
                timer.setText("Selesai!");
                resetTimer();
            }
        }.start();

        kirimbos.setEnabled(false);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK){
            return true;

        }
        return super.onKeyDown(keyCode, event);
    }

    private void updateTimer() {
        int seconds = (int) (timeLeftInMillis / 1000);
        timer.setText(String.valueOf(seconds) + " detik");
    }
    private void resetTimer(){
        timeLeftInMillis = 30000;
    }
}