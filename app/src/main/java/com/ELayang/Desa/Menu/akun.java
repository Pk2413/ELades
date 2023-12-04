package com.ELayang.Desa.Menu;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
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
    FirebaseAuth firebaseAuth;
    ImageButton buka;
    Button keluar;
    private static final String CHANNEL_ID = "MyChannelID";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_akun, container, false);

        keluar = view.findViewById(R.id.logout);
        keluar.setOnClickListener(v -> {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            // Hapus data dari SharedPreferences
            editor.putString("username", null);
            editor.putString("password", null);
            editor.putString("email", null);
            editor.apply();

            getActivity().finish();

        });

        buka = view.findViewById(R.id.buka);
        buka.setOnClickListener(v -> {

            Intent buka = new Intent(getContext(), ganti_password.class);
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
//
//    private void createNotificationChannel() {
//        // Cek versi Android, karena pembuatan channel diperlukan pada Android Oreo (API level 26) ke atas
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            CharSequence name = "MyChannelName";
//            String description = "MyChannelDescription";
//            int importance = NotificationManager.IMPORTANCE_DEFAULT;
//            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
//            channel.setDescription(description);
//            channel.enableLights(true);
//            channel.setLightColor(Color.RED);
//
//            NotificationManager notificationManager = getContext().getSystemService(NotificationManager.class);
//            notificationManager.createNotificationChannel(channel);
//        }
//    }
//
//    private void showNotification() {
//        Intent intent = new Intent(getActivity(), akun.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE);
//
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), CHANNEL_ID)
//                .setSmallIcon(R.drawable.logo)
//                .setContentTitle("Judul Notifikasi")
//                .setContentText("Isi notifikasi yang ingin ditampilkan.")
//                .setContentIntent(pendingIntent)
//                .setAutoCancel(true);
//
//        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify(1, builder.build());
//    }

}