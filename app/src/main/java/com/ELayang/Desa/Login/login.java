package com.ELayang.Desa.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ELayang.Desa.API.APIRequestData;
import com.ELayang.Desa.API.RetroServer;
import com.ELayang.Desa.Asset.koneksi;
import com.ELayang.Desa.DataModel.ModelResponse;
import com.ELayang.Desa.DataModel.ModelUsers;
import com.ELayang.Desa.Menu.dashboard;
import com.ELayang.Desa.R;
import com.ELayang.Desa.menu;
import com.google.android.gms.auth.api.signin.*;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login extends AppCompatActivity {
    private static final int RC_SIGN_IN = 6969;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    EditText username, password;
    Button masuk ;
    
    private String KEY_NAME = "NAMA";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        SharedPreferences sharedPreferences = getSharedPreferences("prefLogin",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        mAuth = FirebaseAuth.getInstance();

        EditText username = findViewById(R.id.username),
                password = findViewById(R.id.password);

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
            } else if(password.getText().toString().isEmpty()){
                password.setError("Password Harus Diisi");
                password.requestFocus();
            } else{
                APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
                Call<ModelResponse> getLoginResponse = ardData.login(username.getText().toString(), password.getText().toString());
                getLoginResponse.enqueue(new Callback<ModelResponse>() {
                    @Override
                    public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {

                        if (response.body().kode == 1) {

                            // Simpan semua data pengguna ke SharedPreferences
                            ModelUsers user = response.body().getData().get(0);
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

                        } else if (response.body().kode == 0) {
                            username.requestFocus();
                            Toast.makeText(login.this, "Akun Belum Terdaftar", Toast.LENGTH_SHORT).show();
                        } else if (response.body().kode == 2) {
                            password.requestFocus();
                            Toast.makeText(login.this, "Password Salah!!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ModelResponse> call, Throwable t) {
                        Toast.makeText(login.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(login.this, "Error Disini", Toast.LENGTH_SHORT).show();
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



//        register = findViewById(R.id.register);
//        register.setOnClickListener(view -> {
//            Intent buka = new Intent(this, register1.class);
//            startActivity(buka);
//        });

//        lupaPasssword = findViewById(R.id.lupapassword);
//        lupaPasssword.setOnClickListener(view -> {
//            Intent buka = new Intent(this, lupa_password.class);
//            startActivity(buka);
//        });

        // firebase
        SignInButton login;
        login = findViewById(R.id.login_google);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("763335909373-t6ek18so6dqdm9a37mbu4n83vmuqd3vn.apps.googleusercontent.com")
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);

        login.setOnClickListener(view ->{
            googleSignIn();
        });

        if (mAuth.getCurrentUser() != null){
            Intent buka = new Intent(login.this, menu.class);
            startActivity(buka);
            finish();
        }
    }
    public void bregister(View view){
        Intent buka = new Intent(this, register1.class);
        startActivity(buka);
    }
    public void blupapassword(View view){
        Intent buka = new Intent(this, lupa_password.class);
        startActivity(buka);
    }
    //fungsi farebase
    private void googleSignIn(){
        Intent buka = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(buka, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try{
                GoogleSignInAccount account = task.getResult();
                firebaseAsuth(account.getIdToken());
            }catch(Exception e){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAsuth(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken,null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();

//                            HashMap<String,Object> map = new HashMap<>();
//                            map.put("id",user.getUid());
//                            map.put("nama", user.getDisplayName());
//                            map.put("profile",user.getPhotoUrl());
//
//                            database.getReference().child("users".child(User,getUid()).setValue(map));
                            Toast.makeText(login.this, "SELAMAT DATANG " + user.getDisplayName(), Toast.LENGTH_SHORT).show();
                            Intent buka;
                            buka = new Intent(login.this, menu.class);
                            startActivity(buka);
                        }else {
                            Toast.makeText(login.this, "GAGAL LOGIN", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void cekLogin ( String username, String password){

        String cekuser = null;
        String cekpw = null;

//        try {
//            Connection Connect =  koneksi.getKoneksi();
//            Statement stm = Connect.createStatement();
//            String sql = "SELECT * FROM akun_user Where username ='"+username+"' AND password='"+password+"'";
//            ResultSet rs = stm.executeQuery(sql);
//            while(rs.next()){
//                cekuser = rs.getString("username");
//                cekpw = rs.getString("password");
//
//            }
//            rs.close();
//            stm.close();
//        }catch (SQLException e) {
//            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
//
//        }
//        if(cekuser == null && cekpw == null){
//
//            String pesan = "username atau password salah";
//            Toast.makeText(this, pesan, Toast.LENGTH_SHORT).show();
////            JOptionPane.showMessageDialog(null, pesan);
//        }else{
//            String pesan1 = "login Berhasil";
//            Toast.makeText(this, pesan1, Toast.LENGTH_SHORT).show();
//            Intent buka = new Intent(this, menu.class);
//
//            startActivity(buka);
//        }
    }



}
