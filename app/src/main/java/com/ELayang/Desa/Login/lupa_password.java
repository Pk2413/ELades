package com.ELayang.Desa.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.ELayang.Desa.API.APIRequestData;
import com.ELayang.Desa.API.RetroServer;
import com.ELayang.Desa.DataModel.Lupa_Password.ResponPassword1;
import com.ELayang.Desa.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class lupa_password extends AppCompatActivity {
    Button lanjut;
    ImageButton kembali;
    EditText username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_password);

        SharedPreferences sharedPreferences = getSharedPreferences("prefLupa_password",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        username =findViewById(R.id.username);


lanjut.setEnabled(true);
        lanjut = findViewById(R.id.lanjut);
        lanjut.setOnClickListener(view -> {
            lanjut.setEnabled(false);

            APIRequestData apiRequestData = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<ResponPassword1> call = apiRequestData.lupa_password1(username.getText().toString());

            call.enqueue(new Callback<ResponPassword1>() {
                @Override
                public void onResponse(Call<ResponPassword1> call, Response<ResponPassword1> response) {

                    if(response.body().kode == 0) {
                        Toast.makeText(lupa_password.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                    } else if (response.body().kode == 1){

                        editor.putString("username",username.getText().toString());
                        editor.apply();

                        Intent buka = new Intent(lupa_password.this, lupa_password_2.class);
                        startActivity(buka);
                    } else if (response.body().kode ==2) {
                        Toast.makeText(lupa_password.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                        enableButton(lanjut);
                    }
                }

                @Override
                public void onFailure(Call<ResponPassword1> call, Throwable t) {
                    Toast.makeText(lupa_password.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    enableButton(lanjut);
                }
            });
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
    private void enableButton(Button button){
        button.setEnabled(true);
    }
}