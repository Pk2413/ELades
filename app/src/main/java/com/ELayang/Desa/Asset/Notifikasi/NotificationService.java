package com.ELayang.Desa.Asset.Notifikasi;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
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

import androidx.core.app.JobIntentService;
import androidx.core.app.NotificationCompat;

import com.ELayang.Desa.API.APIRequestData;
import com.ELayang.Desa.API.RetroServer;
import com.ELayang.Desa.DataModel.Notifikasi.ResponNotifikasi;
import com.ELayang.Desa.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationService extends JobIntentService {

    private static int notificationId = 0;

    private static final String CHANNEL_ID = "MyChannelID";
    private static final int DELAY_INTERVAL = 60000; //ms
    private final Handler handler = new Handler(Looper.getMainLooper());
    private String lastTanggal = "", lastJam = "";
    private String lastalasan = "", kode_surat ="";

    public static void enqueueWork(Context context, Intent work) {
        enqueueWork(context, NotificationService.class, 111, work);
    }

    @Override
    protected void onHandleWork(Intent intent) {
        monitorChangesAndNotify();
        createNotificationChannel();
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

    private void createNotificationChannel() {
        // Cek versi Android, karena pembuatan channel diperlukan pada Android Oreo (API level 26) ke atas
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "MyChannelName";
            String description = "MyChannelDescription";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


    private void checkForUpdatesDitolak() {
        SharedPreferences sharedPreferences = getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponNotifikasi> getNotifRespon = ardData.notifikasi_popup(username);
        getNotifRespon.enqueue(new Callback<ResponNotifikasi>() {
            @Override
            public void onResponse(Call<ResponNotifikasi> call, Response<ResponNotifikasi> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getKode() == 1) {
                        String tanggal = response.body().getTanggal();
                        String jam = response.body().getJam();
                        String alasan = response.body().getAlasan();
                        String status = response.body().getStatus();
                        if (!tanggal.equals(lastTanggal) || !jam.equals(lastJam) || !alasan.equals(lastalasan)) {
                            lastTanggal = tanggal;
                            lastJam = jam;
                            lastalasan = alasan;
                           if(status.equals("Tolak")){
                               showNotificationDitolak();
                           } else if (status.equals("Selesai")) {
                                showNotifikasiSelesai();
                           }
                        }else{

                        }

                    } else if (response.body().getKode() == 0) {

                    } else {

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
            Intent intent = new Intent(this, NotificationService.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.logo)
                    .setContentTitle("Surat Yang Anda Ajukan Ditolak")
                    .setContentText("Alasan: " + lastalasan) // Gunakan alasan dari response
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(notificationId++, builder.build());
        } else {
            Log.e("NotificationService", "Izin notifikasi tidak diberikan oleh pengguna.");
        }
    }
    private void showNotifikasiSelesai() {
        if (isNotificationPermissionGranted()) {
            Intent intent = new Intent(this, NotificationService.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.logo)
                    .setContentTitle("Surat Yang Anda Ajukan Selesai Diproses")
                    .setContentText(lastalasan) // Gunakan alasan dari response
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(notificationId++, builder.build());
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
