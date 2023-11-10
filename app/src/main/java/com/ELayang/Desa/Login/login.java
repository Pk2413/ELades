package com.ELayang.Desa.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ELayang.Desa.API.APIRequestData;
import com.ELayang.Desa.API.RetroServer;
import com.ELayang.Desa.DataModel.Akun.ResponLogin;
import com.ELayang.Desa.DataModel.Akun.ModelLogin;
import com.ELayang.Desa.R;
import com.ELayang.Desa.menu;
import com.google.android.gms.auth.api.signin.*;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login extends AppCompatActivity {
    private static final int RC_SIGN_IN = 6969;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    EditText username, password;
    Button masuk;

    private String KEY_NAME = "NAMA";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        SharedPreferences sharedPreferences = getSharedPreferences("prefLogin", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        mAuth = FirebaseAuth.getInstance();

        EditText username = findViewById(R.id.username), password = findViewById(R.id.password);
        username.setText("user");
        password.setText("user");
        masuk = findViewById(R.id.masuk);
        masuk.setOnClickListener(view -> {


            String usernameText = username.getText().toString();
            String passwordText = password.getText().toString();


            username.setError(null);
            password.setError(null);
            String usernametext = username.getText().toString();
            if (TextUtils.isEmpty(usernametext)) {
                username.setError("Username Harus Diisi");
                username.requestFocus();
            } else if (password.getText().toString().isEmpty()) {
                password.setError("Password Harus Diisi");
                password.requestFocus();
            } else {
                APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
                Call<ResponLogin> getLoginResponse = ardData.login(username.getText().toString(), password.getText().toString());
                getLoginResponse.enqueue(new Callback<ResponLogin>() {
                    @Override
                    public void onResponse(Call<ResponLogin> call, Response<ResponLogin> response) {

                        if (response.body().kode == 1) {

                            // Simpan semua data pengguna ke SharedPreferences
                            ModelLogin user = response.body().getData().get(0);
                            editor.putString("username", user.getUsername());
                            editor.putString("password", user.getPassword());
                            editor.putString("email", user.getEmail());
                            editor.putString("nama", user.getNama());
                            editor.putString("kode_otp", user.getKode_otp());
                            editor.apply();
                            Intent pindah = new Intent(login.this, menu.class);
                            //Bundle send
//                            pindah.putExtra("username", username);
                            startActivity(pindah);
//                                    overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
                            finish();

                        } else if (response.body().kode == 0) {
                            username.requestFocus();
                            Toast.makeText(login.this, "Akun Belum Terdaftar", Toast.LENGTH_SHORT).show();
                        } else if (response.body().kode == 2) {
                            password.requestFocus();
                            Toast.makeText(login.this, "Password Salah!!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponLogin> call, Throwable t) {
                        Toast.makeText(login.this, "Login failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }


//
//
//                    if (usernameText.length() > 0) {
////                        Intent buka = new Intent(this, menu.class);
//                         cekLogin(usernameText, passwordText);


//                        username.setText("");
//                        password.setText("");
            //SharedPreferences send
//                        SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.putString("username", usernameText); // username adalah data yang telah diinputkan
//                        editor.apply();

//                        startActivity(buka);
//                    } else {
//                        Toast.makeText(getApplicationContext(), "username salah", Toast.LENGTH_SHORT).show();
//                        password.setText("");
//                    }

        });


        // firebase
        SignInButton login;
        login = findViewById(R.id.login_google);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("763335909373-t6ek18so6dqdm9a37mbu4n83vmuqd3vn.apps.googleusercontent.com")
                .requestEmail().build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        login.setOnClickListener(view -> {
            googleSignIn();
        });

//        if (mAuth.getCurrentUser() != null){
//            Intent buka = new Intent(login.this, menu.class);
//            startActivity(buka);
//            finish();
//        }

    }

    public void bregister(View view) {
        Intent buka = new Intent(this, register1.class);
        startActivity(buka);
    }

    public void blupapassword(View view) {
        Intent buka = new Intent(this, lupa_password.class);
        startActivity(buka);
    }

    //fungsi farebase
    private void googleSignIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                GoogleSignInAccount account = task.getResult();
                firebaseAsuth(account.getIdToken());
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAsuth(String idToken) {
        SharedPreferences sharedPreferences = getSharedPreferences("prefLogin", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


//                            ModelUsers user ;
//                            editor.putString("username", user.get());
//                            editor.putString("password", user.getPassword());
                    editor.putString("email", user.getEmail());
                    editor.putString("nama", user.getDisplayName());
//                            editor.putString("kode_otp", user.getKode_otp());
                    editor.apply();

                    HashMap<String, Object> map = new HashMap<>();
//                            map.put("id",user.getUid());
//                            map.put("nama", user.getDisplayName());
//                            map.put("profile",user.getPhotoUrl());
//
//                            database.getReference().child("users".child(User,getUid()).setValue(map));
                    Toast.makeText(login.this, "SELAMAT DATANG " + user.getDisplayName(), Toast.LENGTH_SHORT).show();
                    Intent buka;
                    buka = new Intent(login.this, menu.class);
                    startActivity(buka);
                } else {
                    Toast.makeText(login.this, "GAGAL LOGIN", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in successfully, get user details
            firebaseAsuth(account.getIdToken());
        } catch (ApiException e) {
            Toast.makeText(this, "Google sign in failed: " + e.getStatusCode(), Toast.LENGTH_SHORT).show();
            Log.e("GoogleSignIn", "Google sign in failed", e);
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }
}
