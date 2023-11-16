package com.ELayang.Desa.Menu;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ELayang.Desa.API.APIRequestData;
import com.ELayang.Desa.API.RetroServer;
import com.ELayang.Desa.Asset.RiwayatSurat.SuratDiajukan;
import com.ELayang.Desa.Asset.RiwayatSurat.SuratSelesai;
import com.ELayang.Desa.DataModel.RiwayatSurat.ModelDiajukan;
import com.ELayang.Desa.DataModel.RiwayatSurat.ModelSelesai;
import com.ELayang.Desa.DataModel.RiwayatSurat.ResponDiajukan;
import com.ELayang.Desa.DataModel.RiwayatSurat.ResponSelesai;
import com.ELayang.Desa.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class riwayat_surat_suratSelesai extends Fragment {

    private View view;

    ArrayList<ModelSelesai> data = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_riwayat_surat_surat_selesai, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.view_selesai);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        APIRequestData apiRequestData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponSelesai> call = apiRequestData.selesai(username);

        call.enqueue(new Callback<ResponSelesai>() {
            @Override
            public void onResponse(Call<ResponSelesai> call, Response<ResponSelesai> response) {
                ResponSelesai responSelesai = response.body();
                if(responSelesai !=null && responSelesai.getKode() ==1) {
                    ArrayList<ModelSelesai> list = (ArrayList<ModelSelesai>) responSelesai.getData();

                    if (list != null && !list.isEmpty()) {
                        // Tambahkan data surat ke ArrayList dan set up RecyclerView
                        ModelSelesai user = response.body().getData().get(0);
                        data.addAll(list);

                        SuratSelesai adapter = new SuratSelesai(data);
                        recyclerView.setAdapter(adapter);
                    } else {
                        // Handle ketika data surat kosong
                        Toast.makeText(getContext(), responSelesai.getPesan(), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getContext(), responSelesai.getPesan(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponSelesai> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}