package com.ELayang.Desa.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ELayang.Desa.API.APIRequestData;
import com.ELayang.Desa.API.RetroServer;
import com.ELayang.Desa.DataModel.Lupa_Password.ResponPassword2;
import com.ELayang.Desa.DataModel.Register.ResponOTP;
import com.ELayang.Desa.DataModel.Register.ResponRegister3;
import com.ELayang.Desa.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class lupa_password_2 extends AppCompatActivity {
    CountDownTimer countDownTimer;
    private long timeLeftInMillis = 30000;

    EditText pass1 ,pass2, otp;
    Button lanjut, kirim;
    ImageButton kembali;
    TextView timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_password2);
        SharedPreferences sharedPreferences = getSharedPreferences("prefLupa_password", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String username = sharedPreferences.getString("username","");

        pass1 =findViewById(R.id.pass1);
        pass2 = findViewById(R.id.pass2);
        otp = findViewById(R.id.otp);


        lanjut = findViewById(R.id.lanjut);
        lanjut.setOnClickListener(view -> {
            String password1 = pass1.getText().toString();
            String password2 = pass2.getText().toString();
            String kode = otp.getText().toString();

            if (TextUtils.isEmpty(password1)) {
                pass1.setError("Password Harus Diisi");
                pass1.requestFocus();
            } else if(pass2.getText().toString().isEmpty()) {
                pass2.setError("Password Harus Diisi");
                pass2.requestFocus();
            }else if(pass2.length() <6) {
                pass1.setError("Password Harus Lebih dari 6 karakter");
                pass1.requestFocus();
            } else if (otp.getText().toString().isEmpty()) {
                otp.setError("Kode OTP Harus Diisi");
                otp.requestFocus();
            }else if(password1.equals(password2)){
                APIRequestData apiRequestData = RetroServer.konekRetrofit().create(APIRequestData.class);
                Call<ResponPassword2> call = apiRequestData.lupa_password2(kode,password2,username);

//                Toast.makeText(this, "'"+username+"'", Toast.LENGTH_SHORT).show();
                
                call.enqueue(new Callback<ResponPassword2>() {
                    @Override
                    public void onResponse(Call<ResponPassword2> call, Response<ResponPassword2> response) {
                        String pesan = response.body().getPesan();
                        if (response.body().kode == 0){
                            Toast.makeText(lupa_password_2.this, pesan, Toast.LENGTH_SHORT).show();
                        } else if (response.body().kode ==1) {
                            Toast.makeText(lupa_password_2.this, pesan, Toast.LENGTH_SHORT).show();
                            Intent buka = new Intent(lupa_password_2.this, login.class);
                            startActivity(buka);
                        } else if (response.body().kode ==2 ) {
                            Toast.makeText(lupa_password_2.this, pesan, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponPassword2> call, Throwable t) {
                        Toast.makeText(lupa_password_2.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }else {
                pass2.setError("Password Tidak Sama");
                pass2.requestFocus();
            }
        });
        kembali = findViewById(R.id.kembali);
        kembali.setOnClickListener(view ->{
            finish();
        });
        kirim = findViewById(R.id.kirimbos);
        timer = findViewById(R.id.timer);

        kirim.setOnClickListener(view ->{

            APIRequestData apiRequestData = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<ResponOTP> call = apiRequestData.kirim_otp(username);

            call.enqueue(new Callback<ResponOTP>() {
                @Override
                public void onResponse(Call<ResponOTP> call, Response<ResponOTP> response) {
                    if (response.body().kode == 0){
                        Toast.makeText(lupa_password_2.this, "error username tidak ikut", Toast.LENGTH_SHORT).show();
                    } else if (response.body().kode == 1) {
                        Toast.makeText(lupa_password_2.this, "Berhasil terkirim", Toast.LENGTH_SHORT).show();
                    } else if (response.body().kode ==2 ) {
                        Toast.makeText(lupa_password_2.this, "Gagal Mengirim Kode OTP", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponOTP> call, Throwable t) {
                    Toast.makeText(lupa_password_2.this, t.getMessage(), Toast.LENGTH_SHORT).show();
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
