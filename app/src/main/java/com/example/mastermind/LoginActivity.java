package com.example.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
  private   EditText username_et,Password;
   private Button Login,RegisterNow;
    public String adminname="admin";
    public String adminpass="admin";
   DBHelper DB;
   private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

   SharedPreferences sharedPreferences;
   private static final String SHARED_PREF_NAME="mypref";
   private static final String KEY_NAME="username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username_et = (EditText)findViewById(R.id.username_et);
        Password = (EditText) findViewById(R.id.login_password);
        Login = (Button)findViewById(R.id.login_btn);
        RegisterNow = (Button) findViewById(R.id.registerNow);

        //shared_Preferences to store username
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);

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

               String username = username_et.getText().toString();
               String pass = Password.getText().toString();

               if (username.equals("")||pass.equals(""))
                   Toast.makeText(LoginActivity.this,"Please Enter all fields",Toast.LENGTH_SHORT).show();
               else
                   {
                       Boolean checkemailpass = DB.checkusersemailpassword(username,pass);
                       if (checkemailpass==true)
                       {
                           SharedPreferences.Editor editor=sharedPreferences.edit();
                           editor.putString(KEY_NAME,username);
                           editor.apply();



                           Toast.makeText(LoginActivity.this,"Login successful ",Toast.LENGTH_SHORT).show();

                           Intent intent = new Intent(LoginActivity.this, level_select.class);
                           startActivity(intent);

                           username_et.getText().clear();
                           Password.getText().clear();


                       }
                       else
                           {
                               if (username.matches(adminname)&&pass.matches(adminpass))
                               {
                                   Intent intent = new Intent(LoginActivity.this, AdminPannelActivity.class);
                                   startActivity(intent);
                                   username_et.getText().clear();
                                   Password.getText().clear();
                               }else {
                                   Toast.makeText(LoginActivity.this,"invalid credentials",Toast.LENGTH_SHORT).show();
                               }
                           }
                   }

           }
       });
       

    }


    @Override
    public  void  onBackPressed(){
        moveTaskToBack(false);
    }
}