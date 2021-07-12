package com.example.mastermind;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AdminPannelActivity extends AppCompatActivity {
    ImageView Listword, users;
    Button btn_logout;
    CardView ScoresBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_pannel);

        Listword = (ImageView) findViewById(R.id.word_list);
        btn_logout = (Button) findViewById(R.id.admin_logout);
        users = (ImageView) findViewById(R.id.show_users);
        ScoresBtn = (CardView) findViewById(R.id.view_scoresBtn);

        Listword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminPannelActivity.this,AddWordsActivity.class);
                startActivity(intent);
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(AdminPannelActivity.this);
                builder.setMessage("Are you sure you want to logout?");
                builder.setCancelable(true);
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {



                        Toast.makeText(AdminPannelActivity.this, "Log out successfully", Toast.LENGTH_SHORT).show();
                        finish();
                        Intent intent = new Intent(AdminPannelActivity.this,LoginActivity.class);
                        startActivity(intent);




                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });

        users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminPannelActivity.this,show_users.class);
                startActivity(intent);
            }
        });

        ScoresBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminPannelActivity.this,view_scores.class);
                startActivity(intent);
            }
        });









    }


    @Override
    public  void  onBackPressed(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(AdminPannelActivity.this);
        builder.setMessage("Are you sure you want to logout?");
        builder.setCancelable(true);
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(AdminPannelActivity.this, "Log out successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AdminPannelActivity.this,LoginActivity.class);
                startActivity(intent);

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

}