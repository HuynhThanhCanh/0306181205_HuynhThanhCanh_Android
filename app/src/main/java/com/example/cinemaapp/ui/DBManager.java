package com.example.cinemaapp.ui;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.cinemaapp.model.ThanhVien;

public class DBManager extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="cinema";
    private static final String TABLE_NAME ="thanhvien";
    private static final String ID ="id";
    private static final String hoten ="hoten";
    private static final String email ="email";
    private static final String sdt ="sdt";
    private static final String diachi ="diachi";
    private static final String matkhau ="matkhau";
    private static final String ngaysinh ="ngaysinh";
    private static final String gioitinh ="gioitinh";

    private Context context;
    private  String SQLQuery = " CREATE TABLE "+TABLE_NAME+" ("+
            ID +" integer primary key, "+
            hoten + " TEXT, "+
            sdt + " TEXT, "+
            email+ " TEXT, "+
            matkhau + " TEXT, "+
            gioitinh + " TEXT, "+
            ngaysinh+ " TEXT, "+
            diachi + " TEXT)";



    public DBManager( Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase SQLiteDatabase) {
        SQLiteDatabase.execSQL(SQLQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean themThanhVien(ThanhVien thanhVien){
        try{
            SQLiteDatabase db= this.getWritableDatabase();
            ContentValues values=  new ContentValues();
            values.put(hoten,thanhVien.getmHoten());
            values.put(sdt,thanhVien.getmSDT());
            values.put(email,thanhVien.getmEmail());
            values.put(matkhau,thanhVien.getmMatKhau());
            values.put(gioitinh,thanhVien.getmGioitinh());
            values.put(ngaysinh,thanhVien.getmNgaysinh());
            values.put(diachi,thanhVien.getmDiaChi());


            db.insert(TABLE_NAME,null,values);
            db.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
