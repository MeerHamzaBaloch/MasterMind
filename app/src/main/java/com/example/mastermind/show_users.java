package com.example.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class show_users extends AppCompatActivity {

    ArrayList<String> listItem;
    ArrayAdapter adapter;
    DBHelper db;
    Button btn_logout;

    ListView userlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_users);
        btn_logout = (Button) findViewById(R.id.users_logout);

        userlist = (ListView) findViewById(R.id.user_list);



        db = new DBHelper(this);
        listItem = new ArrayList<>();

        viewData();



        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(show_users.this);
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



                        Toast.makeText(show_users.this, "Log out successfully", Toast.LENGTH_SHORT).show();
                        finish();
                        Intent intent = new Intent(show_users.this,LoginActivity.class);
                        startActivity(intent);




                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });


    }

    private void viewData() {

        Cursor cursor = db.viewUsers();

        if(cursor.getCount() == 0){
            Toast.makeText(this,"No users", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                listItem.add(cursor.getString(0));
                listItem.add(cursor.getString(1));
                listItem.add(cursor.getString(2));
            }

            adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, listItem);
            userlist.setAdapter(adapter);

        }
    }
}