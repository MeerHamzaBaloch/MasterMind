package com.example.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
  private   EditText Email,Password;
   private Button Login,RegisterNow;
   DBHelper DB;
   private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email = (EditText)findViewById(R.id.login_email);
        Password = (EditText) findViewById(R.id.login_password);
        Login = (Button)findViewById(R.id.login_btn);
        RegisterNow = (Button) findViewById(R.id.registerNow);
        DB = new DBHelper(this);

        RegisterNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,UserActivity.class);
                startActivity(intent);
            }
        });

       Login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               String email = Email.getText().toString();
               String pass = Password.getText().toString();

               if (email.equals("")||pass.equals(""))
                   Toast.makeText(LoginActivity.this,"Please Enter all fields",Toast.LENGTH_SHORT).show();
               else
                   {
                       Boolean checkemailpass = DB.checkusersemailpassword(email,pass);
                       if (checkemailpass==true)
                       {
                           Toast.makeText(LoginActivity.this,"Login successfull ",Toast.LENGTH_SHORT).show();
                           Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                           startActivity(intent);
                       }
                       else
                           {
                               Toast.makeText(LoginActivity.this,"invalid credentials",Toast.LENGTH_SHORT).show();
                           }
                   }

           }
       });

    }
}