package com.example.praktikumprogmobngurahsuwijaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class AddQuestActivity extends AppCompatActivity {

    CheckBox duelist, sentinel, controller, initiator;
    DataHelper db;
    EditText quest;
    Button btnSubmit, btnBack;
    String sQuest, sRole, sStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quest);

        db = new DataHelper(this);

        sStatus = "None";
        quest = findViewById(R.id.editTextQuest);
        duelist = findViewById(R.id.checkBoxDuelist);
        sentinel = findViewById(R.id.checkBoxSentinel);
        controller = findViewById(R.id.checkBoxController);
        initiator = findViewById(R.id.checkBoxInitiator);

        btnSubmit =  findViewById(R.id.buttonSubmitDialogDone);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sQuest = quest.getText().toString();

                sRole = "";
                if(duelist.isChecked()){
                    sRole = sRole + "duelist; ";
                }
                if(initiator.isChecked()){
                    sRole = sRole + "initiator; ";
                }
                if(controller.isChecked()){
                    sRole = sRole + "controller; ";
                }
                if(sentinel.isChecked()){
                    sRole = sRole + "sentinel; ";
                }

                Toast.makeText(AddQuestActivity.this,"Registration Successfully",Toast.LENGTH_SHORT).show();

                ContentValues values = new ContentValues();
//                values.put(DataHelper.row_player, sName);
                values.put(DataHelper.row_quest, sQuest);
                values.put(DataHelper.row_role_quest, sRole);
                values.put(DataHelper.row_status, sStatus);

                DataHelper.insertQuest(values);

                Intent intent = new Intent(AddQuestActivity.this, AppActivity.class);
                startActivity(intent);
            }
        });

    }
}