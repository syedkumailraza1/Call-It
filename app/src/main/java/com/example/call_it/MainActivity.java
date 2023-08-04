package com.example.call_it;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Random;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    TextInputEditText name, id;
    MaterialButton createbtn, joinbtn;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences=getSharedPreferences("name_pref",MODE_PRIVATE);
        name = findViewById(R.id.name);
        id = findViewById(R.id.meeting_id);
        createbtn = findViewById(R.id.createbtn);
        joinbtn = findViewById(R.id.joinbtn);

        name.setText(sharedPreferences.getString("name",""));

        createbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               createmeeting();

            }
        });

        joinbtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        joinmeeting();
    }
    });


    }

    void joinmeeting() {
        String meetingid = id.getText().toString();

        if (meetingid.length() != 10) {
            id.setError("Invalid ID");
            id.requestFocus();
            return;
        }
        String username = name.getText().toString();
        if (username.isEmpty()) {
            name.setError("Enter Your Name");
            name.requestFocus();
            return;
        }

        startmeeting(meetingid, username);
    }

    void startmeeting(String meetingid, String username) {

        sharedPreferences.edit().putString("name",username).apply();

        String userid = UUID.randomUUID().toString();

        Intent intent =new Intent(MainActivity.this,conf_activity.class);
        intent.putExtra("meetingid",meetingid);
        intent.putExtra("name",username);
        intent.putExtra("userid",userid);
        startActivity(intent);
    }

    void createmeeting() {
        String username = name.getText().toString();
        if (username.isEmpty()) {
            name.setError("Enter Your Name");
            name.requestFocus();}
        startmeeting(getrandomid(),username);
        return;

    }

 String getrandomid(){
        StringBuilder ID=new StringBuilder();
        while(ID.length() != 10){
            int random=new Random().nextInt(10);
            ID.append(random);
        }
        return ID.toString();
 }


}