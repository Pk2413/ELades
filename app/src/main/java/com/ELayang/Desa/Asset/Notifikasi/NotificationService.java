package com.ELayang.Desa.Asset.Notifikasi;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.JobIntentService;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.ELayang.Desa.API.APIRequestData;
import com.ELayang.Desa.API.RetroServer;
import com.ELayang.Desa.DataModel.Notifikasi.ResponNotifikasi;
import com.ELayang.Desa.Menu.Notifikasi;
import com.ELayang.Desa.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationService extends JobIntentService {

    private static final int NOTIFICATION_ID = 1;
    private static final int DELAY_INTERVAL = 30000; //ms
    private final Handler handler = new Handler(Looper.getMainLooper());
    private String lastKnownStateDitolak = "";
    private String ALasan = "";

    public static void enqueueWork(Context context, Intent work) {
        enqueueWork(context, NotificationService.class, 111, work);
    }

    @Override
    protected void onHandleWork(Intent intent) {
        monitorChangesAndNotify();
    }

    private void monitorChangesAndNotify() {
        checkForUpdatesDitolak();
        scheduleNextRun();
    }

    private void scheduleNextRun() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                monitorChangesAndNotify();
            }
        }, DELAY_INTERVAL);
    }

    private void checkForUpdatesDitolak() {
        SharedPreferences sharedPreferences = getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponNotifikasi> getNotifRespon = ardData.notif(username);
        getNotifRespon.enqueue(new Callback<ResponNotifikasi>() {
            @Override
            public void onResponse(Call<ResponNotifikasi> call, Response<ResponNotifikasi> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getKode() == 1) {
                        String currentState = response.body().getPesan();
                        ALasan = response.body().getAlasan();
                        if (!currentState.equals(lastKnownStateDitolak)) {
                            showNotificationDitolak();
                        }
                        lastKnownStateDitolak = currentState;
                    } else if (response.body().getKode() == 0) {
                        // Handle other cases if needed
                    } else {
                        // Handle other cases if needed
                    }
                } else {
                    // Handle unsuccessful response
                }
            }

            @Override
            public void onFailure(Call<ResponNotifikasi> call, Throwable t) {
                Log.e("NotificationService", "onFailure: " + t.getMessage());
            }
        });
    }

    private void showNotificationDitolak() {
        if (isNotificationPermissionGranted()) {
            try{
            Intent intent = new Intent(NotificationService.this, Notifikasi.class)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(NotificationService.this, "prayoga")
                    .setSmallIcon(R.drawable.logo)
                    .setContentTitle("E-lades")
                    .setContentText("Mohon Maaf, Pengajuan Anda Tidak Diterima\nKarena " + ALasan + " ")
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(NotificationService.this);
            if (ActivityCompat.checkSelfPermission(NotificationService.this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Handle the case where permission is not granted
                Log.e("NotificationService", "Permission not granted");
                return;
            }
            notificationManager.notify(NOTIFICATION_ID, builder.build());
        } catch (SecurityException e) {
            // Handle SecurityException
            Log.e("NotificationService", "SecurityException: " + e.getMessage());
        }
        } else {
            Log.e("NotificationService", "Izin notifikasi tidak diberikan oleh pengguna.");
        }
    }

    private boolean isNotificationPermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS)
                    == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }

    private void requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                        .putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
                startActivity(intent);
            }
        }
    }
}
