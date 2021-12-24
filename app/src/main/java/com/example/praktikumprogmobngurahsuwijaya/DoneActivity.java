package com.example.praktikumprogmobngurahsuwijaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class DoneActivity extends AppCompatActivity {

    CheckBox duelist, sentinel, controller, initiator;
    DataHelper db;
    EditText quest;
    Button btnSubmit, btnBack, btnDone, btnDelete;
    String sQuest, sRole, sStatus, sQuest_id, ssRole, getsQuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);

        db = new DataHelper(this);

        sStatus = "None";
        quest = findViewById(R.id.editTextQuest);
        duelist = findViewById(R.id.checkBoxDuelist);
        sentinel = findViewById(R.id.checkBoxSentinel);
        controller = findViewById(R.id.checkBoxController);
        initiator = findViewById(R.id.checkBoxInitiator);

        btnSubmit =  findViewById(R.id.buttonSubmitDialogDone);
        btnDone = findViewById(R.id.buttonDone);
        btnBack = findViewById(R.id.buttonBackDialog2);
        btnDelete = findViewById(R.id.buttonDelete);

        Intent intent = getIntent();

        sQuest_id = intent.getStringExtra("id");
        sQuest = intent.getStringExtra("quest");
        sRole = intent.getStringExtra("role");
        sStatus = intent.getStringExtra("status");

        quest.setText(sQuest);

        String[] splitRole = sRole.split(";");
        for(int i=0; i<=splitRole.length-2; i++) {
            if (splitRole[i].replaceAll("\\s+", "").equals("duelist")) {
                duelist.setChecked(true);
            } else if (splitRole[i].replaceAll("\\s+", "").equals("controller")){
                controller.setChecked(true);
            } else if (splitRole[i].replaceAll("\\s+", "").equals("sentinel")){
                sentinel.setChecked(true);
            } else if (splitRole[i].replaceAll("\\s+", "").equals("initiator")){
                sentinel.setChecked(true);
            }
        }

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteOneRow(sQuest_id);
                Intent intent = new Intent(DoneActivity.this, AppActivity.class);
                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoneActivity.this, AppActivity.class);
                startActivity(intent);
            }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sStatus = "Done";
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ssRole = "";
                if(duelist.isChecked()){
                    ssRole = ssRole + "duelist; ";
                }
                if(initiator.isChecked()){
                    ssRole = ssRole + "initiator; ";
                }
                if(controller.isChecked()){
                    ssRole = ssRole + "controller; ";
                }
                if(sentinel.isChecked()){
                    ssRole = ssRole + "sentinel; ";
                }

                getsQuest = quest.getText().toString().trim();
                db.updateData(sQuest_id, getsQuest, ssRole, sStatus);

                Intent intent = new Intent(DoneActivity.this, AppActivity.class);
                startActivity(intent);
            }
        });
    }

}