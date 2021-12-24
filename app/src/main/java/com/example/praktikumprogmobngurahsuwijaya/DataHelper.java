package com.example.praktikumprogmobngurahsuwijaya;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DataHelper extends SQLiteOpenHelper {
    private static final String database_name = "db_praktikum_DTR";

    public static final String table_name_player = "tb_player";
    public static final String row_id_player = "id";
    public static final String row_username = "username";
    public static final String row_name = "name";
    public static final String row_pass = "password";
    public static final String row_gender = "gender";
    public static final String row_role = "role";
    public static final String row_range = "range";

    public static final String row_id_quest = "id_quest";
    public static final String table_name_quest = "tb_quest";
    public static final String row_player = "player_id";
    public static final String row_quest = "quest";
    public static final String row_role_quest = "role_quest";
    public static final String row_status = "status_quest";

    private static SQLiteDatabase db;
    private Context context;

    public DataHelper(@Nullable Context context) {
        super(context, database_name, null, 1);
        db = getWritableDatabase();
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + table_name_player + "(" + row_id_player + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + row_username + " TEXT," + row_name + " TEXT," + row_pass + " TEXT," + row_gender + " TEXT," + row_role + " TEXT," + row_range + " TEXT)";

        String query_contact = "CREATE TABLE " + table_name_quest + "(" + row_id_quest + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + row_player + " INTEGER," + row_quest + " TEXT," + row_role_quest + " TEXT, " + row_status + " TEXT)";
        db.execSQL(query);
        db.execSQL(query_contact);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name_player);
        db.execSQL("DROP TABLE IF EXISTS " + table_name_quest);
    }

    public static void insertPlayer(ContentValues values){
        db.insert(table_name_player, null, values);
    }

    public static void insertQuest(ContentValues values){
        db.insert(table_name_quest, null, values);
    }

    public Cursor readAllData(){
        String sql = "select * from " + table_name_quest;
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(sql, null);
        }
        return cursor;
    }
    void updateData(String row_id, String quest, String role, String status){
        ContentValues cv = new ContentValues();
        cv.put(row_quest, quest);
        cv.put(row_role_quest, role);
        cv.put(row_status, status);

        long result = db.update(table_name_quest, cv, "id_quest=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }
    }
    void deleteOneRow(String row_id){
        long result = db.delete(table_name_quest, "id_quest=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }
}
