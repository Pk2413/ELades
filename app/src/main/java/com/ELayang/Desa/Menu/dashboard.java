package com.ELayang.Desa.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ELayang.Desa.API.APIRequestData;
import com.ELayang.Desa.API.RetroServer;
import com.ELayang.Desa.Asset.Notifikasi.NotificationService;
import com.ELayang.Desa.Asset.imagePagerAdapter;
import com.ELayang.Desa.DataModel.StatusDasboardModel;
import com.ELayang.Desa.DataModel.StatusDasboardRespon;
import com.ELayang.Desa.R;
import com.ELayang.Desa.menu;

import org.w3c.dom.Text;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class dashboard extends Fragment {

    private String nama;
    private String KEY_NAME = "NAMA";
    private String username;
    TextView selesai, proses, tolak, masuk;
    imagePagerAdapter adapter;
    private Handler autoScrollHandler;
    private Runnable autoScrollRunnable;
    private static final int AUTO_SCROLL_DELAY = 4000;
    private boolean isUserScrolling = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_dashboard, container, false);



        selesai = rootView.findViewById(R.id.surat_selesai);
        tolak = rootView.findViewById(R.id.surat_ditolak);
        masuk = rootView.findViewById(R.id.surat_masuk);


        //bundle get
        Bundle bundle = getActivity().getIntent().getExtras();


        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        String nama = sharedPreferences.getString("nama", "");
        TextView hello = rootView.findViewById(R.id.hello);
        hello.setText("Halo, " + nama);

        ViewPager viewPager = rootView.findViewById(R.id.viewPager);
        adapter = new imagePagerAdapter(getContext());
        viewPager.setAdapter(adapter);

        // Set indeks awal ke nilai tengah untuk tampilan awal yang baik
        int middle = adapter.getCount() / 2;
        viewPager.setCurrentItem(middle, true);

        autoScrollHandler = new Handler(Looper.getMainLooper());
        autoScrollRunnable = new Runnable() {
            @Override
            public void run() {
                int currentItem = viewPager.getCurrentItem();
                viewPager.setCurrentItem(currentItem + 1, true);
                autoScrollHandler.postDelayed(this, AUTO_SCROLL_DELAY);
            }
        };

        // Tambahkan listener untuk mendeteksi perubahan halaman manual
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Tidak perlu implementasi pada saat ini
            }

            @Override
            public void onPageSelected(int position) {
                // Pengguna menggeser manual, hentikan otomatis bergulir
                stopAutoScroll();
                isUserScrolling = true;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // Jika pengguna telah selesai menggeser, mulai kembali otomatis bergulir
                if (state == ViewPager.SCROLL_STATE_IDLE && isUserScrolling) {
                    startAutoScroll();
                    isUserScrolling = false;
                }
            }
        });



        //status
        APIRequestData apiRequestData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<StatusDasboardRespon> call = apiRequestData.dashboard(username);
        call.enqueue(new Callback<StatusDasboardRespon>() {
            @Override
            public void onResponse(Call<StatusDasboardRespon> call, Response<StatusDasboardRespon> response) {
                if (response.body().isKode() == true) {
                    StatusDasboardModel model = response.body().getData().get(0);
                    if( model.getSelesai() != null) {
                        selesai.setText(model.getSelesai());
                    }else{
                        selesai.setText("0");
                    }
                    if(model.getTolak() != null){
                        tolak.setText(model.getTolak());
                    }else{
                        tolak.setText("0");
                    }
                    if(model.getMasuk() != null) {
                        masuk.setText(model.getMasuk());
                    }else{
                        masuk.setText("0");
                    }

                } else {
                    selesai.setText("0");
                    tolak.setText("0");
                    masuk.setText("0");
                }
            }

            @Override
            public void onFailure(Call<StatusDasboardRespon> call, Throwable t) {
                Log.e("error dashboard", t.getMessage());
                selesai.setText("0");
                tolak.setText("0");
                masuk.setText("0");
            }
        });
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Mulai otomatis bergulir ketika aktivitas di-resume
        startAutoScroll();
    }


    @Override
    public void onPause() {
        super.onPause();
        // Hentikan otomatis bergulir ketika aktivitas di-pause
        stopAutoScroll();
    }

    private void startAutoScroll() {
        autoScrollHandler.postDelayed(autoScrollRunnable, AUTO_SCROLL_DELAY);
    }

    private void stopAutoScroll() {
        autoScrollHandler.removeCallbacks(autoScrollRunnable);
    }


}