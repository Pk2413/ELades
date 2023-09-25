package com.ELayang.Desa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.ELayang.Desa.Menu.Notifikasi;
import com.ELayang.Desa.Menu.akun;
import com.ELayang.Desa.Menu.dashboard;
import com.ELayang.Desa.Menu.permintaan_surat;
import com.ELayang.Desa.Menu.riwayat_surat;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

public class menu extends AppCompatActivity {
    private FirebaseAuth mAuth;
    ImageButton dasboard, notifikasi;
    BottomNavigationView bottomNavigationView;
    FloatingActionButton fab;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mAuth = FirebaseAuth.getInstance();
        GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API)
                .build();

        bottomNavigationView = findViewById(R.id.bottomNavView);

// Untuk menghilangkan shadow, kita dapat menggunakan method setElevation dengan nilai 0dp
        bottomNavigationView.setElevation(0);
        bottomNavigationView.getMenu().findItem(R.id.permintaan).setEnabled(false);

        bottomNavigationView = findViewById(R.id.bottomNavView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                if (item.getItemId() == R.id.dashboard) {
                    selectedFragment = new dashboard();
                }else if (item.getItemId() == R.id.notifikasi) {
                    selectedFragment = new Notifikasi();
                }else if (item.getItemId() == R.id.riwayat) {
                    selectedFragment = new riwayat_surat();
                }else if (item.getItemId() == R.id.profil) {
                    selectedFragment = new akun();
                }

                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame, selectedFragment)
                            .commit();
                }

                return true;
            }
        });
        // Set the initial fragment to display
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame, new dashboard())
                .commit();
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(v ->{
            Intent buka = new Intent(this, permintaan_surat.class);
            startActivity(buka);
                });
            }




    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
            if(keyCode == KeyEvent.KEYCODE_BACK){
                signOut();
//                finish();
                return false;
            }
        return super.onKeyDown(keyCode, event);
    }
    private void signOut() {
        // Lakukan logout dari Firebase
        mAuth.signOut();

        // Lakukan logout dari Google Sign-In (jika digunakan)
        GoogleSignIn.getClient(this, GoogleSignInOptions.DEFAULT_SIGN_IN).signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // Logout dari Google berhasil (jika ada)
                        finish(); // Keluar dari aktivitas setelah logout
                    }
                });
    }
}