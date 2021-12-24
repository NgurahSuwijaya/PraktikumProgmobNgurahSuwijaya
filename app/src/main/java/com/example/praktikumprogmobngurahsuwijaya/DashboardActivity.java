package com.example.praktikumprogmobngurahsuwijaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity {
    EditText dName, dRole, dPlaytime;
    TextView dNameTitle;
    String sName, sRole, sPlaytime, sGender;
    Button btnSave, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        dName=findViewById(R.id.editTextDNameIn);
        dRole = findViewById(R.id.editTextDRoleIn);
        dPlaytime = findViewById(R.id.editTextDPlayTimeIn);
        dNameTitle = findViewById(R.id.textViewDName);
        btnLogout = findViewById(R.id.buttonLogout);
        btnSave = findViewById(R.id.buttonSave);

        Intent intent = getIntent();

        sName = intent.getStringExtra("name");
        sGender = intent.getStringExtra("gender");
        sRole = intent.getStringExtra("role");
        sPlaytime = intent.getStringExtra("range");

        dName.setText(sName);
        dRole.setText(sRole);
        dPlaytime.setText(sPlaytime);
        dNameTitle.setText(sName);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(DashboardActivity.this, AppActivity.class);
                startActivity(intent2);
            }
        });
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