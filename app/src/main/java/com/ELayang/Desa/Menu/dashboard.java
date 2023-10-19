package com.ELayang.Desa.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ELayang.Desa.R;

import java.util.Objects;

public class dashboard extends Fragment {

    private String nama;
    private String KEY_NAME = "NAMA";
    private String username;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_dashboard,container, false);


        //bundle get
        Bundle bundle = getActivity().getIntent().getExtras();


        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("prefLogin",Context.MODE_PRIVATE);
        String nama = sharedPreferences.getString("nama","");
        TextView hello = rootView.findViewById(R.id.hello);
        hello.setText("Halo, "+ nama);





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