package com.ELayang.Desa.Login;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

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
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.*;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
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
    GoogleApiClient googleApiClient;
    private FirebaseAuth firebaseAuth;
    private GoogleSignInClient mGoogleSignInClient;
    EditText username, password;
    Button masuk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        SharedPreferences sharedPreferences = getSharedPreferences("prefLogin", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


//        mAuth = FirebaseAuth.getInstance();

        EditText username = findViewById(R.id.username), password = findViewById(R.id.password);

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
        });
        firebaseAuth = FirebaseAuth.getInstance();

        // Inisialisasi klien GoogleSignInClient
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();


        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        SignInButton btnGoogleSignIn = findViewById(R.id.login_google);
        btnGoogleSignIn.setOnClickListener(v -> signInWithGoogle());

    }

    public void bregister(View view) {
        Intent buka = new Intent(this, register1.class);
        startActivity(buka);
    }

    public void blupapassword(View view) {
        Intent buka = new Intent(this, lupa_password.class);
        startActivity(buka);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }


    //firebase baru
    public void signInWithGoogle() {
        // Memulai proses login dengan Google dan memilih akun
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Handle hasil dari aktivitas login dengan Google
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleGoogleSignInResult(task);
        }
    }

    private void handleGoogleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            firebaseAuthWithGoogle(account);
        } catch (ApiException e) {
            Log.w(TAG, "Google sign in failed", e);
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                // Sign in success, update UI with the signed-in user's information
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User signed in, check against your local database
                    String email = user.getEmail();
                    APIRequestData apiRequestData = RetroServer.konekRetrofit().create(APIRequestData.class);
                    Call<ResponLogin> call = apiRequestData.logingoogle(email);

                    call.enqueue(new Callback<ResponLogin>() {
                        @Override
                        public void onResponse(Call<ResponLogin> call, Response<ResponLogin> response) {
                            if (response.body().kode == 1) {
                                SharedPreferences sharedPreferences = getSharedPreferences("prefLogin", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                ModelLogin user = response.body().getData().get(0);
                                editor.putString("username", user.getUsername());
                                editor.putString("email", user.getEmail());
                                editor.putString("nama", user.getNama());
                                editor.apply();
                                Intent pindah = new Intent(login.this, menu.class);
                                revokeAccess();
                                startActivity(pindah);
                                finish(); // Pastikan untuk menutup activity saat ini setelah pindah ke menu.class
                            } else {
                                // Mendeklarasikan Intent
                                Intent intent = new Intent(login.this, register1.class);
                                intent.putExtra("email", user.getEmail());
                                intent.putExtra("nama", user.getDisplayName());
                                startActivity(intent);
//                                finish(); // Pastikan untuk menutup activity saat ini setelah pindah ke register1.class
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponLogin> call, Throwable t) {
                            Log.e("error sign in google:", t.getMessage());
                        }
                    });
                }
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "signInWithCredential:failure", task.getException());
            }
            signOut();
        });
    }
    private void revokeAccess() {
        mGoogleSignInClient.revokeAccess();
    }

    public void signOut() {
        mGoogleSignInClient.signOut();
        revokeAccess();
    }
}