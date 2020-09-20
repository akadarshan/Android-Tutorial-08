package com.darshan.tutorial08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

public class UserDetail extends AppCompatActivity {

    TextView txtRegisterMsg;
    DatabaseHelper myDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDbHelper=new DatabaseHelper(UserDetail.this);
        setContentView(R.layout.activity_user_detail);

        txtRegisterMsg = findViewById(R.id.txtRegisterMsg);

        String userAllData="";

        Intent intent = getIntent();
        String userdata = intent.getStringExtra("userdata");

        Cursor cursor = myDbHelper.getSingleUserDetail(userdata);
        cursor.moveToFirst();
        userAllData +="FirstName : "+cursor.getString(1);
        userAllData +="\nLastName : "+cursor.getString(2);
        userAllData +="\nEmail : "+cursor.getString(3);
        userAllData +="\nPassword : "+cursor.getString(4);
        userAllData +="\nBranch CE/IT : "+cursor.getString(5);
        userAllData +="\nGender : "+cursor.getString(6);
        userAllData +="\nCity : "+cursor.getString(7);
        userAllData +="\nStatus : "+cursor.getString(8);

        txtRegisterMsg.setText(userAllData);
    }
}