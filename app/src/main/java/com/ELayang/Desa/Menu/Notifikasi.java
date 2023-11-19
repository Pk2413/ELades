package com.ELayang.Desa.Menu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ELayang.Desa.Asset.Adapter.AdapterNotifikasi;
import com.ELayang.Desa.Asset.Adapter.ModelNotifikasi;
import com.ELayang.Desa.R;

import java.util.ArrayList;


public class Notifikasi extends Fragment {
    private ArrayList<ModelNotifikasi> settergetters;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        addData(15);
        View view = inflater.inflate(R.layout.fragment_notifikasi, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.view);
        AdapterNotifikasi recycleView = new AdapterNotifikasi(settergetters);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recycleView);
        // Contoh data
//        String[] data = {"Item 1", "Item 2", "Item 3", "Item 4"};


        // Menggunakan ArrayAdapter sebagai adapter bawaan


        return view;
    }

    private void addData(int x) {
        settergetters = new ArrayList<>();
        for(int i=0;i<x; i++) {
            settergetters.add(new ModelNotifikasi(String.valueOf(i), "kode_surat","69-69-6969","nyoba-nyoba dulu gk sih"));
        }

    }
}