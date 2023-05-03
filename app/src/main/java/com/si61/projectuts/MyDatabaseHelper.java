package com.si61.projectuts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.security.PublicKey;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context ctx;
    private static final String DATABASE_NAME = "db_pasar_abcd";
    private static final int  DATABASE_VERSION =1;
    private static final String TABLE_NAME="tbl_pasar_abcd";
    private static final String FIELD_ID="id";
    private static final String FIELD_NAMA="nama";
    private static final String FIELD_KOTA="kota";
    private static final String FIELD_ALAMAT="alamat";


    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*
        CREATE TABLE tbl_destinasi(
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            nama VARCHAR(50),
            alamat TEXT,
            jam VARCHAR(30)
         );
         */

        String query = "CREATE TABLE " + TABLE_NAME + "("+
                FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FIELD_NAMA + " VARCHAR(50), " +
                FIELD_KOTA + " VARCHAR(30), " +
                FIELD_ALAMAT + "TEXT " +
                ");"
                ;
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion /* ( Parameter )*/) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        onCreate(db);
    }

    public long tambahData(String nama, String kota, String alamat){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(FIELD_NAMA, nama);
        cv.put(FIELD_KOTA, kota);
        cv.put(FIELD_ALAMAT, alamat);

        long eksekusi = db.insert(TABLE_NAME, null, cv);
        return eksekusi;
    }

    public Cursor bacaDataDestinasi(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " +TABLE_NAME;

        Cursor varCursor = null;
        if(db!=null){
            varCursor = db.rawQuery(query, null);
        }
        return varCursor;
    }

    public long ubahData(String id, String nama, String kota, String alamat){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(FIELD_NAMA, nama);
        cv.put(FIELD_KOTA, kota);
        cv.put(FIELD_ALAMAT, alamat);

        long eksekusi = db.update(TABLE_NAME, cv, "id = ?", new String[]{id});
        return eksekusi;
    }

    public long hapusData(String id){
        SQLiteDatabase db = this.getWritableDatabase();


        long eksekusi = db.delete(TABLE_NAME, "id = ?", new String[]{id});
        return eksekusi;
    }

}