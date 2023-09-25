package com.ELayang.Desa.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ELayang.Desa.R;

public class lupa_password_2 extends AppCompatActivity {
    CountDownTimer countDownTimer;
    private long timeLeftInMillis = 30000;
    Button lanjut, kirim;
    ImageButton kembali;
    TextView timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_password2);


        lanjut = findViewById(R.id.lanjut);
        lanjut.setOnClickListener(view -> {
            Intent buka = new Intent(this, login.class);
            startActivity(buka);
        });
        kembali = findViewById(R.id.kembali);
        kembali.setOnClickListener(view ->{
            finish();
        });
        kirim = findViewById(R.id.kirimbos);
        timer = findViewById(R.id.timer);

        kirim.setOnClickListener(view ->{
            Toast.makeText(this, "kirim bozz", Toast.LENGTH_SHORT).show();
            countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timeLeftInMillis = millisUntilFinished;
                    updateTimer();
                }

                @Override
                public void onFinish() {
                    kirim.setEnabled(true); // Aktifkan tombol setelah 30 detik berlalu
                    timer.setText("Selesai!");
                    resetTimer();
                }
            }.start();

            kirim.setEnabled(false);
        });
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                kirim.setEnabled(true); // Aktifkan tombol setelah 30 detik berlalu
                timer.setText("Selesai!");
                resetTimer();
            }
        }.start();

        kirim.setEnabled(false); // Matikan tombol saat aktivitas dimulai
    }

    private void updateTimer() {
        int seconds = (int) (timeLeftInMillis / 1000);
        timer.setText(String.valueOf(seconds) + " detik");
    }
private void resetTimer(){
        timeLeftInMillis = 30000;
}
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK){
            return true;

        }
        return super.onKeyDown(keyCode, event);
    }
    }
