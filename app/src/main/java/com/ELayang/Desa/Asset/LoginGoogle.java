//package com.ELayang.Desa.Asset;
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Toast;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.ELayang.Desa.API.APIRequestData;
//import com.ELayang.Desa.API.RetroServer;
//import com.ELayang.Desa.DataModel.Akun.ModelLogin;
//import com.ELayang.Desa.DataModel.Akun.ResponLogin;
//import com.ELayang.Desa.Login.login;
//import com.ELayang.Desa.R;
//import com.ELayang.Desa.menu;
//import com.google.android.gms.auth.api.signin.GoogleSignIn;
//import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
//import com.google.android.gms.auth.api.signin.GoogleSignInClient;
//import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
//import com.google.android.gms.common.SignInButton;
//import com.google.android.gms.common.api.ApiException;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.auth.AuthCredential;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.auth.GoogleAuthProvider;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//
//public class LoginGoogle extends AppCompatActivity {
//
//    private static final int RC_SIGN_IN = 123;
//    private static final String TAG = "LoginGoogle";
//
//    private FirebaseAuth firebaseAuth;
//    private GoogleSignInClient mGoogleSignInClient;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//
//        FirebaseApp.initializeApp(this);
//        firebaseAuth = FirebaseAuth.getInstance();
//
//        // Initialize mGoogleSignInClient
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();
//
//        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
//
//        SignInButton btnGoogleSignIn = findViewById(R.id.login_google);
//        btnGoogleSignIn.setOnClickListener(v -> signInWithGoogle());
//    }
//
//    public void signInWithGoogle() {
//        // Intent untuk memulai proses login dengan Google
//        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
//        startActivityForResult(signInIntent, RC_SIGN_IN);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // Handle hasil dari aktivitas login dengan Google
//        if (requestCode == RC_SIGN_IN) {
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            handleGoogleSignInResult(task);
//        }
//    }
//
//    private void handleGoogleSignInResult(Task<GoogleSignInAccount> completedTask) {
//        try {
//            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
//            firebaseAuthWithGoogle(account);
//        } catch (ApiException e) {
//            Log.w(TAG, "Google sign in failed", e);
//        }
//    }
//
//    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
//        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
//        firebaseAuth.signInWithCredential(credential)
//                .addOnCompleteListener(this, task -> {
//                    if (task.isSuccessful()) {
//                        // Sign in success, update UI with the signed-in user's information
//                        FirebaseUser user = firebaseAuth.getCurrentUser();
//                        if (user != null) {
//                            // User signed in, check against your local database
//                            loginWithGoogle(user.getEmail());
//                        }
//                    } else {
//                        // If sign in fails, display a message to the user.
//                        Log.w(TAG, "signInWithCredential:failure", task.getException());
//                    }
//                });
//    }
//
//    private void loginWithGoogle(String email) {
//        APIRequestData apiRequestData = RetroServer.konekRetrofit().create(APIRequestData.class);
//        Call<ResponLogin> call = apiRequestData.logingoogle(email);
//
//        call.enqueue(new Callback<ResponLogin>() {
//            @Override
//            public void onResponse(Call<ResponLogin> call, Response<ResponLogin> response) {
//                if(response.body().kode == 1){
//                    SharedPreferences sharedPreferences = getSharedPreferences("prefLogin", MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    ModelLogin user = response.body().getData().get(0);
//                    editor.putString("username", user.getUsername());
//                    editor.putString("password", user.getPassword());
//                    editor.putString("email", user.getEmail());
//                    editor.putString("nama", user.getNama());
//                    editor.putString("kode_otp", user.getKode_otp());
//                    editor.apply();
//                    Intent pindah = new Intent(LoginGoogle.this, menu.class);
//                    //Bundle send
////                            pindah.putExtra("username", username);
//                    startActivity(pindah);
////                                    overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
//                    finish();
//                }else {
//                    Toast.makeText(LoginGoogle.this, "kosong", Toast.LENGTH_SHORT).show();
//                    Log.e("error else", response.body().pesan);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponLogin> call, Throwable t) {
//                Log.e("error sign in google :", t.getMessage());
//            }
//        });
//    }
//}
