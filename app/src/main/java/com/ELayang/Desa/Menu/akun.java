package com.ELayang.Desa.Menu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.ELayang.Desa.API.APIRequestData;
import com.ELayang.Desa.API.RetroServer;
import com.ELayang.Desa.DataModel.ModelResponse;
import com.ELayang.Desa.DataModel.ModelUsers;
import com.ELayang.Desa.Login.login;
import com.ELayang.Desa.R;
import com.ELayang.Desa.menu;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class akun extends Fragment {
ImageButton buka;
Button keluar;
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
            editor.apply();
            Intent buka = new Intent(getActivity(), login.class);
            startActivity(buka);
        });




        // Inflate the layout for this fragment
        return view;
    }


}