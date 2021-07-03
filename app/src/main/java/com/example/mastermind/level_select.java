package com.example.mastermind;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class level_select extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    TextView tv_name;
    ImageView logout_btn;
    CardView easy_btn, medium_btn, hard_btn;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME="mypref";
    private static final String KEY_NAME="username";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_select);

        tv_name = (TextView) findViewById(R.id.username_tv);
        logout_btn = (ImageView) findViewById(R.id.logout);
        easy_btn = (CardView) findViewById(R.id.easy_level_btn);
        medium_btn = (CardView) findViewById(R.id.medium_level_btn);
        hard_btn = (CardView) findViewById(R.id.hard_level_btn);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_NAME,null);

        if (name != null){
            tv_name.setText(name);
        }

        easy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(level_select.this,easy_level.class);
                startActivity(intent);
            }
        });

        medium_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(level_select.this,medium_level.class);
                startActivity(intent);
            }
        });

        hard_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(level_select.this,hard_level.class);
                startActivity(intent);
            }
        });






    }









    @Override
    public  void  onBackPressed(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(level_select.this);
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

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Toast.makeText(level_select.this, "Log out successfully", Toast.LENGTH_SHORT).show();
                finish();
                Intent intent = new Intent(level_select.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.options);
        popup.show();
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout_option:
                final AlertDialog.Builder builder = new AlertDialog.Builder(level_select.this);
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

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.clear();
                        editor.commit();
                        Toast.makeText(level_select.this, "Log out successfully", Toast.LENGTH_SHORT).show();
                        finish();
                        Intent intent = new Intent(level_select.this,LoginActivity.class);
                        startActivity(intent);
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            default:
                return false;
        }
    }





}