package com.example.praktikumprogmobngurahsuwijaya;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    boolean isAllFieldsChecked = false;
    TextView seekMeter, nameDialog, passDialog, genderDialog, roleDialog, rangeDialog;
    EditText name, password;
    CheckBox duelist, sentinel, controller, initiator;
    RadioGroup gender;
    RadioButton male, female;
    Button btnSubmit, btnBackDialog, btnSubmitDialog;
    SeekBar seekBar;
    DataHelper db;
    String sRole;
    String valueSeek;
    String sName;
    String sGender;
    String sPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DataHelper(this);
        seekMeter = findViewById(R.id.textViewSeekMeter);

        name = findViewById(R.id.editTextName);
        password = findViewById(R.id.editTextPassowrd);

        duelist = findViewById(R.id.checkBoxDuelist);
        sentinel = findViewById(R.id.checkBoxSentinel);
        controller = findViewById(R.id.checkBoxController);
        initiator = findViewById(R.id.checkBoxInitiator);

        gender = findViewById(R.id.radioGroupGender);
        male = findViewById(R.id.radioButtonMale);
        female = findViewById(R.id.radioButtonFemale);

        btnSubmit = findViewById(R.id.buttonSubmitDialog);
        seekBar  = findViewById(R.id.seekBar);

        //seek bar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                valueSeek = (String.valueOf(i));
                seekMeter.setText(valueSeek);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isAllFieldsChecked = CheckAllFields();
                if(isAllFieldsChecked){
                    sName = name.getText().toString();
                    sPass = password.getText().toString();

                    //Gender radio button
                    int selectedGender = gender.getCheckedRadioButtonId();
                    if(selectedGender == male.getId()){
                        sGender = male.getText().toString();
                    }else if(selectedGender == female.getId()){
                        sGender = female.getText().toString();
                    }
                    //role Checkbox
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
                    alertDialog();

                }
            }
        });
    }
    private boolean CheckAllFields(){
        if (name.length() == 0) {
            name.setError("Must be Filled");
            return false;
        }
        if (password.length() == 0) {
            password.setError("Must be Filled");
            return false;
        }
        if (gender.getCheckedRadioButtonId() == -1) {
            female.setError("Pls Chose ur Gender");
            return false;
        }
        if (!duelist.isChecked() && !sentinel.isChecked() && !controller.isChecked()) {
            Toast.makeText(MainActivity.this,"Pls Chose Ur Role",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void alertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.confirm_dialog,null);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        nameDialog = dialogView.findViewById(R.id.textViewNameDialog);
        passDialog = dialogView.findViewById(R.id.textViewPassDialog);
        genderDialog =  dialogView.findViewById(R.id.textViewGenderDialog);
        roleDialog = dialogView.findViewById(R.id.textViewRoleDialog);
        rangeDialog = dialogView.findViewById(R.id.textViewRangeDialog);

        btnBackDialog = dialogView.findViewById(R.id.buttonBackDialog);
        btnSubmitDialog = dialogView.findViewById(R.id.buttonSubmitDialog);

        nameDialog.setText(sName);
        passDialog.setText(sPass);
        genderDialog.setText(sGender);
        roleDialog.setText(sRole);
        rangeDialog.setText(valueSeek);

        btnSubmitDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

                Toast.makeText(MainActivity.this,"Registration Successfully",Toast.LENGTH_SHORT).show();

                ContentValues values = new ContentValues();
                values.put(DataHelper.row_username, sName);
                values.put(DataHelper.row_pass, sPass);
                values.put(DataHelper.row_gender, sGender);
                values.put(DataHelper.row_role, sRole);
                values.put(DataHelper.row_range, valueSeek);

                DataHelper.insertPlayer(values);

                Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                intent.putExtra("name", sName);
                intent.putExtra("gender", sGender);
                intent.putExtra("role", sRole);
                intent.putExtra("range", valueSeek);
                startActivity(intent);

            }
        });

        btnBackDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
