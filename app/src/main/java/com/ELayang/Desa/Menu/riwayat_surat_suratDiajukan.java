package com.ELayang.Desa.Menu;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ELayang.Desa.API.APIRequestData;
import com.ELayang.Desa.API.RetroServer;
import com.ELayang.Desa.Asset.RiwayatSurat.SuratDiajukan;
import com.ELayang.Desa.DataModel.RiwayatSurat.ModelDiajukan;
import com.ELayang.Desa.DataModel.RiwayatSurat.ResponDiajukan;
import com.ELayang.Desa.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class riwayat_surat_suratDiajukan extends Fragment {


    private View view;
    private ArrayList<ModelDiajukan> data = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_riwayat_surat_surat_diajukan, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.D_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        APIRequestData apiRequestData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponDiajukan> call = apiRequestData.proses(username);

        call.enqueue(new Callback<ResponDiajukan>() {
            @Override
            public void onResponse(Call<ResponDiajukan> call, Response<ResponDiajukan> response) {
                ResponDiajukan responDiajukan = response.body();
                if (responDiajukan != null && responDiajukan.getKode() == 1) {
                    ArrayList<ModelDiajukan> list = (ArrayList<ModelDiajukan>) responDiajukan.getData();

                    if (list != null && !list.isEmpty()) {
                        // Tambahkan data surat ke ArrayList dan set up RecyclerView
                        ModelDiajukan user = response.body().getData().get(0);
                        data.addAll(list);
                        SuratDiajukan adapter = new SuratDiajukan(data);
                        recyclerView.setAdapter(adapter);
                    } else {
                        // Handle ketika data surat kosong
                        Toast.makeText(getContext(), responDiajukan.getPesan(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), responDiajukan.getPesan(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponDiajukan> call, Throwable t) {
                Log.e("error pada suratDiajukan",t.getMessage());
            }
        });
        return view;
    }

}