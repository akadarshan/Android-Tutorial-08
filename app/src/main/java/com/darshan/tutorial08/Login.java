package com.darshan.tutorial08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText login_email,login_password;
    TextView regLink_tv;
    Button btnLogin;
    DatabaseHelper myDbHelper;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDbHelper=new DatabaseHelper(Login.this);
        setContentView(R.layout.activity_login);

        preferences = getSharedPreferences("session", MODE_PRIVATE);
        editor = preferences.edit();
        String pref_email = preferences.getString("email","");
        if(!pref_email.equals("")){
            Intent intent = new Intent(Login.this, Welcome.class);
            startActivity(intent);
            finish();
        }

        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);
        btnLogin = findViewById(R.id.btnLogin);
        regLink_tv = findViewById(R.id.regLink_tv);

        regLink_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,Registration.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String valEmail = login_email.getText().toString();
                String valPassword = login_password.getText().toString();

                if(!Patterns.EMAIL_ADDRESS.matcher(valEmail).matches()){
                    Toast.makeText(Login.this,"Email Format is Not Valid.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(new DatabaseHelper(Login.this).checkUserExist(valEmail,valPassword)){
                    editor.putString("email",valEmail);
                    editor.commit();
                    Intent intent = new Intent(Login.this,Welcome.class);
                    startActivity(intent);
//                    Toast.makeText(Login.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(Login.this,"Invalid Username OR Password",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}