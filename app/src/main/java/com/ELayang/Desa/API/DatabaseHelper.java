//package com.ELayang.Desa.API;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.DatabaseErrorHandler;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.os.Build;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.annotation.RequiresApi;
//
//import com.ELayang.Desa.DataModel.ModelKolom;
//import com.ELayang.Desa.DataModel.ModelSurat;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class DatabaseHelper extends SQLiteOpenHelper {
//    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }
//
//    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
//        super(context, name, factory, version, errorHandler);
//    }
//
//    public DatabaseHelper(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
//        super(context, name, version, openParams);
//    }
//    // Implementasi helper untuk membaca data dari database
//
//    public List<ModelKolom> getDataByKodeSurat(String kodeSurat) {
//        List<ModelKolom> kolomList = new ArrayList<>();
//
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery("SELECT * FROM nama_tabel WHERE kode_surat = ?", new String[]{kodeSurat});
//
//        if (cursor.moveToFirst()) {
//            do {
//                ModelKolom kolom = new ModelKolom();
//                kolom.setNamaKolom(cursor.getString(cursor.getColumnIndex("nama_kolom")));
//                kolom.setNilaiKolom(cursor.getString(cursor.getColumnIndex("nilai_kolom")));
//
//                kolomList.add(kolom);
//            } while (cursor.moveToNext());
//        }
//
//        cursor.close();
//        db.close();
//
//        return kolomList;
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//    }
//}
//
