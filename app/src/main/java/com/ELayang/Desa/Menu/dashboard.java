package com.ELayang.Desa.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ELayang.Desa.API.APIRequestData;
import com.ELayang.Desa.API.RetroServer;
import com.ELayang.Desa.Asset.imagePagerAdapter;
import com.ELayang.Desa.DataModel.StatusDasboardModel;
import com.ELayang.Desa.DataModel.StatusDasboardRespon;
import com.ELayang.Desa.R;

import org.w3c.dom.Text;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class dashboard extends Fragment {

    private String nama;
    private String KEY_NAME = "NAMA";
    private String username;
    TextView selesai, proses;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_dashboard,container, false);

        selesai = rootView.findViewById(R.id.surat_selesai);
        proses = rootView.findViewById(R.id.surat_diajukan);



        //bundle get
        Bundle bundle = getActivity().getIntent().getExtras();


        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("prefLogin",Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        String nama = sharedPreferences.getString("nama","");
        TextView hello = rootView.findViewById(R.id.hello);
        hello.setText("Halo, "+ nama);

        ViewPager viewPager = rootView.findViewById(R.id.viewPager);
        imagePagerAdapter adapter = new imagePagerAdapter(getContext());
        viewPager.setAdapter(adapter);

        // Set indeks awal ke nilai tengah untuk tampilan awal yang baik
        int middle = adapter.getCount() / 2;
        viewPager.setCurrentItem(middle, false);


        //status

        APIRequestData apiRequestData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<StatusDasboardRespon> call = apiRequestData.dashboard(username);
        call.enqueue(new Callback<StatusDasboardRespon>() {
            @Override
            public void onResponse(Call<StatusDasboardRespon> call, Response<StatusDasboardRespon> response) {
                if (response.body().isKode()){
                    StatusDasboardModel model = response.body().getData().get(0);

                    selesai.setText(model.getSelesai());
                    proses.setText(model.getProses());

                }
            }

            @Override
            public void onFailure(Call<StatusDasboardRespon> call, Throwable t) {
                Log.e("error dashboard", t.getMessage());
            }
        });






//         //Mengambil username dari SharedPreferences
//        SharedPreferences sharedPreferences;
//        sharedPreferences = requireActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
//        String username = sharedPreferences.getString("username", ""); // "" adalah nilai default jika tidak ditemukan
//        // Menampilkan username pada TextView
//        TextView usernameTextView = rootView.findViewById(R.id.hello); // Ganti dengan ID TextView Anda
//        usernameTextView.setText("Halo, "+username);


//        }
        // Inflate the layout for this fragment

        return rootView;
    }

//    public dashboard(){
//
//    }
//    public void setUsername(String username){
//        this.username = username;
//    }
}