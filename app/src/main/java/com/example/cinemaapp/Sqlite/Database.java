package com.example.cinemaapp.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.Struct;

public class Database  extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void QueryData(String sql)// truy vấn ko trả về kết quả (CREATE,INSERT,UPDATE,DELETE)
    {
        SQLiteDatabase database= getWritableDatabase(); // ghi dữ liệu không trả về kết qủa
        database.execSQL(sql);

    }
    public Cursor getData(String sql)
    {
        SQLiteDatabase database=getReadableDatabase();
        return database.rawQuery(sql,null);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
