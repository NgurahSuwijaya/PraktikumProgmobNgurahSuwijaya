package com.example.praktikumprogmobngurahsuwijaya;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DataHelper extends SQLiteOpenHelper {
    Context Context;
    private static final String DATABASE_NAME = "db_praktikum";
    private static final int DATABASE_VERSION = 1;

    public DataHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlite = "create table tb_user (id integer primary key autoincrement not null, name text, password text, gender text,role text,range_play text);";
        Log.d("data", "onCreate:"+sqlite);
        sqLiteDatabase.execSQL(sqlite);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insertUser(String name_in, String password_in, String gender_in,String role_in, String range_play_in ){
       try {
           String sqlite = "INSERT INTO tb_user (name, password, gender, role, range_play) VALUES('" + name_in + "', '" + password_in + "', '" + gender_in + "', '" + role_in + "', '" + range_play_in + "');";
           SQLiteDatabase sqLiteDatabase = getWritableDatabase();
           sqLiteDatabase.execSQL(sqlite);
           Toast.makeText(Context, "Berhasil add data",
                   Toast.LENGTH_SHORT).show();
           return true;
       }catch (Exception e){
           return false;
       }
    }

    public Cursor showData(){
        try{
            String sql = "SELECT * FROM tb_orang";
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
            return cursor;
        }catch (Exception e){
            return null;
        }
    }

//    public void readUser( String name, String alamat, int usia, String gender ){
//        String sqlite = "INSERT INTO tb_user(name, password, gender, role, range_play) VALUES(name, password, gender, role, range_play);";
//        SQLiteDatabase db = getWritableDatabase();
//        db.execSQL(sqlite);
//        Toast.makeText(Context,"Berhasil add data",
//                Toast.LENGTH_SHORT).show();
//    }
}
