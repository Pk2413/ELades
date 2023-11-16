package com.ELayang.Desa.Menu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.viewmodel.CreationExtras;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.ELayang.Desa.Login.login;
import com.ELayang.Desa.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.auth.api.Auth;
import com.google.firebase.auth.FirebaseAuth;


import java.util.concurrent.Executor;

public class akun extends Fragment implements GoogleApiClient.OnConnectionFailedListener {

    private GoogleSignInClient mGoogleSignInClient;
    private GoogleApiClient mGoogleApiClient;
    FirebaseAuth firebaseAuth ;
ImageButton buka;
Button keluar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_akun, container, false);

//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();
//
//        mGoogleApiClient = new GoogleApiClient.Builder(requireContext())
//                .enableAutoManage(requireActivity(), this)
//                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
//                .build();


        keluar = view.findViewById(R.id.logout);
        keluar.setOnClickListener(v -> {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            // Hapus data dari SharedPreferences
            editor.putString("username", null);
            editor.putString("password", null);
            editor.putString("email", null);
            editor.apply();

            signOut();

            getActivity().finish();

        });

        buka = view.findViewById(R.id.buka);
        buka.setOnClickListener(v->{

            Intent buka = new Intent(getContext(), ganti_password.class );
            startActivity(buka);
        });







        // Inflate the layout for this fragment
        return view;
    }

    private void signOut() {
        if (getActivity() instanceof login) {
            ((login) getActivity()).signOut();
//            mGoogleSignInClient.signOut();

//            ((login) getActivity()).signOutAndClearCache();
        }
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e("GoogleSignIn", "Connection failed: " + connectionResult);
    }

    @NonNull
    @Override
    public CreationExtras getDefaultViewModelCreationExtras() {
        return super.getDefaultViewModelCreationExtras();
    }
}