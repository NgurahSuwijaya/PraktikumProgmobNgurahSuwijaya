package com.example.praktikumprogmobngurahsuwijaya;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AppActivity extends AppCompatActivity {

    FloatingActionButton BtnAdd;
    RecyclerView listQuest;
    DataHelper db;
    ArrayList<String> quest_id, quest, quest_role, quest_player, quest_status;
    ListAdapter listAdapter;
    TextView TextViewEmpty;
    EditText editQuest;
    CheckBox duelist, sentinel, controller, initiator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        db = new DataHelper(this);
        listQuest = findViewById(R.id.recyclerQuest);
        BtnAdd = findViewById(R.id.floatingActionButtonAdd);
        TextViewEmpty = findViewById(R.id.noData);

        quest_id =  new ArrayList<>();
        quest = new ArrayList<>();
        quest_role = new ArrayList<>();
        quest_player = new ArrayList<>();
        quest_status = new ArrayList<>();

        storeDataInArrays();

        listAdapter = new ListAdapter(AppActivity.this, this,quest_id, quest, quest_role, quest_player, quest_status);
        listQuest.setAdapter(listAdapter);
        listQuest.setLayoutManager(new LinearLayoutManager(AppActivity.this));

        BtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AppActivity.this, AddQuestActivity.class);
                startActivity(intent);
            }
        });

    }
    void storeDataInArrays(){
        Cursor cursor = db.readAllData();
        if(cursor.getCount() == 0){
            TextViewEmpty.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                quest_id.add(cursor.getString(0));
                quest_player.add(cursor.getString(1));
                quest.add(cursor.getString(2));
                quest_role.add(cursor.getString(3));
                quest_status.add(cursor.getString(4));
            }
            TextViewEmpty.setVisibility(View.GONE);
        }
    }
}