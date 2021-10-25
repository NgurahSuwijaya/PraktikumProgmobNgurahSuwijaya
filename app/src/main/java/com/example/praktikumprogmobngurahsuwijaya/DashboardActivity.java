package com.example.praktikumprogmobngurahsuwijaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity {
    DataHelper dataHelper;
    protected Cursor cursor;
    EditText dName, dRole, dPlaytime;
    TextView dNameTitle;
    String sName, sRole, sPlaytime, sGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        dName=findViewById(R.id.editTextDNameIn);
        dRole = findViewById(R.id.editTextDRoleIn);
        dPlaytime = findViewById(R.id.editTextDPlayTimeIn);
        dNameTitle = findViewById(R.id.textViewDName);

        Intent intent = getIntent();

        sName = intent.getStringExtra("name");
        sGender = intent.getStringExtra("gender");
        sRole = intent.getStringExtra("role");
        sPlaytime = intent.getStringExtra("range");



//        DataHelper dataHelper = new DataHelper(DashboardActivity.this);
//        SQLiteDatabase sqLiteDatabase = dataHelper.getReadableDatabase();
//        cursor = sqLiteDatabase.rawQuery("SELECT * FROM tb_mhs WHERE nim = 1 '" +
//                getIntent().getStringExtra("MainNim") + "'", null);
        dName.setText("Input Ur Full Name Pls");
        dRole.setText(sRole);
        dPlaytime.setText(sPlaytime);
        dNameTitle.setText(sName);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Siklus hidup onStart",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Siklus hidup onResume",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Siklus hidup onPause",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Aplication Paused",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "Mantap",Toast.LENGTH_SHORT).show();
    }

}