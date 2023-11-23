package com.ELayang.Desa.Menu;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ELayang.Desa.API.APIRequestData;
import com.ELayang.Desa.API.RetroServer;
import com.ELayang.Desa.Asset.Adapter.AdapterNotifikasi;
import com.ELayang.Desa.DataModel.Notifikasi.ModelNotifikasi;
import com.ELayang.Desa.DataModel.Notifikasi.ResponNotifikasi;
import com.ELayang.Desa.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Notifikasi extends Fragment {
    private ArrayList<ModelNotifikasi> data = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifikasi, container, false);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        RecyclerView recyclerView = view.findViewById(R.id.view);

        // Set the layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        APIRequestData apiRequestData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponNotifikasi> call = apiRequestData.notif(username);
        call.enqueue(new Callback<ResponNotifikasi>() {
            @Override
            public void onResponse(Call<ResponNotifikasi> call, Response<ResponNotifikasi> response) {
                if (response.body() != null && response.body().getKode() == 1) {
                    ArrayList<ModelNotifikasi> list = (ArrayList<ModelNotifikasi>) response.body().getData();
                    data.addAll(list);

                    // Initialize and set the adapter
                    AdapterNotifikasi recyclerViewAdapter = new AdapterNotifikasi(data);
                    recyclerView.setAdapter(recyclerViewAdapter);
                    recyclerViewAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ResponNotifikasi> call, Throwable t) {
                // Handle failure, e.g., show an error message
            }
        });

        return view;
    }
}