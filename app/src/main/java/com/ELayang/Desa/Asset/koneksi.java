package com.ELayang.Desa.Asset;

import android.widget.Toast;

import com.google.android.material.internal.ContextUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//public class koneksi {


    public class koneksi {
        Connection connection = null;
        String url = "jdbc:mysql://192.168.137.22:3306/elades";
        String username = "root";
        String password ="" ;
        private static java.sql.Connection koneksi;
        private static java.sql.Statement stm;

        public static java.sql.Connection getKoneksi() throws ClassNotFoundException{
//            Class.forName("com.mysql.jdbc.Driver");
            if ( koneksi == null){
                try {
                    String url = "jdbc:mysql://localhost:3306/lama_umkm";

                    String user = "root";
                    String password = "";

                    koneksi = DriverManager.getConnection(url, user, password);
                    stm = koneksi.createStatement();
                    System.out.println("koneksi berhasil");
                } catch(SQLException t){
//                    Toast.makeText(null, "error" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            return koneksi;
        }
    }

//}
