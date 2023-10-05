package com.ELayang.Desa.Menu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ELayang.Desa.R;

public class riwayat_surat extends Fragment {

    Button diajukan, selesai;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_riwayat_surat, container, false);
        diajukan = rootView.findViewById(R.id.diajukan);
        diajukan.setOnClickListener(v ->{
            loadFragment(new riwayat_surat_suratDiajukan());
            diajukan.setEnabled(false);
            selesai.setEnabled(true);
        });

        selesai = rootView.findViewById(R.id.selesai);
        selesai.setOnClickListener(v ->{
            loadFragment(new riwayat_surat_suratSelesai());
            diajukan.setEnabled(true);
            selesai.setEnabled(false);
        });

    diajukan.performClick();

        // Inflate the layout for this fragment
        return rootView;
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frame_riwayat, fragment);
        fragmentTransaction.commit();
    }
}