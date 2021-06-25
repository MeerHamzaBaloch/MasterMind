package com.example.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.time.Instant;

public class UserActivity extends AppCompatActivity {
    EditText fullname,etemail,etpassword;
    Button registerbtn,LoginNow;
    DBHelper DB;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        fullname = (EditText) findViewById(R.id.full_name);
        etemail = (EditText) findViewById(R.id.et_email);
        etpassword = (EditText) findViewById(R.id.et_password);
        registerbtn = (Button) findViewById(R.id.register_btn);
        LoginNow =(Button)findViewById(R.id.Login_Now);
        DB = new DBHelper( this);
        {
            LoginNow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(UserActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            });
            registerbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String FullName = fullname.getText().toString();
                    String email = etemail.getText().toString();
                    String pass = etpassword.getText().toString();

                    if (FullName.equals("")||email.equals("")||pass.equals(""))
                        Toast.makeText( UserActivity.this, "Pleace Enter all the fields", Toast.LENGTH_SHORT).show();
                    else {
                            Boolean checkusersemail = DB.checkusersemail(email, FullName);
                            if (checkusersemail==false)
                            {
                                Boolean insert = DB.insertData(FullName,email,pass);
                                if (insert==true)
                                {
                                    Toast.makeText(UserActivity.this,"Registered Successfull",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(UserActivity.this,LoginActivity.class);
                                    startActivity(intent);
                                }else
                                {
                                    Toast.makeText(UserActivity.this,"Register failed",Toast.LENGTH_SHORT).show();
                                }
                            }else
                            {
                                Toast.makeText(UserActivity.this,"username or email already registered",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
            });

        }
    }
}